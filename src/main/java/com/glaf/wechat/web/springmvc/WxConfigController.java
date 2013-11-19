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

@Controller("/wx/wxConfig")
@RequestMapping("/wx/wxConfig")
public class WxConfigController {
	protected static final Log logger = LogFactory
			.getLog(WxConfigController.class);

	protected WxConfigService wxConfigService;

	public WxConfigController() {

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
					WxConfig wxConfig = wxConfigService.getWxConfig(Long
							.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxConfig != null
							&& (StringUtils.equals(wxConfig.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxConfigService.save(wxConfig);
					}
				}
			}
		} else if (id != null) {
			WxConfig wxConfig = wxConfigService.getWxConfig(Long.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */
			if (wxConfig != null
					&& (StringUtils.equals(wxConfig.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxConfigService.save(wxConfig);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/detail")
	public byte[] detail(HttpServletRequest request) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxConfig wxConfig = wxConfigService.getWxConfig(RequestUtils.getLong(
				request, "id"));
		if (wxConfig != null
				&& (StringUtils.equals(wxConfig.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			JSONObject rowJSON = wxConfig.toJsonObject();
			return rowJSON.toJSONString().getBytes("UTF-8");
		}
		return null;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");
		WxConfig wxConfig = wxConfigService.getWxConfig(RequestUtils.getLong(
				request, "id"));
		if (wxConfig != null
				&& (StringUtils.equals(wxConfig.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxConfig", wxConfig);
			JSONObject rowJSON = wxConfig.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		} else {
			wxConfig = wxConfigService.getWxConfigByUser(loginContext
					.getActorId());
			if (wxConfig != null) {
				request.setAttribute("wxConfig", wxConfig);
				JSONObject rowJSON = wxConfig.toJsonObject();
				request.setAttribute("x_json", rowJSON.toJSONString());
			}
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxConfig.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/config/edit", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxConfigQuery query = new WxConfigQuery();
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
		int total = wxConfigService.getWxConfigCountByQueryCriteria(query);
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

			List<WxConfig> list = wxConfigService.getWxConfigsByQueryCriteria(
					start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxConfig wxConfig : list) {
					JSONObject rowJSON = wxConfig.toJsonObject();
					rowJSON.put("id", wxConfig.getId());
					rowJSON.put("wxConfigId", wxConfig.getId());
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

		return new ModelAndView("/wx/config/list", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxConfig.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/config/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxConfig wxConfig = new WxConfig();
		Tools.populate(wxConfig, params);

		wxConfig.setCallBackUrl(request.getParameter("callBackUrl"));
		wxConfig.setToken(request.getParameter("token"));
		wxConfig.setAccountId(RequestUtils.getLong(request, "accountId"));
		wxConfig.setWxAppId(request.getParameter("wxAppId"));
		wxConfig.setWxAppSecret(request.getParameter("wxAppSecret"));
		wxConfig.setApiStatus(request.getParameter("apiStatus"));
		wxConfig.setDefaultReply(request.getParameter("defaultReply"));

		wxConfig.setCreateBy(actorId);

		wxConfigService.save(wxConfig);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxConfig")
	public byte[] saveWxConfig(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxConfig wxConfig = new WxConfig();
		try {
			Tools.populate(wxConfig, params);
			wxConfig.setCallBackUrl(request.getParameter("callBackUrl"));
			wxConfig.setToken(request.getParameter("token"));
			wxConfig.setAccountId(RequestUtils.getLong(request, "accountId"));
			wxConfig.setWxAppId(request.getParameter("wxAppId"));
			wxConfig.setWxAppSecret(request.getParameter("wxAppSecret"));
			wxConfig.setApiStatus(request.getParameter("apiStatus"));
			wxConfig.setDefaultReply(request.getParameter("defaultReply"));
			wxConfig.setCreateBy(actorId);
			this.wxConfigService.save(wxConfig);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxConfigService(WxConfigService wxConfigService) {
		this.wxConfigService = wxConfigService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxConfig wxConfig = wxConfigService.getWxConfig(RequestUtils.getLong(
				request, "id"));
		if (wxConfig != null
				&& (StringUtils.equals(wxConfig.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxConfig.setCallBackUrl(request.getParameter("callBackUrl"));
			wxConfig.setToken(request.getParameter("token"));
			wxConfig.setAccountId(RequestUtils.getLong(request, "accountId"));
			wxConfig.setWxAppId(request.getParameter("wxAppId"));
			wxConfig.setWxAppSecret(request.getParameter("wxAppSecret"));
			wxConfig.setApiStatus(request.getParameter("apiStatus"));
			wxConfig.setDefaultReply(request.getParameter("defaultReply"));
			wxConfig.setLastUpdateBy(loginContext.getActorId());

			wxConfigService.save(wxConfig);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		WxConfig wxConfig = wxConfigService.getWxConfig(RequestUtils.getLong(
				request, "id"));
		request.setAttribute("wxConfig", wxConfig);
		JSONObject rowJSON = wxConfig.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxConfig.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/config/view");
	}

}
