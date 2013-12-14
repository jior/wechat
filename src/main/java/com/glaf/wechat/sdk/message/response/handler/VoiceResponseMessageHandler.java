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
package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseVoiceMessage;

/**
 * message response voice handler
 * 
 */
public class VoiceResponseMessageHandler extends AbstractResponseMessageHandler {

	@Override
	public String response(Message message) {
		ResponseVoiceMessage voiceMessage = (ResponseVoiceMessage) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer item = new StringBuffer();
		item.append(wrapperContent(TAG_MEDIAID, voiceMessage.getMediaId(), true));
		String voice = wrapperContent(TAG_VOICE, item.toString(), false);
		buffer.append(
				wrapperContent(TAG_TOUSERNAME, voiceMessage.getToUserName(),
						true))
				.append(wrapperContent(TAG_FROMUSERNAME,
						voiceMessage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME,
						voiceMessage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, voiceMessage.getMsgType(),
						true)).append(voice);
		return wrapperXmlContent(buffer.toString());
	}

}
