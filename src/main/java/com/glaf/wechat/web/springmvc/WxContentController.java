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
import com.glaf.core.base.BaseTree;
import com.glaf.core.base.TreeModel;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.identity.*;
import com.glaf.core.security.*;
import com.glaf.core.tree.helper.TreeHelper;
import com.glaf.core.util.*;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.*;

@Controller("/wx/wxContent")
@RequestMapping("/wx/wxContent")
public class WxContentController {
	protected static final Log logger = LogFactory
			.getLog(WxContentController.class);

	protected WxCategoryService wxCategoryService;

	protected WxContentService wxContentService;

	public WxContentController() {

	}

	@RequestMapping("/choose")
	public ModelAndView choose(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxContent.choose");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/content/choose_contents", modelMap);
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
					WxContent wxContent = wxContentService.getWxContent(Long
							.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxContent != null
							&& (StringUtils.equals(wxContent.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxContentService.deleteById(wxContent.getId());
					}
				}
			}
		} else if (id != null) {
			WxContent wxContent = wxContentService.getWxContent(Long
					.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */
			if (wxContent != null
					&& (StringUtils.equals(wxContent.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxContentService.deleteById(wxContent.getId());
			}
		}
	}

	@ResponseBody
	@RequestMapping("/detail")
	public byte[] detail(HttpServletRequest request) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxContent wxContent = wxContentService.getWxContent(RequestUtils
				.getLong(request, "id"));
		if (wxContent != null
				&& (StringUtils.equals(wxContent.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			JSONObject rowJSON = wxContent.toJsonObject();
			return rowJSON.toJSONString().getBytes("UTF-8");
		}
		return null;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);

		WxContent wxContent = wxContentService.getWxContent(RequestUtils
				.getLong(request, "id"));
		if (wxContent != null
				&& (StringUtils.equals(wxContent.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxContent", wxContent);
			JSONObject rowJSON = wxContent.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxContent.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/content/edit", modelMap);
	}

	@RequestMapping("/editPPT")
	public ModelAndView editPPT(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);

		WxContent wxContent = wxContentService.getWxContent(RequestUtils
				.getLong(request, "id"));
		if (wxContent != null
				&& (StringUtils.equals(wxContent.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxContent", wxContent);
			JSONObject rowJSON = wxContent.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxContent.editPPT");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/content/editPPT", modelMap);
	}

	@RequestMapping("/indexPPT")
	public ModelAndView indexPPT(HttpServletRequest request, ModelMap modelMap) {
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

		return new ModelAndView("/wx/content/indexPPT", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxContentQuery query = new WxContentQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);

		Long categoryId = RequestUtils.getLong(request, "categoryId", 0);
		if (categoryId > 0) {
			query.categoryId(categoryId);
		}

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
		int total = wxContentService.getWxContentCountByQueryCriteria(query);
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

			List<WxContent> list = wxContentService
					.getWxContentsByQueryCriteria(start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxContent wxContent : list) {
					JSONObject rowJSON = wxContent.toJsonObject();
					rowJSON.put("id", wxContent.getId());
					rowJSON.put("wxContentId", wxContent.getId());
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

		return new ModelAndView("/wx/content/list", modelMap);
	}

	@RequestMapping("/ppt")
	public ModelAndView ppt(HttpServletRequest request, ModelMap modelMap) {
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

		return new ModelAndView("/wx/content/ppt", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxContent.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/content/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxContent wxContent = new WxContent();
		Tools.populate(wxContent, params);

		wxContent.setCategoryId(RequestUtils.getLong(request, "categoryId"));
		wxContent.setTitle(request.getParameter("title"));
		wxContent.setContent(request.getParameter("content"));
		wxContent.setDetailShowCover(request.getParameter("detailShowCover"));
		wxContent.setAuthor(request.getParameter("author"));
		wxContent.setStatus(RequestUtils.getInt(request, "status"));
		wxContent.setPriority(RequestUtils.getInt(request, "priority"));
		wxContent.setType(request.getParameter("type"));
		wxContent.setKeywords(request.getParameter("keywords"));
		wxContent.setKeywordsMatchType(request
				.getParameter("keywordsMatchType"));
		wxContent.setSort(RequestUtils.getInt(request, "sort"));
		wxContent.setRelationIds(request.getParameter("relationIds"));
		wxContent.setRecommendationIds(request
				.getParameter("recommendationIds"));
		wxContent.setSummary(request.getParameter("summary"));
		wxContent.setIcon(request.getParameter("icon"));
		wxContent.setBigIcon(request.getParameter("bigIcon"));
		wxContent.setSmallIcon(request.getParameter("smallIcon"));
		wxContent.setUrl(request.getParameter("url"));
		wxContent.setCreateBy(actorId);

		wxContentService.save(wxContent);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxContent")
	public byte[] saveWxContent(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxContent wxContent = new WxContent();
		try {
			Tools.populate(wxContent, params);
			wxContent
					.setCategoryId(RequestUtils.getLong(request, "categoryId"));
			wxContent.setTitle(request.getParameter("title"));
			wxContent.setContent(request.getParameter("content"));
			wxContent.setDetailShowCover(request
					.getParameter("detailShowCover"));
			wxContent.setAuthor(request.getParameter("author"));
			wxContent.setStatus(RequestUtils.getInt(request, "status"));
			wxContent.setPriority(RequestUtils.getInt(request, "priority"));
			wxContent.setType(request.getParameter("type"));
			wxContent.setKeywords(request.getParameter("keywords"));
			wxContent.setKeywordsMatchType(request
					.getParameter("keywordsMatchType"));
			wxContent.setSort(RequestUtils.getInt(request, "sort"));
			wxContent.setRelationIds(request.getParameter("relationIds"));
			wxContent.setRecommendationIds(request
					.getParameter("recommendationIds"));
			wxContent.setSummary(request.getParameter("summary"));
			wxContent.setIcon(request.getParameter("icon"));
			wxContent.setBigIcon(request.getParameter("bigIcon"));
			wxContent.setSmallIcon(request.getParameter("smallIcon"));
			wxContent.setUrl(request.getParameter("url"));
			wxContent.setCreateBy(actorId);
			this.wxContentService.save(wxContent);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxCategoryService(WxCategoryService wxCategoryService) {
		this.wxCategoryService = wxCategoryService;
	}

	@javax.annotation.Resource
	public void setWxContentService(WxContentService wxContentService) {
		this.wxContentService = wxContentService;
	}

	@ResponseBody
	@RequestMapping("/treeJson")
	public byte[] treeJson(HttpServletRequest request) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		String selecteds = request.getParameter("selecteds");
		List<String> checkIds = new ArrayList<String>();
		if (StringUtils.isNotEmpty(selecteds)) {
			checkIds = StringTools.split(selecteds);
		}

		String type = request.getParameter("type");
		JSONObject result = new JSONObject();
		List<WxCategory> categories = wxCategoryService.getCategoryList(
				loginContext.getActorId(), type);
		if (categories != null && !categories.isEmpty()) {
			Map<Long, TreeModel> treeMap = new HashMap<Long, TreeModel>();
			List<TreeModel> treeModels = new ArrayList<TreeModel>();
			List<Long> categoryIds = new ArrayList<Long>();
			for (WxCategory category : categories) {
				TreeModel tree = new BaseTree();
				tree.setId(category.getId());
				tree.setParentId(category.getParentId());
				tree.setCode(category.getCode());
				tree.setName(category.getName());
				tree.setSortNo(category.getSort());
				tree.setDescription(category.getDesc());
				tree.setCreateBy(category.getCreateBy());
				tree.setIconCls("tree_folder");
				tree.setTreeId(category.getTreeId());
				tree.setUrl(category.getUrl());
				treeModels.add(tree);
				categoryIds.add(category.getId());
				treeMap.put(category.getId(), tree);
			}
			WxContentQuery query = new WxContentQuery();
			query.categoryIds(categoryIds);
			query.createBy(loginContext.getActorId());
			List<WxContent> contents = wxContentService.list(query);
			if (contents != null && !contents.isEmpty()) {
				for (WxContent content : contents) {
					TreeModel parent = treeMap.get(content.getCategoryId());
					TreeModel tree = new BaseTree();
					tree.setId(content.getId());
					tree.setParentId(parent.getId());
					tree.setName(content.getTitle());
					tree.setSortNo(content.getSort());
					tree.setCreateBy(content.getCreateBy());
					tree.setIconCls("tree_leaf");
					tree.setUrl(content.getUrl());
					if (checkIds.contains(String.valueOf(content.getId()))) {
						tree.setChecked(true);
					}
					treeModels.add(tree);
				}
			}
			logger.debug("treeModels:" + treeModels.size());
			TreeHelper treeHelper = new TreeHelper();
			JSONArray jsonArray = treeHelper.getTreeJSONArray(treeModels);
			logger.debug(jsonArray.toJSONString());
			return jsonArray.toJSONString().getBytes("UTF-8");
		}
		return result.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping("/treeList")
	public ModelAndView treeList(HttpServletRequest request, ModelMap modelMap) {
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

		return new ModelAndView("/wx/content/treeList", modelMap);
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxContent wxContent = wxContentService.getWxContent(RequestUtils
				.getLong(request, "id"));
		if (wxContent != null
				&& (StringUtils.equals(wxContent.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxContent
					.setCategoryId(RequestUtils.getLong(request, "categoryId"));
			wxContent.setTitle(request.getParameter("title"));
			wxContent.setContent(request.getParameter("content"));
			wxContent.setDetailShowCover(request
					.getParameter("detailShowCover"));
			wxContent.setAuthor(request.getParameter("author"));
			wxContent.setStatus(RequestUtils.getInt(request, "status"));
			wxContent.setPriority(RequestUtils.getInt(request, "priority"));
			wxContent.setType(request.getParameter("type"));
			wxContent.setKeywords(request.getParameter("keywords"));
			wxContent.setKeywordsMatchType(request
					.getParameter("keywordsMatchType"));
			wxContent.setSort(RequestUtils.getInt(request, "sort"));
			wxContent.setRelationIds(request.getParameter("relationIds"));
			wxContent.setRecommendationIds(request
					.getParameter("recommendationIds"));
			wxContent.setSummary(request.getParameter("summary"));
			wxContent.setIcon(request.getParameter("icon"));
			wxContent.setBigIcon(request.getParameter("bigIcon"));
			wxContent.setSmallIcon(request.getParameter("smallIcon"));
			wxContent.setUrl(request.getParameter("url"));
			wxContent.setLastUpdateBy(loginContext.getActorId());

			wxContentService.save(wxContent);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		WxContent wxContent = wxContentService.getWxContent(RequestUtils
				.getLong(request, "id"));
		request.setAttribute("wxContent", wxContent);
		JSONObject rowJSON = wxContent.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxContent.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/content/view");
	}

}
