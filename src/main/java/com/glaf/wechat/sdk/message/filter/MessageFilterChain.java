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

import java.util.Date;
import java.util.List;

import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.util.WxLogFactory;

/**
 * message filter chain
 * 
 */
public class MessageFilterChain {

	public List<IMessageFilter> filters = new java.util.concurrent.CopyOnWriteArrayList<IMessageFilter>();

	// add a filter
	public void addFilter(IMessageFilter filter) {
		filters.add(filter);
	}

	// do filter chain
	public Message doFilterChain(Message message) {
		Message msg = null;
		for (int i = 0; i < filters.size(); i++) {
			msg = filters.get(i).filterMessage(message);
			if (msg != null) {
				// if one filter can deal the message,then do it!so,it can be
				// returned!
				try {
					WxLog bean = new WxLog();
					bean.setActorId(message.getCustomer());
					bean.setCreateTime(new Date());
					bean.setFlag(0);
					bean.setIp(message.getRemoteIPAddr());
					bean.setOperate(message.getMsgType());
					WxLogFactory.create(bean);
				} catch (Exception ex) {
				}
				return msg;
			}
		}
		return msg;// if none of the filter can do it!
	}

	public List<IMessageFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<IMessageFilter> filters) {
		this.filters = filters;
	}

}
