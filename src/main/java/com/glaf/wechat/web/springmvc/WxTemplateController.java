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
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import com.alibaba.fastjson.*;

import com.glaf.core.config.ViewProperties;
import com.glaf.core.identity.*;
import com.glaf.core.security.*;
import com.glaf.core.util.*;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.*;

@Controller("/wx/wxTemplate")
@RequestMapping("/wx/wxTemplate")
public class WxTemplateController {
	protected static final Log logger = LogFactory
			.getLog(WxTemplateController.class);

	protected WxTemplateService wxTemplateService;

	public WxTemplateController() {

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
					WxTemplate wxTemplate = wxTemplateService
							.getWxTemplate(Long.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxTemplate != null
							&& (StringUtils.equals(wxTemplate.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxTemplateService.save(wxTemplate);
					}
				}
			}
		} else if (id != null) {
			WxTemplate wxTemplate = wxTemplateService.getWxTemplate(Long
					.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */
			if (wxTemplate != null
					&& (StringUtils.equals(wxTemplate.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxTemplateService.save(wxTemplate);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/detail")
	public byte[] detail(HttpServletRequest request) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxTemplate wxTemplate = wxTemplateService.getWxTemplate(RequestUtils
				.getLong(request, "id"));
		if (wxTemplate != null
				&& (StringUtils.equals(wxTemplate.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			JSONObject rowJSON = wxTemplate.toJsonObject();
			return rowJSON.toJSONString().getBytes("UTF-8");
		}
		return null;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");

		WxTemplate wxTemplate = wxTemplateService.getWxTemplate(RequestUtils
				.getLong(request, "id"));
		if (wxTemplate != null
				&& (StringUtils.equals(wxTemplate.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxTemplate", wxTemplate);
			JSONObject rowJSON = wxTemplate.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxTemplate.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/template/edit", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxTemplateQuery query = new WxTemplateQuery();
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
		int total = wxTemplateService.getWxTemplateCountByQueryCriteria(query);
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

			List<WxTemplate> list = wxTemplateService
					.getWxTemplatesByQueryCriteria(start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxTemplate wxTemplate : list) {
					JSONObject rowJSON = wxTemplate.toJsonObject();
					rowJSON.put("id", wxTemplate.getId());
					rowJSON.put("wxTemplateId", wxTemplate.getId());
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

		return new ModelAndView("/wx/template/list", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxTemplate.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/template/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxTemplate wxTemplate = new WxTemplate();
		Tools.populate(wxTemplate, params);

		wxTemplate.setSkinId(request.getParameter("skinId"));
		wxTemplate.setSkinImage(request.getParameter("skinImage"));
		wxTemplate.setType(request.getParameter("type"));
		wxTemplate.setUrl(request.getParameter("url"));
		wxTemplate.setDefaultFlag(RequestUtils.getInt(request, "defaultFlag"));
		wxTemplate.setCreateBy(actorId);

		wxTemplateService.save(wxTemplate);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxTemplate")
	public byte[] saveWxTemplate(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxTemplate wxTemplate = new WxTemplate();
		try {
			Tools.populate(wxTemplate, params);
			wxTemplate.setSkinId(request.getParameter("skinId"));
			wxTemplate.setSkinImage(request.getParameter("skinImage"));
			wxTemplate.setType(request.getParameter("type"));
			wxTemplate.setUrl(request.getParameter("url"));
			wxTemplate.setDefaultFlag(RequestUtils.getInt(request,
					"defaultFlag"));
			wxTemplate.setCreateBy(actorId);
			this.wxTemplateService.save(wxTemplate);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxTemplateService(WxTemplateService wxTemplateService) {
		this.wxTemplateService = wxTemplateService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxTemplate wxTemplate = wxTemplateService.getWxTemplate(RequestUtils
				.getLong(request, "id"));
		if (wxTemplate != null
				&& (StringUtils.equals(wxTemplate.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxTemplate.setSkinId(request.getParameter("skinId"));
			wxTemplate.setSkinImage(request.getParameter("skinImage"));
			wxTemplate.setType(request.getParameter("type"));
			wxTemplate.setUrl(request.getParameter("url"));
			wxTemplate.setDefaultFlag(RequestUtils.getInt(request,
					"defaultFlag"));
			wxTemplate.setLastUpdateBy(loginContext.getActorId());
			wxTemplateService.save(wxTemplate);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		WxTemplate wxTemplate = wxTemplateService.getWxTemplate(RequestUtils
				.getLong(request, "id"));
		request.setAttribute("wxTemplate", wxTemplate);
		JSONObject rowJSON = wxTemplate.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxTemplate.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/template/view");
	}

}
