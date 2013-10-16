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

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.handler.AbstractMessageHandler;

/**
 * message filter abstract class <br>
 * extends message handler helper,then filter can access the message handling
 * method
 */
public abstract class AbstractMessageFilter extends AbstractMessageHandler
		implements IMessageFilter {

	// subclass handle it
	public abstract Message doSpecailMessageFilter(Message message);

	@Override
	public Message filterMessage(Message message) {
		this.message = message;// must do this! otherwise, message may be null
		return doSpecailMessageFilter(message);
	}

	@Override
	protected Message handleSpecialMessage(Message message) { 
		return null;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) { 
		
	}

}
