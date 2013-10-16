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

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.LinkMessage;
import com.glaf.wechat.sdk.message.filter.MessageFilterChain;
import com.glaf.wechat.sdk.message.filter.DefaultResponseMessageFilter;

/**
 * handle link message
 * 
 */
public class LinkMessageHandler extends AbstractMessageHandler {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageFilterChain filterChain = new MessageFilterChain();
		//加入默认的响应处理类
		filterChain.addFilter(new DefaultResponseMessageFilter());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		LinkMessage msg = (LinkMessage) message;
		msg.setUrl(root.elementText(TAG_URL));
		msg.setDescription(root.elementText(TAG_DESCRIPTION));
		msg.setTitle(root.elementText(TAG_TITLE));
	}

}
