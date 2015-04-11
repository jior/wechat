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

import org.apache.commons.lang3.StringUtils;

import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.domain.WxFollower;
import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxFollowerService;
import com.glaf.wechat.util.Constants;
import com.glaf.wechat.util.WxLogFactory;

/**
 * 取消关注时回复
 */
public class UnsubscribeMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		WxContentService wxContentService = ContextFactory
				.getBean("wxContentService");
		WxContentQuery query = new WxContentQuery();
		query.accountId(message.getAccountId());
		query.createBy(message.getCustomer());
		query.type("F");
		query.locked(0);

		List<WxContent> rows = wxContentService.list(query);
		if (rows != null && !rows.isEmpty()) {
			ResponseNewsMessage newsMessage = new ResponseNewsMessage();
			newsMessage.setCount(rows.size());
			for (WxContent c : rows) {
				ItemArticle art = new ItemArticle();
				art.setDescription(c.getSummary());
				art.setTitle(c.getTitle());
				if (StringUtils.isNotEmpty(c.getUrl())) {
					if (StringUtils.startsWith(c.getUrl(), "/website/wx/")) {
						String url = message.getServiceUrl() + c.getUrl();
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
					if (StringUtils.startsWith(c.getPicUrl(), "/website/wx/")) {
						String url = message.getServiceUrl() + c.getPicUrl();
						art.setPicUrl(url);
					} else {
						art.setPicUrl(c.getPicUrl());
					}
				} else {
					if (StringUtils.isNotEmpty(c.getIcon())) {
						if (StringUtils.startsWith(c.getIcon(), "/wx/upload/")) {
							String url = message.getServiceUrl() + c.getIcon();
							art.setPicUrl(url);
						} else {
							art.setPicUrl(c.getIcon());
						}
					}
				}
				newsMessage.addItemArticle(art);
			}

			return newsMessage;
		}

		try {
			WxLog bean = new WxLog();
			bean.setOpenId(message.getFromUserName());
			bean.setActorId(message.getCustomer());
			bean.setCreateTime(new Date());
			bean.setFlag(Constants.UNSUBSCRIBE_LOG_FLAG);
			bean.setIp(message.getRemoteIPAddr());
			bean.setOperate("unsubscribe");
			WxLogFactory.create(bean);
		} catch (Exception ex) {
		}

		try {
			WxFollowerService wxFollowerService = ContextFactory
					.getBean("wxFollowerService");
			WxFollower follower = wxFollowerService.getWxFollower(
					message.getAccountId(), message.getToUserName(),
					message.getFromUserName());
			if (follower == null) {
				Date date = new Date();
				follower = new WxFollower();
				follower.setAccountId(message.getAccountId());
				follower.setActorId(message.getCustomer());
				follower.setSourceId(message.getToUserName());
				follower.setOpenId(message.getFromUserName());
				follower.setUnsubscribeTime(date.getTime());
			}
			follower.setLocked(1);
			wxFollowerService.save(follower);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
