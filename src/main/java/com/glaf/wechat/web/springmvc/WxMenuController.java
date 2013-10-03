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

@Controller("/wx/wxMenu")
@RequestMapping("/wx/wxMenu")
public class WxMenuController {
	protected static final Log logger = LogFactory
			.getLog(WxMenuController.class);

	protected WxMenuService wxMenuService;

	public WxMenuController() {

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
					WxMenu wxMenu = wxMenuService.getWxMenu(Long.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxMenu != null
							&& (StringUtils.equals(wxMenu.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxMenuService.save(wxMenu);
					}
				}
			}
		} else if (id != null) {
			WxMenu wxMenu = wxMenuService.getWxMenu(Long.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */

			if (wxMenu != null
					&& (StringUtils.equals(wxMenu.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxMenuService.save(wxMenu);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/detail")
	public byte[] detail(HttpServletRequest request) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxMenu wxMenu = wxMenuService.getWxMenu(RequestUtils.getLong(request,
				"id"));
		if (wxMenu != null
				&& (StringUtils.equals(wxMenu.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			JSONObject rowJSON = wxMenu.toJsonObject();
			return rowJSON.toJSONString().getBytes("UTF-8");
		}
		return null;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");

		WxMenu wxMenu = wxMenuService.getWxMenu(RequestUtils.getLong(request,
				"id"));
		if (wxMenu != null
				&& (StringUtils.equals(wxMenu.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxMenu", wxMenu);
			JSONObject rowJSON = wxMenu.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxMenu.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/menu/edit", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMenuQuery query = new WxMenuQuery();
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
		int total = wxMenuService.getWxMenuCountByQueryCriteria(query);
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

			List<WxMenu> list = wxMenuService.getWxMenusByQueryCriteria(start,
					limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxMenu wxMenu : list) {
					JSONObject rowJSON = wxMenu.toJsonObject();
					rowJSON.put("id", wxMenu.getId());
					rowJSON.put("wxMenuId", wxMenu.getId());
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

		return new ModelAndView("/wx/menu/list", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxMenu.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/menu/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMenu wxMenu = new WxMenu();
		Tools.populate(wxMenu, params);

		wxMenu.setParentId(RequestUtils.getLong(request, "parentId"));
		wxMenu.setName(request.getParameter("name"));
		wxMenu.setType(request.getParameter("type"));
		wxMenu.setKey(request.getParameter("key"));
		wxMenu.setUrl(request.getParameter("url"));
		wxMenu.setSort(RequestUtils.getInt(request, "sort"));

		wxMenu.setCreateBy(actorId);

		wxMenuService.save(wxMenu);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxMenu")
	public byte[] saveWxMenu(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMenu wxMenu = new WxMenu();
		try {
			Tools.populate(wxMenu, params);
			wxMenu.setParentId(RequestUtils.getLong(request, "parentId"));
			wxMenu.setName(request.getParameter("name"));
			wxMenu.setType(request.getParameter("type"));
			wxMenu.setKey(request.getParameter("key"));
			wxMenu.setUrl(request.getParameter("url"));
			wxMenu.setSort(RequestUtils.getInt(request, "sort"));
			wxMenu.setCreateBy(actorId);
			this.wxMenuService.save(wxMenu);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxMenuService(WxMenuService wxMenuService) {
		this.wxMenuService = wxMenuService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMenu wxMenu = wxMenuService.getWxMenu(RequestUtils.getLong(request,
				"id"));
		if (wxMenu != null
				&& (StringUtils.equals(wxMenu.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxMenu.setParentId(RequestUtils.getLong(request, "parentId"));
			wxMenu.setName(request.getParameter("name"));
			wxMenu.setType(request.getParameter("type"));
			wxMenu.setKey(request.getParameter("key"));
			wxMenu.setUrl(request.getParameter("url"));
			wxMenu.setSort(RequestUtils.getInt(request, "sort"));
			wxMenu.setLastUpdateBy(loginContext.getActorId());

			wxMenuService.save(wxMenu);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		WxMenu wxMenu = wxMenuService.getWxMenu(RequestUtils.getLong(request,
				"id"));
		request.setAttribute("wxMenu", wxMenu);
		JSONObject rowJSON = wxMenu.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxMenu.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/menu/view");
	}

}
