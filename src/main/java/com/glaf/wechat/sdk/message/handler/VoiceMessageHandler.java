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

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.VoiceMessage;
import com.glaf.wechat.sdk.message.filter.IMessageFilter;
import com.glaf.wechat.sdk.message.filter.MessageFilterChain;
import com.glaf.wechat.sdk.message.filter.DefaultResponseMessageFilter;
import com.glaf.wechat.sdk.message.filter.VoiceMessageFilter;

/**
 * handle text message <br>
 * every handler can create its own filter chain to handler message or not
 * create it by handling directly
 * 
 */
public class VoiceMessageHandler extends AbstractMessageHandler {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageFilterChain filterChain = new MessageFilterChain();
		if (StringUtils.isNotEmpty(conf.get("voice.messageFilter"))) {
			String str = conf.get("voice.messageFilter");
			StringTokenizer token = new StringTokenizer(str);
			while (token.hasMoreTokens()) {
				String className = token.nextToken();
				Object object = com.glaf.core.util.ReflectUtils
						.instantiate(className);
				if (object instanceof IMessageFilter) {
					IMessageFilter filter = (IMessageFilter) object;
					filterChain.addFilter(filter);
				}
			}
		}
		filterChain.addFilter(new VoiceMessageFilter());
		// 加入默认的响应处理类
		filterChain.addFilter(new DefaultResponseMessageFilter());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		VoiceMessage msg = (VoiceMessage) message;
		msg.setMsgType(message.getMsgType());
		msg.setFormat(root.elementText(TAG_FORMAT));
		msg.setMediaId(root.elementText(TAG_MEDIAID));
	}

}
