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

package com.glaf.wechat.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glaf.wechat.message.response.Article;
import com.glaf.wechat.message.response.NewsMessage;
import com.glaf.wechat.message.response.TextMessage;
import com.glaf.wechat.util.MessageUtils;

/**
 * 核心服务类
 * 
 * @author jior
 */
public class CoreServiceTest {
	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtils.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			textMessage
					.setContent("欢迎访问<a href=\"https://github.com/jior/glaf\">GLAF</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtils.textMessageToXml(textMessage);

			// 文本消息
			if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");

				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					article.setDescription("GLAF，企业基础应用开发框架。");
					article.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article.setUrl("https://github.com/jior/glaf");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// 单图文消息---不含图片
				else if ("2".equals(content)) {
					Article article = new Article();

					article.setTitle("微信公众帐号开发教程Java版");
					article.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article.setUrl("https://github.com/jior/glaf");

					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// 多图文消息
				else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发教程Java版");
					article1.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article1.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article1.setUrl("https://github.com/jior/glaf");

					Article article2 = new Article();
					article2.setTitle("第2篇\n微信公众帐号的类型");
					article2.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article2.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article2.setUrl("https://github.com/jior/glaf");

					Article article3 = new Article();
					article3.setTitle("第3篇\n开发模式启用及接口配置");
					article3.setTitle("微信公众帐号开发教程Java版");
					article3.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article3.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article3.setUrl("https://github.com/jior/glaf");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// 多图文消息---首条消息不含图片
				else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发教程Java版");
					article1.setDescription("");
					// 将图片置为空
					article1.setPicUrl("");
					article1.setUrl("https://github.com/jior/glaf");

					Article article2 = new Article();
					article2.setTitle("第2篇\n微信公众帐号的类型");
					article2.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article2.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article2.setUrl("https://github.com/jior/glaf");

					Article article3 = new Article();
					article3.setTitle("第3篇\n开发模式启用及接口配置");
					article3.setTitle("微信公众帐号开发教程Java版");
					article3.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article3.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article3.setUrl("https://github.com/jior/glaf");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// 多图文消息---最后一条消息不含图片
				else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("第7篇\n文本消息中换行符的使用");
					article1.setDescription("");
					article1.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article1.setUrl("https://github.com/jior/glaf");

					Article article2 = new Article();
					article2.setTitle("第2篇\n微信公众帐号的类型");
					article2.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article2.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article2.setUrl("https://github.com/jior/glaf");

					Article article3 = new Article();
					article3.setTitle("第3篇\n开发模式启用及接口配置");
					article3.setTitle("微信公众帐号开发教程Java版");
					article3.setDescription("GLAF，企业基础应用开发框架。" + emoji(0x1F6B9));
					article3.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article3.setUrl("https://github.com/jior/glaf");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}