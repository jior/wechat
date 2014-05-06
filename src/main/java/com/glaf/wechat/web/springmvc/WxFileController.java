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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.config.SystemProperties;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.identity.User;
import com.glaf.core.security.LoginContext;
import com.glaf.core.util.FileUtils;
import com.glaf.core.util.JsonUtils;
import com.glaf.core.util.LogUtils;
import com.glaf.core.util.Paging;
import com.glaf.core.util.ParamUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.Tools;
import com.glaf.wechat.domain.WxFile;
import com.glaf.wechat.query.WxFileQuery;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.util.WechatUtils;

@Controller("/wx/wxFile")
@RequestMapping("/wx/wxFile")
public class WxFileController {
	protected static final Log logger = LogFactory
			.getLog(WxFileController.class);

	protected WxFileService wxFileService;

	public WxFileController() {

	}

	@RequestMapping("/chooseFile")
	public ModelAndView chooseFile(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		LogUtils.debug("params=" + RequestUtils.getParameterMap(request));
		String x_view = ViewProperties.getString("wxFile.chooseFile");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/file/chooseFile", modelMap);
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
					WxFile wxFile = wxFileService.getWxFile(Long.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxFile != null
							&& (StringUtils.equals(wxFile.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						String filename = SystemProperties.getAppPath()
								+ wxFile.getPath();
						FileUtils.deleteFile(filename);
						wxFileService.deleteById(wxFile.getId());
					}
				}
			}
		} else if (id != null) {
			WxFile wxFile = wxFileService.getWxFile(Long.valueOf(id));

			if (wxFile != null
					&& (StringUtils.equals(wxFile.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				String filename = SystemProperties.getAppPath()
						+ wxFile.getPath();
				FileUtils.deleteFile(filename);
				wxFileService.deleteById(id);
			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");

		WxFile wxFile = wxFileService.getWxFile(RequestUtils.getLong(request,
				"id"));
		if (wxFile != null
				&& (StringUtils.equals(wxFile.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxFile", wxFile);
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxFile.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/file/edit", modelMap);
	}

	@RequestMapping("/json/{accountId}")
	@ResponseBody
	public byte[] json(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxFileQuery query = new WxFileQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);
		query.setAccountId(accountId);

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
		int total = wxFileService.getWxFileCountByQueryCriteria(query);
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

			List<WxFile> list = wxFileService.getWxFilesByQueryCriteria(start,
					limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxFile wxFile : list) {
					JSONObject rowJSON = wxFile.toJsonObject();
					rowJSON.put("id", wxFile.getId());
					rowJSON.put("wxFileId", wxFile.getId());
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

	@RequestMapping("/json2/{accountId}")
	@ResponseBody
	public byte[] json2(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxFileQuery query = new WxFileQuery();
		Tools.populate(query, params);

		query.createBy("system");
		query.setAccountId(accountId);

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
		int total = wxFileService.getWxFileCountByQueryCriteria(query);
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

			List<WxFile> list = wxFileService.getWxFilesByQueryCriteria(start,
					limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxFile wxFile : list) {
					JSONObject rowJSON = wxFile.toJsonObject();
					rowJSON.put("id", wxFile.getId());
					rowJSON.put("fileId", wxFile.getId());
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

	@RequestMapping("/jsonArray")
	@ResponseBody
	public byte[] jsonArray(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxFileQuery query = new WxFileQuery();
		Tools.populate(query, params);

		query.createBy("system");

		JSONArray result = new JSONArray();
		int total = wxFileService.getWxFileCountByQueryCriteria(query);
		if (total > 0) {
			List<WxFile> list = wxFileService.list(query);
			if (list != null && !list.isEmpty()) {
				for (WxFile wxFile : list) {
					JSONObject rowJSON = wxFile.toJsonObject();
					rowJSON.put("id", wxFile.getId());
					rowJSON.put("fileId", wxFile.getId());
					result.add(rowJSON);
				}
			}
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

		String requestURI = request.getRequestURI();
		logger.debug("requestURI:" + requestURI);
		logger.debug("queryString:" + request.getQueryString());
		request.setAttribute(
				"fromUrl",
				RequestUtils.encodeURL(requestURI + "?"
						+ request.getQueryString()));

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		return new ModelAndView("/wx/file/list", modelMap);
	}

	@RequestMapping("/query/{accountId}")
	public ModelAndView query(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		request.setAttribute("accountId", accountId);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxFile.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/file/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		Long accountId = RequestUtils.getLong(req, "accountId");
		Long id = RequestUtils.getLong(req, "id");
		Map<String, MultipartFile> fileMap = req.getFileMap();
		WxFile wxFile = null;
		String path = null;
		boolean update = true;
		boolean exists = false;
		if (id != null && id > 0) {
			wxFile = wxFileService.getWxFile(id);
		}
		if (wxFile != null) {
			exists = true;
			path = wxFile.getPath();
		} else {
			exists = false;
			wxFile = new WxFile();
		}
		wxFile.setTitle(req.getParameter("title"));
		wxFile.setDesc(req.getParameter("desc"));
		wxFile.setContent(req.getParameter("content"));
		if (!exists) {
			if (StringUtils.isNotEmpty(request.getParameter("categoryId"))
					&& !StringUtils.equals(request.getParameter("categoryId"),
							"undefined")) {
				wxFile.setCategoryId(RequestUtils.getLong(req, "categoryId"));
			}
			wxFile.setAccountId(accountId);
		}

		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile mFile = entry.getValue();
			if (mFile.getSize() > 0) {
				String rand = WechatUtils.getImagePath(user.getId(), accountId);
				if (path == null) {
					path = com.glaf.wechat.util.Constants.UPLOAD_PATH + rand;
					try {
						FileUtils.mkdirs(SystemProperties.getAppPath() + path);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

				String fileExt = FileUtils.getFileExt(
						mFile.getOriginalFilename()).toLowerCase();
				if (!(StringUtils.equals(fileExt, "jsp") || StringUtils.equals(
						fileExt, "js"))) {
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					wxFile.setOriginalFilename(mFile.getOriginalFilename());
					if (!exists) {
						String newFilename = df.format(new Date()) + "_"
								+ new Random().nextInt(10000) + "." + fileExt;
						wxFile.setFilename(newFilename);
						wxFile.setPath(path + "/" + newFilename);
					}
					wxFile.setCreateBy(actorId);
					wxFile.setSize(mFile.getSize());
					wxFileService.save(wxFile);
					try {
						FileUtils.save(
								SystemProperties.getAppPath()
										+ wxFile.getPath(), mFile.getBytes());
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					update = false;
				}
			}
		}

		if (update) {
			wxFileService.save(wxFile);
		}

		return this.list(request, modelMap);
	}

	@javax.annotation.Resource
	public void setWxFileService(WxFileService wxFileService) {
		this.wxFileService = wxFileService;
	}

	@RequestMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id,
			HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		WxFile wxFile = wxFileService.getWxFile(id);
		request.setAttribute("wxFile", wxFile);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxFile.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/file/view");
	}

}
