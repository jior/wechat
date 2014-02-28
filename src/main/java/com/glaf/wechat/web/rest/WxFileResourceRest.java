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
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.Tools;
import com.glaf.wechat.domain.WxFile;
import com.glaf.wechat.query.WxFileQuery;
import com.glaf.wechat.service.WxFileService;

@Controller
@Path("/rs/wx/file")
public class WxFileResourceRest {
	protected static final Log logger = LogFactory
			.getLog(WxFileResourceRest.class);

	protected WxFileService wxFileService;

	@GET
	@POST
	@Path("/jsonArray")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] jsonArray(@Context HttpServletRequest request)
			throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxFileQuery query = new WxFileQuery();
		Tools.populate(query, params);

		query.createBy("system");

		JSONArray result = new JSONArray();
		int total = wxFileService.getWxFileCountByQueryCriteria(query);
		if (total > 0) {
			List<WxFile> list = wxFileService.list(query);
			if (list != null && !list.isEmpty()) {
				for (WxFile wxFile : list) {
					JSONObject rowJSON = wxFile.toJsonObject();
					rowJSON.put("id", wxFile.getId());
					rowJSON.put("fileId", wxFile.getId());
					result.add(rowJSON);
				}
			}
		}

		return result.toJSONString().getBytes("UTF-8");
	}

	@javax.annotation.Resource
	public void setWxFileService(WxFileService wxFileService) {
		this.wxFileService = wxFileService;
	}

	@GET
	@POST
	@Path("/view")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] view(@Context HttpServletRequest request) throws IOException {
		WxFile wxFile = null;
		if (StringUtils.isNotEmpty(request.getParameter("uuid"))) {
			wxFile = wxFileService.getWxFileByUUID(RequestUtils.getString(
					request, "uuid"));
		}
		JSONObject result = new JSONObject();
		if (wxFile != null) {
			result = wxFile.toJsonObject();
			result.put("id", wxFile.getId());
			result.put("fileId", wxFile.getId());
		}
		return result.toJSONString().getBytes("UTF-8");
	}
}
