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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.el.ExpressionTools;
import com.glaf.core.security.LoginContext;
import com.glaf.core.util.JsonUtils;
import com.glaf.core.util.Paging;
import com.glaf.core.util.ParamUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.ResponseUtils;
import com.glaf.core.util.Tools;
import com.glaf.wechat.domain.WxModule;
import com.glaf.wechat.query.WxModuleQuery;
import com.glaf.wechat.service.WxModuleService;

@Controller("/wx/wxModule")
@RequestMapping("/wx/wxModule")
public class WxModuleController {
	protected static final Log logger = LogFactory
			.getLog(WxModuleController.class);

	protected WxModuleService wxModuleService;

	public WxModuleController() {

	}

	@ResponseBody
	@RequestMapping("/dataJson")
	public byte[] dataJson(HttpServletRequest request) throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.put("contextPath", request.getContextPath());
		JSONObject result = new JSONObject();
		long id = RequestUtils.getLong(request, "id");
		long accountId = RequestUtils.getLong(request, "accountId");
		long total = 0;
		WxModule wxModule = wxModuleService.getWxModuleWithData(id, accountId,
				params);
		if (wxModule != null && wxModule.getDataList() != null) {
			List<Map<String, Object>> dataList = wxModule.getDataList();
			JSONArray rowsJSON = new JSONArray();
			int start = 0;
			String link = wxModule.getLink();
			String list_link = wxModule.getLink();
			for (Map<String, Object> row : dataList) {
				row.put("accountId", accountId);

				JSONObject json = new JSONObject();

				Set<Entry<String, Object>> entrySet = row.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					String key = entry.getKey();
					Object value = entry.getValue();
					json.put(key, value);
				}

				if (link != null) {
					String mx_link = ExpressionTools.evaluate(link, row);
					json.put("link", mx_link);
					json.put("url", mx_link);
				}
				if (list_link != null) {
					String mx_list_link = ExpressionTools.evaluate(list_link,
							row);
					json.put("list_link", mx_list_link);
				}
				json.put("startIndex", ++start);
				rowsJSON.add(json);
			}

			result.put("rows", rowsJSON);
			result.put("total", dataList.size());
		} else {
			JSONArray rowsJSON = new JSONArray();
			result.put("rows", rowsJSON);
			result.put("total", total);
		}
		return result.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		if (loginContext.isSystemAdministrator()) {
			RequestUtils.setRequestParameterToAttribute(request);
			request.removeAttribute("canSubmit");

			WxModule wxModule = wxModuleService.getWxModule(RequestUtils
					.getLong(request, "id"));
			if (wxModule != null) {
				request.setAttribute("wxModule", wxModule);
			}
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxModule.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/module/edit", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxModuleQuery query = new WxModuleQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);
		/**
		 * 此处业务逻辑需自行调整
		 */
		if (!loginContext.isSystemAdministrator()) {
			String actorId = loginContext.getActorId();
			query.createBy(actorId);
		}

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
		int total = wxModuleService.getWxModuleCountByQueryCriteria(query);
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

			List<WxModule> list = wxModuleService.getWxModulesByQueryCriteria(
					start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxModule wxModule : list) {
					JSONObject rowJSON = wxModule.toJsonObject();
					rowJSON.put("id", wxModule.getId());
					rowJSON.put("moduleId", wxModule.getId());
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
		if (request.getQueryString() != null) {
			request.setAttribute(
					"fromUrl",
					RequestUtils.encodeURL(requestURI + "?"
							+ request.getQueryString()));
		} else {
			request.setAttribute("fromUrl", RequestUtils.encodeURL(requestURI));
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		return new ModelAndView("/wx/module/list", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		if (loginContext.isSystemAdministrator()) {
			WxModule wxModule = new WxModule();
			Tools.populate(wxModule, params);

			wxModule.setCode(request.getParameter("code"));
			wxModule.setTitle(request.getParameter("title"));
			wxModule.setContent(request.getParameter("content"));
			wxModule.setLink(request.getParameter("link"));
			wxModule.setListLink(request.getParameter("listLink"));
			wxModule.setLinkType(request.getParameter("linkType"));
			wxModule.setIdField(request.getParameter("idField"));
			wxModule.setSubjectField(request.getParameter("subjectField"));
			wxModule.setModuleId(request.getParameter("moduleId"));
			wxModule.setModuleName(request.getParameter("moduleName"));
			wxModule.setSql(request.getParameter("sql"));
			wxModule.setJson(request.getParameter("json"));
			wxModule.setLocked(RequestUtils.getInt(request, "locked"));
			wxModule.setSort(RequestUtils.getInt(request, "sort"));

			wxModuleService.save(wxModule);
		}

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxModule")
	public byte[] saveWxModule(HttpServletRequest request) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		if (loginContext.isSystemAdministrator()) {
			Map<String, Object> params = RequestUtils.getParameterMap(request);
			WxModule wxModule = new WxModule();
			try {
				Tools.populate(wxModule, params);
				wxModule.setCode(request.getParameter("code"));
				wxModule.setTitle(request.getParameter("title"));
				wxModule.setContent(request.getParameter("content"));
				wxModule.setLink(request.getParameter("link"));
				wxModule.setListLink(request.getParameter("listLink"));
				wxModule.setLinkType(request.getParameter("linkType"));
				wxModule.setIdField(request.getParameter("idField"));
				wxModule.setSubjectField(request.getParameter("subjectField"));
				wxModule.setModuleId(request.getParameter("moduleId"));
				wxModule.setModuleName(request.getParameter("moduleName"));
				wxModule.setSql(request.getParameter("sql"));
				wxModule.setJson(request.getParameter("json"));
				wxModule.setLocked(RequestUtils.getInt(request, "locked"));
				wxModule.setSort(RequestUtils.getInt(request, "sort"));
				this.wxModuleService.save(wxModule);
				return ResponseUtils.responseJsonResult(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
			}
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxModuleService(WxModuleService wxModuleService) {
		this.wxModuleService = wxModuleService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		if (loginContext.isSystemAdministrator()) {
			WxModule wxModule = wxModuleService.getWxModule(RequestUtils
					.getLong(request, "id"));
			if (wxModule != null) {
				wxModule.setCode(request.getParameter("code"));
				wxModule.setTitle(request.getParameter("title"));
				wxModule.setContent(request.getParameter("content"));
				wxModule.setLink(request.getParameter("link"));
				wxModule.setListLink(request.getParameter("listLink"));
				wxModule.setLinkType(request.getParameter("linkType"));
				wxModule.setIdField(request.getParameter("idField"));
				wxModule.setSubjectField(request.getParameter("subjectField"));
				wxModule.setModuleId(request.getParameter("moduleId"));
				wxModule.setModuleName(request.getParameter("moduleName"));
				wxModule.setSql(request.getParameter("sql"));
				wxModule.setJson(request.getParameter("json"));
				wxModule.setLocked(RequestUtils.getInt(request, "locked"));
				wxModule.setSort(RequestUtils.getInt(request, "sort"));
				wxModuleService.save(wxModule);
			}
		}
		return this.list(request, modelMap);
	}

}
