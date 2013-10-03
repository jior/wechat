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
import com.glaf.wechat.domain.WxMember;
import com.glaf.wechat.query.WxMemberQuery;
import com.glaf.wechat.service.WxMemberService;

@Controller
@Path("/rs/wx/wxMember")
public class WxMemberResourceRest {
	protected static final Log logger = LogFactory
			.getLog(WxMemberResourceRest.class);

	protected WxMemberService wxMemberService;

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
				wxMemberService.deleteByIds(ids);
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
		wxMemberService.deleteById(RequestUtils.getLong(request, "id"));
		return ResponseUtils.responseJsonResult(true);
	}

	@GET
	@POST
	@Path("/list")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] list(@Context HttpServletRequest request) throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMemberQuery query = new WxMemberQuery();
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

	@POST
	@Path("/saveWxMember")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] saveWxMember(@Context HttpServletRequest request) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMember wxMember = new WxMember();
		try {
			Tools.populate(wxMember, params);

			wxMember.setCardNo(request.getParameter("cardNo"));
			wxMember.setName(request.getParameter("name"));
			wxMember.setTelephone(request.getParameter("telephone"));
			wxMember.setMobile(request.getParameter("mobile"));
			wxMember.setMail(request.getParameter("mail"));
			wxMember.setQq(request.getParameter("qq"));
			wxMember.setAddress(request.getParameter("address"));
			wxMember.setStatus(RequestUtils.getInt(request, "status"));
			wxMember.setUuid(request.getParameter("uuid"));
			wxMember.setCreateBy(request.getParameter("createBy"));
			wxMember.setCreateDate(RequestUtils.getDate(request, "createDate"));

			this.wxMemberService.save(wxMember);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxMemberService(WxMemberService wxMemberService) {
		this.wxMemberService = wxMemberService;
	}

	@GET
	@POST
	@Path("/view")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] view(@Context HttpServletRequest request) throws IOException {
		WxMember wxMember = null;
		if (StringUtils.isNotEmpty(request.getParameter("id"))) {
			wxMember = wxMemberService.getWxMember(RequestUtils.getLong(
					request, "id"));
		}
		JSONObject result = new JSONObject();
		if (wxMember != null) {
			result = wxMember.toJsonObject();

			result.put("id", wxMember.getId());
			result.put("wxMemberId", wxMember.getId());
		}
		return result.toJSONString().getBytes("UTF-8");
	}
}
