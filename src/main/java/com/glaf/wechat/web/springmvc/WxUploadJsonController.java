/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glaf.wechat.web.springmvc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.glaf.core.config.CustomProperties;
import com.glaf.core.config.SystemProperties;
import com.glaf.core.identity.User;
import com.glaf.core.security.LoginContext;
import com.glaf.core.util.FileUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.wechat.domain.WxFile;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.util.WechatUtils;

@Controller("/wx/uploadJson")
@RequestMapping("/wx/uploadJson")
public class WxUploadJsonController {

	public final static String sp = System.getProperty("file.separator");

	protected WxFileService wxFileService;

	private String getError(String message) throws Exception {
		JSONObject object = new JSONObject();
		object.put("error", 1);
		object.put("message", message);
		return object.toString();
	}

	@javax.annotation.Resource
	public void setWxFileService(WxFileService wxFileService) {
		this.wxFileService = wxFileService;
	}

	@RequestMapping
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		// 文件保存目录路径

		LoginContext loginContext = RequestUtils.getLoginContext(request);

		// 定义允许上传的文件扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp",
				"swf", "mp3", "wma", "wav", "amr", "rm", "rmvb", "mp4", "wvm",
				"avi", "mpg", "mpeg" };
		// 最大文件大小
		long maxSize = FileUtils.MB_SIZE * 5;

		String allowSize = CustomProperties.getString("upload.maxSize");
		if (StringUtils.isEmpty(allowSize)) {
			allowSize = SystemProperties.getString("upload.maxSize");
		}

		if (StringUtils.isNotEmpty(allowSize)
				&& StringUtils.isNumeric(allowSize)) {
			maxSize = Long.parseLong(allowSize);
		}

		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

		Map<String, MultipartFile> fileMap = req.getFileMap();
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile mFile = entry.getValue();
			if (mFile.getOriginalFilename() != null && mFile.getSize() > 0) {
				// 检查文件大小
				if (mFile.getSize() > maxSize) {
					response.getWriter().write(getError("上传文件大小超过限制。"));
					return;
				}
				String fileName = mFile.getOriginalFilename();
				// 检查扩展名
				String fileExt = FileUtils.getFileExt(fileName).toLowerCase();
				if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
					response.getWriter().write(getError("上传文件扩展名是不允许的扩展名。"));
					return;
				}

				Long accountId = RequestUtils.getLong(request, "accountId");
				User user = RequestUtils.getUser(request);
				String rand = WechatUtils.getImagePath(user.getId(), accountId);
				String path = com.glaf.wechat.util.Constants.UPLOAD_PATH + rand;
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFilename = df.format(new Date()) + "_"
						+ new Random().nextInt(10000) + "." + fileExt;
				String savePath = path + "/" + newFilename;
				try {

					WxFile wxFile = new WxFile();
					wxFile.setCreateBy(loginContext.getActorId());
					wxFile.setCreateDate(new Date());
					wxFile.setFilename(newFilename);
					wxFile.setOriginalFilename(mFile.getOriginalFilename());
					wxFile.setPath(savePath);
					wxFile.setTitle(mFile.getName());
					wxFile.setSize(mFile.getSize());
					wxFile.setType(mFile.getContentType());
					wxFile.setCategoryId(RequestUtils
							.getLong(req, "categoryId"));
					try {
						FileUtils.mkdirs(SystemProperties.getAppPath() + path);
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					wxFileService.save(wxFile);

					try {
						FileUtils.save(
								SystemProperties.getAppPath()
										+ wxFile.getPath(), mFile.getBytes());
					} catch (IOException ex) {
						ex.printStackTrace();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					response.getWriter().write(getError("上传文件失败。"));
					return;
				}

				JSONObject object = new JSONObject();
				object.put("error", 0);
				object.put("url", request.getContextPath() + savePath);
				response.getWriter().write(object.toString());
			}
		}
	}

}