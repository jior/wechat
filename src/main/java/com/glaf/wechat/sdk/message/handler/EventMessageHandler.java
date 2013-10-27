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
package com.glaf.wechat.sdk.message.handler;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.EventMessage;
import com.glaf.wechat.sdk.message.filter.MenuMessageFilter;
import com.glaf.wechat.sdk.message.filter.MessageFilterChain;
import com.glaf.wechat.sdk.message.filter.DefaultResponseMessageFilter;
import com.glaf.wechat.sdk.message.filter.SubscribeMessageFilter;

/**
 * handle event message
 */
public class EventMessageHandler extends AbstractMessageHandler {
	protected static final Log logger = LogFactory
			.getLog(EventMessageHandler.class);

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageFilterChain filterChain = new MessageFilterChain();
		EventMessage msg = (EventMessage) message;
		if (StringUtils.equalsIgnoreCase(msg.getEvent(), "subscribe")) {
			// 订阅事件
			filterChain.addFilter(new SubscribeMessageFilter());
		} else if (StringUtils.equalsIgnoreCase(msg.getEvent(), "CLICK")) {
			// 自定义菜单点击事件
			String eventKey = msg.getEventKey();
			if (StringUtils.isNotEmpty(eventKey)) {
				filterChain.addFilter(new MenuMessageFilter());
				logger.debug("自定义菜单点击事件:" + eventKey);
			}
		}

		// 加入默认的响应处理类
		filterChain.addFilter(new DefaultResponseMessageFilter());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		EventMessage msg = (EventMessage) message;
		msg.setEvent(root.elementText(TAG_EVENT));
		msg.setEventKey(root.elementText(TAG_EVENTKEY));
	}

}
