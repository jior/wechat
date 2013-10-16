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

import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;
import com.glaf.wechat.service.WxContentService;

/**
 * ¹Ø×¢Ê±»Ø¸´
 */
public class SubscribeMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		WxContentService wxContentService = ContextFactory
				.getBean("wxContentService");
		WxContentQuery query = new WxContentQuery();
		query.createBy(message.getCustomer());
		query.type("F");
		List<WxContent> rows = wxContentService.list(query);
		if (rows != null && !rows.isEmpty()) {
			ResponseNewsMessage newsMessage = new ResponseNewsMessage();
			newsMessage.setCount(rows.size());
			for (WxContent c : rows) {
				ItemArticle art = new ItemArticle();
				art.setDescription(c.getSummary());
				art.setTitle(c.getTitle());
				art.setUrl(c.getUrl());//
				art.setPicUrl(c.getPicUrl());//
				newsMessage.addItemArticle(art);
			}
			return newsMessage;
		}
		return null;
	}

}
