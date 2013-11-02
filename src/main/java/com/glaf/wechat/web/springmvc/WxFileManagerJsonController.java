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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.glaf.core.security.LoginContext;
import com.glaf.core.util.DateUtils;
import com.glaf.core.util.RequestUtils;

import com.glaf.wechat.domain.WxFile;
import com.glaf.wechat.query.WxFileQuery;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.util.WechatUtils;

@Controller("/wx/fileManagerJson")
@RequestMapping("/wx/fileManagerJson")
public class WxFileManagerJsonController {

	protected WxFileService wxFileService;

	private static class NameComparator implements Comparator<Object>,
			java.io.Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(Object a, Object b) {
			Hashtable<?, ?> hashA = (Hashtable<?, ?>) a;
			Hashtable<?, ?> hashB = (Hashtable<?, ?>) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}

	private static class SizeComparator implements Comparator<Object>,
			java.io.Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(Object a, Object b) {
			Hashtable<?, ?> hashA = (Hashtable<?, ?>) a;
			Hashtable<?, ?> hashB = (Hashtable<?, ?>) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	private static class TypeComparator implements Comparator<Object>,
			java.io.Serializable {

		private static final long serialVersionUID = 1L;

		public int compare(Object a, Object b) {
			Hashtable<?, ?> hashA = (Hashtable<?, ?>) a;
			Hashtable<?, ?> hashB = (Hashtable<?, ?>) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}

	@RequestMapping
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");

		LoginContext loginContext = RequestUtils.getLoginContext(request);

		// 文件扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp",
				"swf", "mp3", "wma", "wav", "amr", "rm", "rmvb", "mp4", "wvm",
				"avi", "mpg", "mpeg" };

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request
				.getParameter("order").toLowerCase() : "filename";

		WxFileQuery query = new WxFileQuery();
		query.createBy(loginContext.getActorId());
		query.serviceKey("IMG_" + loginContext.getActorId());
		if ("size".equals(order)) {
			query.setSortField("size");
		} else if ("type".equals(order)) {
			query.setSortField("type");
		} else {
			query.setSortField("filename");
		}

		List<WxFile> dataFiles = wxFileService.list(query);

		// 遍历目录取的文件信息
		List<Hashtable<?, ?>> fileList = new ArrayList<Hashtable<?, ?>>();
		if (dataFiles != null && !dataFiles.isEmpty()) {
			for (WxFile file : dataFiles) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getFilename();

				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				hash.put("is_dir", false);
				hash.put("has_file", false);
				hash.put("filesize", file.getSize());
				hash.put("is_photo", Arrays.<String> asList(fileTypes)
						.contains(fileExt));
				hash.put("filetype", fileExt);

				hash.put("filename", file.getFilename());
				hash.put("datetime",
						DateUtils.getDateTime(file.getCreateDate()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();

		String rand = WechatUtils.getHashedPath(loginContext.getActorId());
		String path = com.glaf.wechat.util.Constants.UPLOAD_PATH + rand + "/";

		result.put("moveup_dir_path", request.getContextPath() + path);
		result.put("current_dir_path", request.getContextPath() + path);
		result.put("current_url", request.getContextPath() + path);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(result.toString());
	}

	@javax.annotation.Resource
	public void setWxFileService(WxFileService wxFileService) {
		this.wxFileService = wxFileService;
	}

}