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
package com.glaf.wechat.website.springmvc;

import java.io.IOException;
import java.util.Map;

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
import com.glaf.core.identity.User;
import com.glaf.core.security.IdentityFactory;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.ResponseUtils;
import com.glaf.core.util.Tools;
import com.glaf.wechat.domain.WxMessage;
import com.glaf.wechat.service.WxMessageService;

@Controller("/wx/message")
@RequestMapping("/wx/message")
public class WxPublicMessageController {

	protected static final Log logger = LogFactory
			.getLog(WxPublicMessageController.class);

	protected WxMessageService wxMessageService;

	@ResponseBody
	@RequestMapping("/post/{userId}")
	public byte[] post(@PathVariable("userId") Long userId,
			HttpServletRequest request) throws IOException {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		User user = IdentityFactory.getUserByUserId(userId);

		try {
			WxMessage wxMessage = new WxMessage();
			Tools.populate(wxMessage, params);

			wxMessage.setName(request.getParameter("name"));
			wxMessage.setMobile(request.getParameter("mobile"));
			wxMessage.setTitle(request.getParameter("title"));
			wxMessage.setContent(request.getParameter("content"));
			wxMessage.setCreateBy(user.getActorId());

			wxMessageService.save(wxMessage);
			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}

		return ResponseUtils.responseJsonResult(false, "反馈不成功，请稍候再试！");
	}

	@javax.annotation.Resource
	public void setWxMessageService(WxMessageService wxMessageService) {
		this.wxMessageService = wxMessageService;
	}

	@RequestMapping("/mobile/{userId}")
	public ModelAndView mobile(@PathVariable("userId") Long userId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		RequestUtils.setRequestParameterToAttribute(request);

		User user = IdentityFactory.getUserByUserId(userId);
		request.setAttribute("user", user);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxMessage.mobile");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/message/mobile", modelMap);
	}

}
