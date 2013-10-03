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
import com.glaf.wechat.domain.WxSiteInfo;
import com.glaf.wechat.query.WxSiteInfoQuery;
import com.glaf.wechat.service.WxSiteInfoService;

@Controller
@Path("/rs/wx/wxSiteInfo")
public class WxSiteInfoResourceRest {
	protected static final Log logger = LogFactory
			.getLog(WxSiteInfoResourceRest.class);

	protected WxSiteInfoService wxSiteInfoService;

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
				wxSiteInfoService.deleteByIds(ids);
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
		wxSiteInfoService.deleteById(RequestUtils.getLong(request, "id"));
		return ResponseUtils.responseJsonResult(true);
	}

	@GET
	@POST
	@Path("/list")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] list(@Context HttpServletRequest request) throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxSiteInfoQuery query = new WxSiteInfoQuery();
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
		int total = wxSiteInfoService.getWxSiteInfoCountByQueryCriteria(query);
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

			List<WxSiteInfo> list = wxSiteInfoService
					.getWxSiteInfosByQueryCriteria(start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxSiteInfo wxSiteInfo : list) {
					JSONObject rowJSON = wxSiteInfo.toJsonObject();
					rowJSON.put("id", wxSiteInfo.getId());
					rowJSON.put("wxSiteInfoId", wxSiteInfo.getId());
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
	@Path("/saveWxSiteInfo")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] saveWxSiteInfo(@Context HttpServletRequest request) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxSiteInfo wxSiteInfo = new WxSiteInfo();
		try {
			Tools.populate(wxSiteInfo, params);

			wxSiteInfo.setLinkman(request.getParameter("linkman"));
			wxSiteInfo.setTelephone(request.getParameter("telephone"));
			wxSiteInfo.setMobile(request.getParameter("mobile"));
			wxSiteInfo.setMail(request.getParameter("mail"));
			wxSiteInfo.setQq(request.getParameter("qq"));
			wxSiteInfo.setAddress(request.getParameter("address"));
			wxSiteInfo.setSiteUrl(request.getParameter("siteUrl"));
			wxSiteInfo.setRemark(request.getParameter("remark"));
			wxSiteInfo.setUuid(request.getParameter("uuid"));
			wxSiteInfo.setCreateBy(request.getParameter("createBy"));
			wxSiteInfo.setCreateDate(RequestUtils
					.getDate(request, "createDate"));

			this.wxSiteInfoService.save(wxSiteInfo);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxSiteInfoService(WxSiteInfoService wxSiteInfoService) {
		this.wxSiteInfoService = wxSiteInfoService;
	}

	@GET
	@POST
	@Path("/view")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] view(@Context HttpServletRequest request) throws IOException {
		WxSiteInfo wxSiteInfo = null;
		if (StringUtils.isNotEmpty(request.getParameter("id"))) {
			wxSiteInfo = wxSiteInfoService.getWxSiteInfo(RequestUtils.getLong(
					request, "id"));
		}
		JSONObject result = new JSONObject();
		if (wxSiteInfo != null) {
			result = wxSiteInfo.toJsonObject();

			result.put("id", wxSiteInfo.getId());
			result.put("wxSiteInfoId", wxSiteInfo.getId());
		}
		return result.toJSONString().getBytes("UTF-8");
	}
}
