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

@Controller("/wx/wxMessage")
@RequestMapping("/wx/wxMessage")
public class WxMessageController {
	protected static final Log logger = LogFactory
			.getLog(WxMessageController.class);

	protected WxMessageService wxMessageService;

	public WxMessageController() {

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
					WxMessage wxMessage = wxMessageService.getWxMessage(Long
							.valueOf(x));
					if (wxMessage != null
							&& (StringUtils.equals(wxMessage.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxMessageService.deleteById(wxMessage.getId());
					}
				}
			}
		} else if (id != null) {
			WxMessage wxMessage = wxMessageService.getWxMessage(Long
					.valueOf(id));
			if (wxMessage != null
					&& (StringUtils.equals(wxMessage.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxMessageService.deleteById(wxMessage.getId());
			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");
		WxMessage wxMessage = wxMessageService.getWxMessage(RequestUtils
				.getLong(request, "id"));
		if (wxMessage != null
				&& (StringUtils.equals(wxMessage.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxMessage", wxMessage);
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

		String x_view = ViewProperties.getString("wxMessage.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/message/edit", modelMap);
	}

	@RequestMapping("/json/{accountId}")
	@ResponseBody
	public byte[] json(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMessageQuery query = new WxMessageQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);

		String actorId = loginContext.getActorId();
		query.createBy(actorId);
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
		int total = wxMessageService.getWxMessageCountByQueryCriteria(query);
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

			List<WxMessage> list = wxMessageService
					.getWxMessagesByQueryCriteria(start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxMessage wxMessage : list) {
					JSONObject rowJSON = wxMessage.toJsonObject();
					rowJSON.put("id", wxMessage.getId());
					rowJSON.put("wxMessageId", wxMessage.getId());
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

		return new ModelAndView("/wx/message/list", modelMap);
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
		String x_view = ViewProperties.getString("wxMessage.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/message/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMessage wxMessage = new WxMessage();
		Tools.populate(wxMessage, params);
		Long accountId = RequestUtils.getLong(request, "accountId");
		wxMessage.setName(request.getParameter("name"));
		wxMessage.setMobile(request.getParameter("mobile"));
		wxMessage.setTitle(request.getParameter("title"));
		wxMessage.setContent(request.getParameter("content"));
		wxMessage.setCreateBy(actorId);
		wxMessage.setAccountId(accountId);

		wxMessageService.save(wxMessage);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxMessage")
	public byte[] saveWxMessage(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxMessage wxMessage = new WxMessage();
		try {
			Tools.populate(wxMessage, params);
			wxMessage.setName(request.getParameter("name"));
			wxMessage.setMobile(request.getParameter("mobile"));
			wxMessage.setTitle(request.getParameter("title"));
			wxMessage.setContent(request.getParameter("content"));
			wxMessage.setCreateBy(actorId);
			wxMessage.setAccountId(accountId);
			this.wxMessageService.save(wxMessage);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxMessageService(WxMessageService wxMessageService) {
		this.wxMessageService = wxMessageService;
	}

	@RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id,
			HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMessage wxMessage = wxMessageService.getWxMessage(id);
		if (wxMessage != null
				&& (StringUtils.equals(wxMessage.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxMessage.setName(request.getParameter("name"));
			wxMessage.setMobile(request.getParameter("mobile"));
			wxMessage.setTitle(request.getParameter("title"));
			wxMessage.setContent(request.getParameter("content"));
			wxMessage.setLastUpdateBy(loginContext.getActorId());

			wxMessageService.save(wxMessage);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id,
			HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		WxMessage wxMessage = wxMessageService.getWxMessage(id);
		request.setAttribute("wxMessage", wxMessage);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxMessage.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/message/view");
	}

}
