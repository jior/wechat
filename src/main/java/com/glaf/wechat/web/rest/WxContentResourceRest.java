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

package com.glaf.wechat.web.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.glaf.core.util.PageResult;
import com.glaf.core.util.ParamUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.ResponseUtils;
import com.glaf.core.util.StringTools;
import com.glaf.core.util.Tools;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.WxContentService;

@Controller
@Path("/rs/wx/wxContent")
public class WxContentResourceRest {
	protected static final Log logger = LogFactory
			.getLog(WxContentResourceRest.class);

	protected WxContentService wxContentService;

	@POST
	@Path("/deleteAll")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] deleteAll(@Context HttpServletRequest request)
			throws IOException {
		String rowIds = request.getParameter("ids");
		if (rowIds != null) {
			List<Long> ids = StringTools.splitToLong(rowIds);
			if (ids != null && !ids.isEmpty()) {
				wxContentService.deleteByIds(ids);
			}
		}
		return ResponseUtils.responseJsonResult(true);
	}

	@POST
	@Path("/delete")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] deleteById(@Context HttpServletRequest request)
			throws IOException {
		wxContentService.deleteById(RequestUtils.getLong(request, "id"));
		return ResponseUtils.responseJsonResult(true);
	}

	@GET
	@POST
	@Path("/list")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] list(@Context HttpServletRequest request) throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxContentQuery query = new WxContentQuery();
		Tools.populate(query, params);

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
			limit = PageResult.DEFAULT_PAGE_SIZE;
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

	@POST
	@Path("/saveWxContent")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] saveWxContent(@Context HttpServletRequest request) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxContent wxContent = new WxContent();
		try {
			Tools.populate(wxContent, params);

			wxContent
					.setCategoryId(RequestUtils.getLong(request, "categoryId"));
			wxContent.setTitle(request.getParameter("title"));
			wxContent.setContent(request.getParameter("content"));
			wxContent.setStatus(RequestUtils.getInt(request, "status"));
			wxContent.setPriority(RequestUtils.getInt(request, "priority"));
			wxContent.setType(request.getParameter("type"));
			wxContent.setUuid(request.getParameter("uuid"));
			wxContent.setKeywords(request.getParameter("keywords"));
			wxContent.setKeywordsCount(RequestUtils.getInt(request,
					"keywordsCount"));
			wxContent.setSummary(request.getParameter("summary"));
			wxContent.setIcon(request.getParameter("icon"));
			wxContent.setBigIcon(request.getParameter("bigIcon"));
			wxContent.setSmallIcon(request.getParameter("smallIcon"));
			wxContent.setUrl(request.getParameter("url"));
			wxContent.setCreateBy(request.getParameter("createBy"));
			wxContent
					.setCreateDate(RequestUtils.getDate(request, "createDate"));

			this.wxContentService.save(wxContent);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxContentService(WxContentService wxContentService) {
		this.wxContentService = wxContentService;
	}

	@GET
	@POST
	@Path("/view")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] view(@Context HttpServletRequest request) throws IOException {
		WxContent wxContent = null;
		if (StringUtils.isNotEmpty(request.getParameter("id"))) {
			wxContent = wxContentService.getWxContent(RequestUtils.getLong(
					request, "id"));
		}
		JSONObject result = new JSONObject();
		if (wxContent != null) {
			result = wxContent.toJsonObject();

			result.put("id", wxContent.getId());
			result.put("wxContentId", wxContent.getId());
		}
		return result.toJSONString().getBytes("UTF-8");
	}
}
