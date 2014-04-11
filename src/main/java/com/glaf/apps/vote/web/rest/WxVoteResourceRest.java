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

package com.glaf.apps.vote.web.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glaf.apps.vote.domain.WxVote;
import com.glaf.apps.vote.domain.WxVoteResult;
import com.glaf.apps.vote.service.WxVoteResultService;
import com.glaf.apps.vote.service.WxVoteService;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.ResponseUtils;

@Controller
@Path("/rs/wx/vote")
public class WxVoteResourceRest {
	protected static final Log logger = LogFactory
			.getLog(WxVoteResourceRest.class);

	protected WxVoteService wxVoteService;

	protected WxVoteResultService wxVoteResultService;

	@GET
	@POST
	@Path("/post")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public byte[] post(@Context HttpServletRequest request) throws IOException {
		WxVote vote = null;
		if (StringUtils.isNotEmpty(request.getParameter("id"))) {
			vote = wxVoteService.getWxVote(RequestUtils.getLong(request, "id"));
			if (vote != null) {
				if (vote.getStatus() != 0) {
					return ResponseUtils.responseJsonResult(false,
							"投票主题已经结束，谢谢您的关注！");
				}
				Date now = new Date();
				if (vote.getEndDate() != null && vote.getEndDate().getTime() < now.getTime()) {
					return ResponseUtils.responseJsonResult(false,
							"投票主题已经结束，谢谢您的关注！");
				}
				String ip = RequestUtils.getIPAddress(request);
				if (vote.getLimitFlag() == 1) {// 限制每个IP地址投票次数
					WxVoteResult result = wxVoteService.getLatestVoteResult(
							vote.getId(), ip);
					if (result != null) {
						Date voteDate = result.getVoteDate();
						long ts = now.getTime() - voteDate.getTime();
						if (ts / (1000 * 60) <= vote.getLimitTimeInterval()) {
							return ResponseUtils.responseJsonResult(false,
									"您已经投过票了，谢谢您的关注！");
						}
					}
				}
				if (vote.getRelations() != null
						&& !vote.getRelations().isEmpty()) {
					List<WxVoteResult> wxVoteResults = new java.util.concurrent.CopyOnWriteArrayList<WxVoteResult>();
					for (WxVote relation : vote.getRelations()) {
						WxVoteResult result = new WxVoteResult();
						result.setIp(ip);
						result.setVoteId(relation.getId());
						result.setVoteDate(new Date());
						result.setResult(request.getParameter("result_"
								+ relation.getId()));
						wxVoteResults.add(result);
					}
					wxVoteResultService.saveAll(wxVoteResults);
				} else {
					WxVoteResult result = new WxVoteResult();
					result.setIp(ip);
					result.setVoteId(vote.getId());
					result.setVoteDate(new Date());
					result.setResult(request.getParameter("result"));
					wxVoteResultService.save(result);
				}
			}
		}
		return ResponseUtils.responseJsonResult(false, "投票不成功，请稍候再试！");
	}

	@javax.annotation.Resource
	public void setWxVoteResultService(WxVoteResultService wxVoteResultService) {
		this.wxVoteResultService = wxVoteResultService;
	}

	@javax.annotation.Resource
	public void setWxVoteService(WxVoteService wxVoteService) {
		this.wxVoteService = wxVoteService;
	}

}
