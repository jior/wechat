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
package com.glaf.wechat.sdk.message.filter;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.domain.WxMenu;
import com.glaf.wechat.query.WxMenuQuery;
import com.glaf.wechat.sdk.message.EventMessage;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseMenuMessage;
import com.glaf.wechat.service.WxMenuService;

public class MenuMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		EventMessage msg = (EventMessage) message;
		WxMenuService wxMenuService = ContextFactory.getBean("wxMenuService");
		WxMenuQuery query = new WxMenuQuery();
		query.createBy(message.getCustomer());
		query.key(msg.getEventKey());
		List<WxMenu> rows = wxMenuService.list(query);
		if (rows != null && !rows.isEmpty()) {
			WxMenu menu = rows.get(0);
			ResponseMenuMessage menuMessage = new ResponseMenuMessage();
			menuMessage.setDescription(menu.getDesc());
			menuMessage.setTitle(menu.getName());
			if (StringUtils.isNotEmpty(menu.getUrl())) {
				if (StringUtils.startsWith(menu.getUrl(), "/website/wx/")) {
					String url = message.getServiceUrl() + menu.getUrl();
					menuMessage.setUrl(url);
				} else {
					menuMessage.setUrl(menu.getUrl());
				}
			} else {
				String url = message.getServiceUrl()
						+ "/website/wx/content/view/" + menu.getId();
				menuMessage.setUrl(url);
			}
			if (StringUtils.isNotEmpty(menu.getPicUrl())) {
				if (StringUtils.startsWith(menu.getPicUrl(), "/website/wx/")) {
					String url = message.getServiceUrl() + menu.getPicUrl();
					menuMessage.setPicUrl(url);
				} else {
					menuMessage.setPicUrl(menu.getPicUrl());
				}
			}
			return menuMessage;
		}
		return null;
	}

}
