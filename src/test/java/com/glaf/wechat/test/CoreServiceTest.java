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
 * ���ķ�����
 * 
 * @author jior
 */
public class CoreServiceTest {
	/**
	 * emoji����ת��(hex -> utf-16)
	 * 
	 * @param
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml�������
			Map<String, String> requestMap = MessageUtils.parseXml(request);

			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// Ĭ�ϻظ����ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// ����href����ֵ������˫�������������ַ��������˫���ų�ͻ������Ҫת��
			textMessage
					.setContent("��ӭ����<a href=\"https://github.com/jior/glaf\">GLAF</a>!");
			// ���ı���Ϣ����ת����xml�ַ���
			respMessage = MessageUtils.textMessageToXml(textMessage);

			// �ı���Ϣ
			if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_TEXT)) {
				// �����û����͵��ı���Ϣ����
				String content = requestMap.get("Content");

				// ����ͼ����Ϣ
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				// ��ͼ����Ϣ
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("΢�Ź����ʺſ����̳�Java��");
					article.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�");
					article.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article.setUrl("https://github.com/jior/glaf");
					articleList.add(article);
					// ����ͼ����Ϣ����
					newsMessage.setArticleCount(articleList.size());
					// ����ͼ����Ϣ������ͼ�ļ���
					newsMessage.setArticles(articleList);
					// ��ͼ����Ϣ����ת����xml�ַ���
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ---����ͼƬ
				else if ("2".equals(content)) {
					Article article = new Article();

					article.setTitle("΢�Ź����ʺſ����̳�Java��");
					article.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
					article.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article.setUrl("https://github.com/jior/glaf");

					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ
				else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("΢�Ź����ʺſ����̳�Java��");
					article1.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
					article1.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article1.setUrl("https://github.com/jior/glaf");

					Article article2 = new Article();
					article2.setTitle("��2ƪ\n΢�Ź����ʺŵ�����");
					article2.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
					article2.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article2.setUrl("https://github.com/jior/glaf");

					Article article3 = new Article();
					article3.setTitle("��3ƪ\n����ģʽ���ü��ӿ�����");
					article3.setTitle("΢�Ź����ʺſ����̳�Java��");
					article3.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
					article3.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article3.setUrl("https://github.com/jior/glaf");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ---������Ϣ����ͼƬ
				else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("΢�Ź����ʺſ����̳�Java��");
					article1.setDescription("");
					// ��ͼƬ��Ϊ��
					article1.setPicUrl("");
					article1.setUrl("https://github.com/jior/glaf");

					Article article2 = new Article();
					article2.setTitle("��2ƪ\n΢�Ź����ʺŵ�����");
					article2.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
					article2.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article2.setUrl("https://github.com/jior/glaf");

					Article article3 = new Article();
					article3.setTitle("��3ƪ\n����ģʽ���ü��ӿ�����");
					article3.setTitle("΢�Ź����ʺſ����̳�Java��");
					article3.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
					article3.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article3.setUrl("https://github.com/jior/glaf");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtils.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ---���һ����Ϣ����ͼƬ
				else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("��7ƪ\n�ı���Ϣ�л��з���ʹ��");
					article1.setDescription("");
					article1.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article1.setUrl("https://github.com/jior/glaf");

					Article article2 = new Article();
					article2.setTitle("��2ƪ\n΢�Ź����ʺŵ�����");
					article2.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
					article2.setPicUrl("https://github.com/jior/glaf/blob/master/workspace/glaf-web/WebContent/images/logo.png");
					article2.setUrl("https://github.com/jior/glaf");

					Article article3 = new Article();
					article3.setTitle("��3ƪ\n����ģʽ���ü��ӿ�����");
					article3.setTitle("΢�Ź����ʺſ����̳�Java��");
					article3.setDescription("GLAF����ҵ����Ӧ�ÿ�����ܡ�" + emoji(0x1F6B9));
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