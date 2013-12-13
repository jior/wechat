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
package com.glaf.apps.vote.website.springmvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.glaf.core.config.ViewProperties;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.ResponseUtils;
import com.glaf.apps.vote.domain.WxVote;
import com.glaf.apps.vote.domain.WxVoteResult;
import com.glaf.apps.vote.service.WxVoteResultService;
import com.glaf.apps.vote.service.WxVoteService;

@Controller("/wx/vote")
@RequestMapping("/wx/vote")
public class WxPublicVoteController {

	protected static final Log logger = LogFactory
			.getLog(WxPublicVoteController.class);

	protected WxVoteService wxVoteService;

	protected WxVoteResultService wxVoteResultService;

	@ResponseBody
	@RequestMapping("/post/{id}")
	public byte[] post(@PathVariable("id") String id, HttpServletRequest request)
			throws IOException {
		WxVote vote = null;
		if (StringUtils.isNotEmpty(id)) {
			vote = wxVoteService.getWxVote(Long.parseLong(id));
			if (vote != null) {
				if (vote.getStatus() != 1) {
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
									"您已经投过票了，谢谢您的参与！");
						}
					}
				}
				if (vote.getRelations() != null
						&& !vote.getRelations().isEmpty()) {
					List<WxVoteResult> wxVoteResults = new ArrayList<WxVoteResult>();
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
				return ResponseUtils.responseJsonResult(true, "投票成功，谢谢您的参与！");
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

	@RequestMapping("/vote/{id}")
	public ModelAndView vote(@PathVariable("id") String id,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		RequestUtils.setRequestParameterToAttribute(request);
		WxVote vote = null;
		if (StringUtils.isNotEmpty(id)) {
			vote = wxVoteService.getWxVote(Long.parseLong(id));
			if (vote != null) {
				request.setAttribute("vote", vote);
			}
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxVote.vote");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/vote/vote", modelMap);
	}

}
