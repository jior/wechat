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
package com.glaf.apps.member.website.springmvc;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.glaf.core.util.Tools;

import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.util.WxIdentityFactory;
import com.glaf.apps.member.domain.WxMember;
import com.glaf.apps.member.service.WxMemberService;

@Controller("/wx/member")
@RequestMapping("/wx/member")
public class WxPublicMemberController {

	protected static final Log logger = LogFactory
			.getLog(WxPublicMemberController.class);

	protected WxMemberService wxMemberService;

	@ResponseBody
	@RequestMapping("/post/{accountId}")
	public byte[] post(@PathVariable("accountId") Long accountId,
			HttpServletRequest request) throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		try {
			WxMember wxMember = new WxMember();
			Tools.populate(wxMember, params);
			wxMember.setAccountId(accountId);
			wxMember.setName(request.getParameter("name"));
			wxMember.setTelephone(request.getParameter("telephone"));
			wxMember.setMobile(request.getParameter("mobile"));
			wxMember.setMail(request.getParameter("mail"));
			wxMember.setQq(request.getParameter("qq"));
			wxMember.setAddress(request.getParameter("address"));
			wxMember.setCreateBy(user.getActorId());

			wxMemberService.save(wxMember);
			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}

		return ResponseUtils.responseJsonResult(false, "提交不成功，请稍候再试！");
	}

	@javax.annotation.Resource
	public void setWxMemberService(WxMemberService wxMemberService) {
		this.wxMemberService = wxMemberService;
	}

	@RequestMapping("/mobile/{accountId}")
	public ModelAndView mobile(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		RequestUtils.setRequestParameterToAttribute(request);

		request.setAttribute("accountId", accountId);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxMember.mobile");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/member/mobile", modelMap);
	}

}
