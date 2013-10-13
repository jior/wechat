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
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.*;
import com.glaf.core.config.SystemProperties;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.security.*;
import com.glaf.core.util.*;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.*;

@Controller("/wx/wxCover")
@RequestMapping("/wx/wxCover")
public class WxCoverController {
	protected static final Log logger = LogFactory
			.getLog(WxCoverController.class);

	protected WxCoverService wxCoverService;

	protected WxFileService wxFileService;

	public WxCoverController() {

	}

	@ResponseBody
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long id = RequestUtils.getLong(request, "id");
		String ids = request.getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			StringTokenizer token = new StringTokenizer(ids, ",");
			while (token.hasMoreTokens()) {
				String x = token.nextToken();
				if (StringUtils.isNotEmpty(x)) {
					WxCover wxCover = wxCoverService
							.getWxCover(Long.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxCover != null
							&& (StringUtils.equals(wxCover.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxCoverService.save(wxCover);
					}
				}
			}
		} else if (id != null) {
			WxCover wxCover = wxCoverService.getWxCover(Long.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */
			if (wxCover != null
					&& (StringUtils.equals(wxCover.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxCoverService.save(wxCover);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/detail")
	public byte[] detail(HttpServletRequest request) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxCover wxCover = wxCoverService.getWxCover(RequestUtils.getLong(
				request, "id"));
		if (wxCover != null
				&& (StringUtils.equals(wxCover.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			JSONObject rowJSON = wxCover.toJsonObject();
			return rowJSON.toJSONString().getBytes("UTF-8");
		}
		return null;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		WxCover wxCover = wxCoverService.getWxCoverByUser(loginContext
				.getActorId());
		if (wxCover != null
				&& (StringUtils.equals(wxCover.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxCover", wxCover);
			JSONObject rowJSON = wxCover.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxCover.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/cover/edit", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxCoverQuery query = new WxCoverQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);

		String actorId = loginContext.getActorId();
		query.createBy(actorId);

		String gridType = ParamUtils.getString(params, "gridType");
		if (gridType == null) {
			gridType = "easyui";
		}
		int start = 0;
		int limit = 10;
		String orderName = null;
		String order = null;

		int pageNo = ParamUtils.getInt(params, "page");
		limit = ParamUtils.getInt(params, "rows");
		start = (pageNo - 1) * limit;
		orderName = ParamUtils.getString(params, "sortName");
		order = ParamUtils.getString(params, "sortOrder");

		if (start < 0) {
			start = 0;
		}

		if (limit <= 0) {
			limit = Paging.DEFAULT_PAGE_SIZE;
		}

		JSONObject result = new JSONObject();
		int total = wxCoverService.getWxCoverCountByQueryCriteria(query);
		if (total > 0) {
			result.put("total", total);
			result.put("totalCount", total);
			result.put("totalRecords", total);
			result.put("start", start);
			result.put("startIndex", start);
			result.put("limit", limit);
			result.put("pageSize", limit);

			if (StringUtils.isNotEmpty(orderName)) {
				query.setSortOrder(orderName);
				if (StringUtils.equals(order, "desc")) {
					query.setSortOrder(" desc ");
				}
			}

			List<WxCover> list = wxCoverService.getWxCoversByQueryCriteria(
					start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxCover wxCover : list) {
					JSONObject rowJSON = wxCover.toJsonObject();
					rowJSON.put("id", wxCover.getId());
					rowJSON.put("wxCoverId", wxCover.getId());
					rowJSON.put("startIndex", ++start);
					rowsJSON.add(rowJSON);
				}

			}
		} else {
			JSONArray rowsJSON = new JSONArray();
			result.put("rows", rowsJSON);
			result.put("total", total);
		}
		return result.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String x_query = request.getParameter("x_query");
		if (StringUtils.equals(x_query, "true")) {
			Map<String, Object> paramMap = RequestUtils
					.getParameterMap(request);
			String x_complex_query = JsonUtils.encode(paramMap);
			x_complex_query = RequestUtils.encodeString(x_complex_query);
			request.setAttribute("x_complex_query", x_complex_query);
		} else {
			request.setAttribute("x_complex_query", "");
		}
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		return new ModelAndView("/wx/cover/list", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxCover.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/cover/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		String actorId = loginContext.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = req.getFileMap();
		WxCover wxCover = wxCoverService.getWxCoverByUser(actorId);
		if (wxCover == null) {
			wxCover = new WxCover();
			wxCover.setCreateBy(actorId);
		}
		boolean save = false;
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile mFile = entry.getValue();
			if (mFile.getSize() > 0) {
				String rand = Math.abs(HashUtils.FNVHash1(actorId) % 1024)
						+ "/" + DigestUtils.md5Hex(loginContext.getActorId());
				String path = "/upload/users/" + rand;
				try {
					FileUtils.mkdirs(SystemProperties.getAppPath() + path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				String fileExt = FileUtils.getFileExt(
						mFile.getOriginalFilename()).toLowerCase();
				if (!(StringUtils.equals(fileExt, "jsp") || StringUtils.equals(
						fileExt, "js"))) {
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFilename = df.format(new Date()) + "_"
							+ new Random().nextInt(10000) + "." + fileExt;
					WxFile wxFile = new WxFile();
					wxFile.setId(RequestUtils.getLong(req, "id"));
					wxFile.setFilename(newFilename);
					wxFile.setOriginalFilename(mFile.getOriginalFilename());
					wxFile.setPath(path + "/" + newFilename);
					wxFile.setCreateBy(actorId);
					wxFile.setSize(mFile.getSize());

					if (StringUtils.equals(mFile.getName(), "bigIcon")) {
						wxCover.setBigIcon(wxFile.getPath());
						wxFile.setTitle("封面大图片");
						save = true;
					} else if (StringUtils.equals(mFile.getName(), "smallIcon")) {
						wxFile.setTitle("封面小图片");
						wxCover.setSmallIcon(wxFile.getPath());
						save = true;
					}

					wxFileService.save(wxFile);

					try {
						FileUtils.save(
								SystemProperties.getAppPath()
										+ wxFile.getPath(), mFile.getBytes());
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}

		if (save) {
			wxCover.setLastUpdateBy(actorId);
			this.wxCoverService.save(wxCover);
		}

		return this.edit(request, modelMap);
	}

	@javax.annotation.Resource
	public void setWxCoverService(WxCoverService wxCoverService) {
		this.wxCoverService = wxCoverService;
	}

	@javax.annotation.Resource
	public void setWxFileService(WxFileService wxFileService) {
		this.wxFileService = wxFileService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxCover wxCover = wxCoverService.getWxCover(RequestUtils.getLong(
				request, "id"));
		if (wxCover != null
				&& (StringUtils.equals(wxCover.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxCover.setBigIcon(request.getParameter("bigIcon"));
			wxCover.setSmallIcon(request.getParameter("smallIcon"));
			wxCover.setLastUpdateBy(loginContext.getActorId());
		}

		wxCoverService.save(wxCover);

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		WxCover wxCover = wxCoverService.getWxCover(RequestUtils.getLong(
				request, "id"));
		request.setAttribute("wxCover", wxCover);
		JSONObject rowJSON = wxCover.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxCover.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/cover/view");
	}

}
