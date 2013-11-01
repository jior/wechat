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

package com.glaf.apps.vote.web.springmvc;

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
import com.glaf.apps.vote.domain.*;
import com.glaf.apps.vote.query.*;
import com.glaf.apps.vote.service.*;

@Controller("/wx/wxVote")
@RequestMapping("/wx/wxVote")
public class WxVoteController {
	protected static final Log logger = LogFactory
			.getLog(WxVoteController.class);

	protected WxVoteService wxVoteService;

	public WxVoteController() {

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
					WxVote wxVote = wxVoteService.getWxVote(Long.valueOf(x));
					if (wxVote != null
							&& (StringUtils.equals(wxVote.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxVoteService.deleteById(wxVote.getId());
					}
				}
			}
		} else if (id != null) {
			WxVote wxVote = wxVoteService.getWxVote(Long.valueOf(id));
			if (wxVote != null
					&& (StringUtils.equals(wxVote.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxVoteService.deleteById(wxVote.getId());
			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);

		RequestUtils.setRequestParameterToAttribute(request);

		WxVote wxVote = wxVoteService.getWxVote(RequestUtils.getLong(request,
				"id"));
		if (wxVote != null
				&& (StringUtils.equals(wxVote.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxVote", wxVote);
			JSONObject rowJSON = wxVote.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxVote.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/vote/edit", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxVoteQuery query = new WxVoteQuery();
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
		int total = wxVoteService.getWxVoteCountByQueryCriteria(query);
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

			List<WxVote> list = wxVoteService.getWxVotesByQueryCriteria(start,
					limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxVote wxVote : list) {
					JSONObject rowJSON = wxVote.toJsonObject();
					rowJSON.put("id", wxVote.getId());
					rowJSON.put("voteId", wxVote.getId());
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

		return new ModelAndView("/wx/vote/list", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxVote.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/vote/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);

		WxVote wxVote = new WxVote();
		Tools.populate(wxVote, params);

		wxVote.setTitle(request.getParameter("title"));
		wxVote.setContent(request.getParameter("content"));
		wxVote.setIcon(request.getParameter("icon"));
		wxVote.setKeywords(request.getParameter("keywords"));
		wxVote.setStatus(RequestUtils.getInt(request, "status"));
		wxVote.setShowIconFlag(RequestUtils.getInt(request, "showIconFlag"));
		wxVote.setSignFlag(RequestUtils.getInt(request, "signFlag"));
		wxVote.setMultiFlag(RequestUtils.getInt(request, "multiFlag"));
		wxVote.setLimitFlag(RequestUtils.getInt(request, "limitFlag"));
		wxVote.setLimitTimeInterval(RequestUtils.getInt(request,
				"limitTimeInterval"));
		wxVote.setResultFlag(RequestUtils.getInt(request, "resultFlag"));
		wxVote.setStartDate(RequestUtils.getDate(request, "startDate"));
		wxVote.setEndDate(RequestUtils.getDate(request, "endDate"));

		wxVote.setCreateBy(actorId);

		wxVoteService.save(wxVote);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxVote")
	public byte[] saveWxVote(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		logger.debug("params:" + params);
		WxVote wxVote = new WxVote();
		try {
			Tools.populate(wxVote, params);
			wxVote.setTitle(request.getParameter("title"));
			wxVote.setContent(request.getParameter("content"));
			wxVote.setIcon(request.getParameter("icon"));
			wxVote.setKeywords(request.getParameter("keywords"));
			wxVote.setStatus(RequestUtils.getInt(request, "status"));
			wxVote.setShowIconFlag(RequestUtils.getInt(request, "showIconFlag"));
			wxVote.setSignFlag(RequestUtils.getInt(request, "signFlag"));
			wxVote.setMultiFlag(RequestUtils.getInt(request, "multiFlag"));
			wxVote.setLimitFlag(RequestUtils.getInt(request, "limitFlag"));
			wxVote.setLimitTimeInterval(RequestUtils.getInt(request,
					"limitTimeInterval"));
			wxVote.setResultFlag(RequestUtils.getInt(request, "resultFlag"));
			wxVote.setStartDate(RequestUtils.getDate(request, "startDate"));
			wxVote.setEndDate(RequestUtils.getDate(request, "endDate"));

			wxVote.setCreateBy(actorId);

			Map<Integer, WxVoteItem> dataMap = new HashMap<Integer, WxVoteItem>();
			String[] titleArray = request.getParameterValues("item_title");
			if (titleArray != null && titleArray.length > 0) {
				int index = 0;
				for (String t : titleArray) {
					WxVoteItem item = new WxVoteItem();
					item.setName(t);
					item.setValue(String.valueOf(index));
					wxVote.addItem(item);
					dataMap.put(index, item);
					index++;
				}
			}

			String[] sortArray = request.getParameterValues("item_sort");
			if (sortArray != null && sortArray.length > 0) {
				int index = 0;
				for (String sort : sortArray) {
					WxVoteItem item = dataMap.get(index++);
					if (item != null && StringUtils.isNotEmpty(sort)
							&& StringUtils.isNumeric(sort)) {
						item.setSort(Integer.parseInt(sort));
					}
				}
			}

			this.wxVoteService.save(wxVote);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxVoteService(WxVoteService wxVoteService) {
		this.wxVoteService = wxVoteService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);

		WxVote wxVote = wxVoteService.getWxVote(RequestUtils.getLong(request,
				"id"));

		if (wxVote != null
				&& (StringUtils.equals(wxVote.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxVote.setTitle(request.getParameter("title"));
			wxVote.setContent(request.getParameter("content"));
			wxVote.setIcon(request.getParameter("icon"));
			wxVote.setKeywords(request.getParameter("keywords"));
			wxVote.setStatus(RequestUtils.getInt(request, "status"));
			wxVote.setShowIconFlag(RequestUtils.getInt(request, "showIconFlag"));
			wxVote.setSignFlag(RequestUtils.getInt(request, "signFlag"));
			wxVote.setMultiFlag(RequestUtils.getInt(request, "multiFlag"));
			wxVote.setLimitFlag(RequestUtils.getInt(request, "limitFlag"));
			wxVote.setLimitTimeInterval(RequestUtils.getInt(request,
					"limitTimeInterval"));
			wxVote.setResultFlag(RequestUtils.getInt(request, "resultFlag"));
			wxVote.setStartDate(RequestUtils.getDate(request, "startDate"));
			wxVote.setEndDate(RequestUtils.getDate(request, "endDate"));

			wxVoteService.save(wxVote);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		WxVote wxVote = wxVoteService.getWxVote(RequestUtils.getLong(request,
				"id"));
		if (wxVote != null
				&& (StringUtils.equals(wxVote.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxVote", wxVote);
			JSONObject rowJSON = wxVote.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxVote.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/vote/view");
	}

}
