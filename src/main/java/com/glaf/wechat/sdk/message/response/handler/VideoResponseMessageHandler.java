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
import com.glaf.wechat.sdk.message.ResponseVideoMessage;

/**
 * message response video handler
 * 
 */
public class VideoResponseMessageHandler extends AbstractResponseMessageHandler {

	@Override
	public String response(Message message) {
		ResponseVideoMessage videoMessage = (ResponseVideoMessage) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer item = new StringBuffer();
		item.append(wrapperContent(TAG_TITLE, videoMessage.getTitle(), true))
				.append(wrapperContent(TAG_DESCRIPTION,
						videoMessage.getDescription(), true))
				.append(wrapperContent(TAG_MEDIAID, videoMessage.getMediaId(),
						true));
		String video = wrapperContent(TAG_VIDEO, item.toString(), false);
		buffer.append(
				wrapperContent(TAG_TOUSERNAME, videoMessage.getToUserName(),
						true))
				.append(wrapperContent(TAG_FROMUSERNAME,
						videoMessage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME,
						videoMessage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, videoMessage.getMsgType(),
						true)).append(video);
		return wrapperXmlContent(buffer.toString());
	}

}
