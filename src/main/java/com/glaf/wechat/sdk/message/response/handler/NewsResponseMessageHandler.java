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

import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;

/**
 * message response news handler
 * 
 */
public class NewsResponseMessageHandler extends AbstractResponseMessageHandler {

	@Override
	public String response(Message message) {
		ResponseNewsMessage newsMessage = (ResponseNewsMessage) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer items = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemArticle itemArticle = null;
		for (int i = 0; i < newsMessage.getCount(); i++) {
			item = new StringBuffer();
			itemArticle = newsMessage.getArticleItems().get(i);
			item.append(wrapperContent(TAG_TITLE, itemArticle.getTitle(), true))
					.append(wrapperContent(TAG_DESCRIPTION,
							itemArticle.getDescription(), true))
					.append(wrapperContent(TAG_PICURL, itemArticle.getPicUrl(),
							true))
					.append(wrapperContent(TAG_URL, itemArticle.getUrl(), true));
			items.append(wrapperContent(TAG_ITEM, item.toString(), false));
		}
		String articles = wrapperContent(TAG_ARTICLES, items.toString(), false);
		buffer.append(
				wrapperContent(TAG_TOUSERNAME, newsMessage.getToUserName(),
						true))
				.append(wrapperContent(TAG_FROMUSERNAME,
						newsMessage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME,
						newsMessage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, newsMessage.getMsgType(),
						true))
				.append(wrapperContent(TAG_ARTICLECOUNT, newsMessage.getCount()
						+ "", false))
				.append(articles)
				.append(wrapperContent(TAG_FUNCFLAG, newsMessage.getFuncFlag()
						+ "", false));
		return wrapperXmlContent(buffer.toString());
	}

}
