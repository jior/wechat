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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.VoiceMessage;

/**
 * ”Ô—‘ªÿ∏¥
 * 
 */
public class VoiceMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	protected static final Log logger = LogFactory
			.getLog(VoiceMessageFilter.class);

	@Override
	public Message doSpecailMessageFilter(Message message) {
		if (message instanceof VoiceMessage) {
			VoiceMessage msg = (VoiceMessage) message;
			logger.debug("MediaId:" + msg.getMediaId());
			logger.debug("Format:" + msg.getFormat());
		}
		return null;
	}

}
