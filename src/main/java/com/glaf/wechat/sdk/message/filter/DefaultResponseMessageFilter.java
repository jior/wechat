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

import org.apache.commons.lang.StringUtils;

import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;
import com.glaf.wechat.service.WxContentService;

/**
 * filter whether the message is for everything<br>
 * so,if this filter locates in the last of filterchain,then it certainly
 * returns the message response
 * 
 */
public class DefaultResponseMessageFilter extends AbstractMessageFilter
		implements IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		WxContentService wxContentService = ContextFactory
				.getBean("wxContentService");
		WxContentQuery query = new WxContentQuery();
		query.createBy(message.getCustomer());
		query.type("D");
		List<WxContent> rows = wxContentService.list(query);
		if (rows != null && !rows.isEmpty()) {
			ResponseNewsMessage newsMessage = new ResponseNewsMessage();
			newsMessage.setCount(rows.size());
			for (WxContent c : rows) {
				ItemArticle art = new ItemArticle();
				art.setDescription(c.getSummary());
				art.setTitle(c.getTitle());
				if (StringUtils.isNotEmpty(c.getUrl())) {
					if (StringUtils.startsWith(c.getUrl(), "/mx/wx/")) {
						String url = message.getContextPath()
								+ c.getUrl();
						art.setUrl(url);
					} else {
						art.setUrl(c.getUrl());
					}
				} else {
					String url = message.getContextPath()
							+ "/mx/wx/content/detail/" + c.getUuid();
					art.setUrl(url);
				}
				if (StringUtils.isNotEmpty(c.getPicUrl())) {
					if (StringUtils
							.startsWith(c.getPicUrl(), "/mx/wx/")) {
						String url = message.getContextPath()
								+ c.getPicUrl();
						art.setPicUrl(url);
					} else {
						art.setPicUrl(c.getPicUrl());
					}
				}
				newsMessage.addItemArticle(art);
			}
			return newsMessage;
		}
		return null;
	}

}
