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

package com.glaf.apps.member.web.springmvc;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.glaf.apps.member.domain.WxMember;
import com.glaf.apps.member.query.WxMemberQuery;
import com.glaf.apps.member.service.WxMemberService;

@Controller("/wx/wxMember")
@RequestMapping("/wx/wxMember")
public class WxMemberController {
	protected static final Log logger = LogFactory
			.getLog(WxMemberController.class);

	protected WxMemberService wxMemberService;

	public WxMemberController() {

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
					WxMember wxMember = wxMemberService.getWxMember(Long
							.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxMember != null
							&& (StringUtils.equals(wxMember.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxMemberService.save(wxMember);
					}
				}
			}
		} else if (id != null) {
			WxMember wxMember = wxMemberService.getWxMember(Long.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */
			if (wxMember != null
					&& (StringUtils.equals(wxMember.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxMemberService.save(wxMember);
			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");

		WxMember wxMember = wxMemberService.getWxMember(RequestUtils.getLong(
				request, "id"));
		if (wxMember != null
				&& (StringUtils.equals(wxMember.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxMember", wxMember);
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxMember.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/member/edit", modelMap);
	}

	@RequestMapping("/json/{accountId}")
	@ResponseBody
	public byte[] json(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMemberQuery query = new WxMemberQuery();
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
		int total = wxMemberService.getWxMemberCountByQueryCriteria(query);
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

			List<WxMember> list = wxMemberService.getWxMembersByQueryCriteria(
					start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxMember wxMember : list) {
					JSONObject rowJSON = wxMember.toJsonObject();
					rowJSON.put("id", wxMember.getId());
					rowJSON.put("wxMemberId", wxMember.getId());
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

		return new ModelAndView("/wx/member/list", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxMember.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/member/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMember wxMember = new WxMember();
		Tools.populate(wxMember, params);
		Long accountId = RequestUtils.getLong(request, "accountId");
		wxMember.setAccountId(accountId);
		wxMember.setCardNo(request.getParameter("cardNo"));
		wxMember.setName(request.getParameter("name"));
		wxMember.setTelephone(request.getParameter("telephone"));
		wxMember.setMobile(request.getParameter("mobile"));
		wxMember.setMail(request.getParameter("mail"));
		wxMember.setQq(request.getParameter("qq"));
		wxMember.setAddress(request.getParameter("address"));
		wxMember.setBalance(RequestUtils.getDouble(request, "balance"));
		wxMember.setStatus(RequestUtils.getInt(request, "status"));

		wxMember.setCreateBy(actorId);

		wxMemberService.save(wxMember);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxMember")
	public byte[] saveWxMember(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMember wxMember = new WxMember();
		try {
			Tools.populate(wxMember, params);
			Long accountId = RequestUtils.getLong(request, "accountId");
			wxMember.setAccountId(accountId);
			wxMember.setCardNo(request.getParameter("cardNo"));
			wxMember.setName(request.getParameter("name"));
			wxMember.setTelephone(request.getParameter("telephone"));
			wxMember.setMobile(request.getParameter("mobile"));
			wxMember.setMail(request.getParameter("mail"));
			wxMember.setQq(request.getParameter("qq"));
			wxMember.setAddress(request.getParameter("address"));
			wxMember.setBalance(RequestUtils.getDouble(request, "balance"));
			wxMember.setStatus(RequestUtils.getInt(request, "status"));
			wxMember.setCreateBy(actorId);
			this.wxMemberService.save(wxMember);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxMemberService(WxMemberService wxMemberService) {
		this.wxMemberService = wxMemberService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMember wxMember = wxMemberService.getWxMember(RequestUtils.getLong(
				request, "id"));
		if (wxMember != null
				&& (StringUtils.equals(wxMember.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxMember.setCardNo(request.getParameter("cardNo"));
			wxMember.setName(request.getParameter("name"));
			wxMember.setTelephone(request.getParameter("telephone"));
			wxMember.setMobile(request.getParameter("mobile"));
			wxMember.setMail(request.getParameter("mail"));
			wxMember.setQq(request.getParameter("qq"));
			wxMember.setAddress(request.getParameter("address"));
			wxMember.setBalance(RequestUtils.getDouble(request, "balance"));
			wxMember.setStatus(RequestUtils.getInt(request, "status"));
			wxMember.setLastUpdateBy(loginContext.getActorId());

			wxMemberService.save(wxMember);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		WxMember wxMember = wxMemberService.getWxMemberByUUID(RequestUtils
				.getString(request, "uuid"));
		request.setAttribute("wxMember", wxMember);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxMember.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/member/view");
	}

}
