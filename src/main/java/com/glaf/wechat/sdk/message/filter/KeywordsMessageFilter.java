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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.domain.WxKeywords;
import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.query.WxKeywordsQuery;
import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;
import com.glaf.wechat.sdk.message.TextMessage;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxKeywordsService;
import com.glaf.wechat.util.WxLogFactory;

/**
 * 关键字回复
 * 
 */
public class KeywordsMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	protected static final Log logger = LogFactory
			.getLog(KeywordsMessageFilter.class);

	@Override
	public Message doSpecailMessageFilter(Message message) {
		if (message instanceof TextMessage) {
			TextMessage msg = (TextMessage) message;
			String content = msg.getContent();
			if (StringUtils.equals(content, "主页")
					|| StringUtils.equals(content, "首页")) {

			}
			WxContentService wxContentService = ContextFactory
					.getBean("wxContentService");
			WxKeywordsService wxKeywordsService = ContextFactory
					.getBean("wxKeywordsService");
			WxKeywordsQuery query = new WxKeywordsQuery();
			query.accountId(message.getAccountId());
			query.createBy(message.getCustomer());
			query.keywords(content);
			List<WxKeywords> list = wxKeywordsService.list(query);
			if (list != null && !list.isEmpty()) {
				List<Long> contentIds = new ArrayList<Long>();
				for (WxKeywords m : list) {
					contentIds.add(m.getContentId());
				}
				WxContentQuery q = new WxContentQuery();
				q.contentIds(contentIds);
				q.type("K");
				List<WxContent> rows = wxContentService.list(q);
				if (rows != null && !rows.isEmpty()) {
					ResponseNewsMessage newsMessage = new ResponseNewsMessage();
					newsMessage.setCount(rows.size());
					for (WxContent c : rows) {
						ItemArticle art = new ItemArticle();
						art.setDescription(c.getSummary());
						art.setTitle(c.getTitle());
						if (StringUtils.isNotEmpty(c.getUrl())) {
							if (StringUtils.startsWith(c.getUrl(),
									"/website/wx/")) {
								String url = message.getServiceUrl()
										+ c.getUrl();
								art.setUrl(url);
							} else {
								art.setUrl(c.getUrl());
							}
						} else {
							String url = message.getServiceUrl()
									+ "/website/wx/content/view/" + c.getId();
							art.setUrl(url);
						}
						if (StringUtils.isNotEmpty(c.getPicUrl())) {
							if (StringUtils.startsWith(c.getPicUrl(),
									"/website/wx/")) {
								String url = message.getServiceUrl()
										+ c.getPicUrl();
								art.setPicUrl(url);
							} else {
								art.setPicUrl(c.getPicUrl());
							}
						} else {
							if (StringUtils.isNotEmpty(c.getIcon())) {
								if (StringUtils.startsWith(c.getIcon(),
										"/wx/upload/")) {
									String url = message.getServiceUrl()
											+ c.getIcon();
									art.setPicUrl(url);
								}
							}
						}
						newsMessage.addItemArticle(art);
					}
					logger.debug(msg.getContent() + " reply content:"
							+ newsMessage.getArticleItems().size());

					try {
						WxLog bean = new WxLog();
						bean.setOpenId(message.getFromUserName());
						bean.setAccount(message.getCustomer());
						bean.setCreateTime(new Date());
						bean.setFlag(100);// 关键字回复
						bean.setIp(message.getRemoteIPAddr());
						bean.setOperate("keywords");
						bean.setContent(content);
						WxLogFactory.create(bean);
					} catch (Exception ex) {
					}

					return newsMessage;
				}
			}
		}
		return null;
	}

}
