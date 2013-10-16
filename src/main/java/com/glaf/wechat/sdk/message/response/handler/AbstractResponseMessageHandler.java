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

import com.glaf.wechat.sdk.message.IMessage;
import com.glaf.wechat.sdk.message.Message;

/**
 * a abstract class for handle message response
 * 
 */
public abstract class AbstractResponseMessageHandler implements IMessage,
		IResponseMessageHandler {

	public abstract String response(Message message);

	// wrap the content by tag & content & whether CDATA used
	public String wrapperContent(String tag, String content, boolean isCDATA) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<" + tag + ">");
		if (isCDATA) {
			buffer.append("<![CDATA[");
		}
		buffer.append(content);
		if (isCDATA) {
			buffer.append("]]>");
		}
		buffer.append("</" + tag + ">");
		return buffer.toString();
	}

	// wrap the root tag xml
	public String wrapperXmlContent(String content) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<" + TAG_XML + ">");
		buffer.append(content);
		buffer.append("</" + TAG_XML + ">");
		return buffer.toString();
	}

}
