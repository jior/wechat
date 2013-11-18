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
						wxFileService.save(wxFile);
					}
				}
			}
		} else if (id != null) {
			WxFile wxFile = wxFileService.getWxFile(Long.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */

			if (wxFile != null
					&& (StringUtils.equals(wxFile.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxFileService.save(wxFile);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/detail")
	public byte[] detail(HttpServletRequest request) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxFile wxFile = wxFileService.getWxFile(RequestUtils.getLong(request,
				"id"));
		if (wxFile != null
				&& (StringUtils.equals(wxFile.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			JSONObject rowJSON = wxFile.toJsonObject();
			return rowJSON.toJSONString().getBytes("UTF-8");
		}
		return null;
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
			JSONObject rowJSON = wxFile.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
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

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxFileQuery query = new WxFileQuery();
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

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
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
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		String actorId = loginContext.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = req.getFileMap();
		WxFile wxFile = new WxFile();
		wxFile.setId(RequestUtils.getLong(req, "id"));
		wxFile.setTitle(req.getParameter("title"));
		wxFile.setDesc(req.getParameter("desc"));
		wxFile.setContent(req.getParameter("content"));
		wxFile.setCategoryId(RequestUtils.getLong(req, "categoryId"));

		boolean update = true;
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile mFile = entry.getValue();
			if (mFile.getSize() > 0) {
				String rand = WechatUtils.getHashedPath(loginContext
						.getActorId());
				String path = com.glaf.wechat.util.Constants.UPLOAD_PATH + rand;
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
					wxFile.setFilename(newFilename);
					wxFile.setOriginalFilename(mFile.getOriginalFilename());
					wxFile.setPath(path + "/" + newFilename);
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

		return new ModelAndView("/wx/file/saveOK");
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

		WxFile wxFile = wxFileService.getWxFile(RequestUtils.getLong(request,
				"id"));
		if (wxFile != null
				&& (StringUtils.equals(wxFile.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxFile.setCategoryId(RequestUtils.getLong(request, "categoryId"));
			wxFile.setTitle(request.getParameter("title"));
			wxFile.setFilename(request.getParameter("filename"));
			wxFile.setPath(request.getParameter("path"));
			wxFile.setLastUpdateBy(loginContext.getActorId());

			wxFileService.save(wxFile);
		}

		return new ModelAndView("/wx/file/saveOK");
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		WxFile wxFile = wxFileService.getWxFile(RequestUtils.getLong(request,
				"id"));
		request.setAttribute("wxFile", wxFile);
		JSONObject rowJSON = wxFile.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());

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
