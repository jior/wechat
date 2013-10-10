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

package com.glaf.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.glaf.core.context.ContextFactory;
import com.glaf.core.identity.User;
import com.glaf.core.security.IdentityFactory;
import com.glaf.wechat.message.BaseMessage;
import com.glaf.wechat.message.request.ImageMessage;
import com.glaf.wechat.message.response.Article;
import com.glaf.wechat.message.response.NewsMessage;
import com.glaf.wechat.message.response.TextMessage;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.util.SignUtils;
import com.glaf.wechat.util.TimeUtils;

/**
 * ������������
 * 
 * @author jior
 */
public class WechatCoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	protected static final Log logger = LogFactory
			.getLog(WechatCoreServlet.class);

	/**
	 * emoji����ת��(hex -> utf-16)
	 * 
	 * @param
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	protected WxContentService wxContentService;

	protected String convertMsg(BaseMessage msg, String fromUser,
			String localUser) {
		String type = msg.getType();
		if ("text".equals(type)) {
			return getText((TextMessage) msg, fromUser, localUser);
		} else if ("image".equals(type)) {
			return getImage((ImageMessage) msg, fromUser, localUser);
		} else if ("news".equals(type)) {
			return getNews((NewsMessage) msg, fromUser, localUser);
		}
		return "";
	}

	/**
	 * ȷ����������΢�ŷ�����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * ����΢�ŷ�������������Ϣ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��Ϣ�Ľ��ա�������Ӧ
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// ΢�ż���ǩ��
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");

		// ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��
		if (SignUtils.checkSignature(signature, timestamp, nonce)) {
			logger.debug("WEIXIN SIGN END!");
			// echostr��ֵ�򷵻أ�Ϊ����Ϊ�û�POST���󣬸���KEYʱ�䷵�ر���
			if (echostr != null && !echostr.equals("")) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.flush();
				out.close();
				out = null;
			} else {
				this.process(request, response);
			}
		} else {
			logger.error("WEIXIN TOKEN SIGN ERROR!");
		}
	}

	protected String getImage(ImageMessage msg, String fromUser,
			String localUser) {
		StringBuffer returnXML = new StringBuffer();
		returnXML.append("<xml>");
		returnXML.append("<ToUserName><![CDATA[" + fromUser
				+ "]]></ToUserName>");
		returnXML.append("<FromUserName><![CDATA[" + localUser
				+ "]]></FromUserName>");
		returnXML.append("<CreateTime>" + TimeUtils.getCurrentTime()
				+ "</CreateTime>");
		returnXML.append("<MsgType><![CDATA[image]]></MsgType>");
		returnXML.append("<Content><![CDATA[" + msg.getContent()
				+ "]]></Content>");
		returnXML
				.append("<PicUrl><![CDATA[" + msg.getPicUrl() + "]]></PicUrl>");
		returnXML.append("<FuncFlag>0</FuncFlag>");
		returnXML.append("</xml>");
		return returnXML.toString();
	}

	protected String getNews(NewsMessage msg, String fromUser, String localUser) {
		StringBuffer returnXML = new StringBuffer();
		returnXML.append("<xml>");
		returnXML.append("<ToUserName><![CDATA[" + fromUser
				+ "]]></ToUserName>");
		returnXML.append("<FromUserName><![CDATA[" + localUser
				+ "]]></FromUserName>");
		returnXML.append("<CreateTime>" + TimeUtils.getCurrentTime()
				+ "</CreateTime>");

		int size = msg.getArticleCount();
		StringBuffer artStr = new StringBuffer();
		artStr.append("<MsgType><![CDATA[news]]></MsgType>");
		artStr.append("<ArticleCount>" + size + "</ArticleCount>");
		artStr.append("<Articles>");
		List<Article> articles = msg.getArticles();
		for (int i = 0; i < articles.size(); i++) {
			Article art = articles.get(0);
			artStr.append("<item>");
			artStr.append("<Title><![CDATA[" + art.getTitle() + "]]></Title>");
			artStr.append("<Description><![CDATA[" + art.getDescription()
					+ "]]></Description>");
			artStr.append("<PicUrl><![CDATA[" + art.getPicUrl()
					+ "]]></PicUrl>");
			artStr.append("<Url><![CDATA[" + art.getUrl() + "]]></Url>");
			artStr.append("</item>");
		}
		artStr.append("</Articles>");
		returnXML.append(artStr.toString());
		returnXML.append("<FuncFlag>1</FuncFlag>");
		returnXML.append("</xml>");
		return returnXML.toString();
	}

	protected String getText(TextMessage msg, String fromUser, String localUser) {
		StringBuffer returnXML = new StringBuffer();
		returnXML.append("<xml>");
		returnXML.append("<ToUserName><![CDATA[" + fromUser
				+ "]]></ToUserName>");
		returnXML.append("<FromUserName><![CDATA[" + localUser
				+ "]]></FromUserName>");
		returnXML.append("<CreateTime>" + TimeUtils.getCurrentTime()
				+ "</CreateTime>");
		returnXML.append("<MsgType><![CDATA[text]]></MsgType>");
		returnXML.append("<Content><![CDATA[" + msg.getContent()
				+ "]]></Content>");
		returnXML.append("<FuncFlag>0</FuncFlag>");
		returnXML.append("</xml>");
		return returnXML.toString();
	}

	public WxContentService getWxContentService() {
		if (wxContentService == null) {
			wxContentService = ContextFactory.getBean("wxContentService");
		}
		return wxContentService;
	}

	private BaseMessage menuEvent(String eventKey, String customer,
			String msgType) {
		return null;
	}

	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public void process(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		String uri = request.getRequestURI();
		String customer = uri.substring(uri.lastIndexOf("/") + 1);
		logger.debug("******************************WEIXIN POST BEGIN**********************************");
		try {
			out = response.getWriter();
			SAXReader reader = new SAXReader();
			Document doc = reader.read(request.getInputStream());
			logger.debug("******************************XML DATA READ BEGIN**********************************");
			logger.debug(doc.asXML());

			Element root = doc.getRootElement();
			String fromUserName = root.elementText("FromUserName");
			String toUserName = root.elementText("ToUserName");
			String msgType = root.elementText("MsgType");
			BaseMessage msg = null;
			User user = IdentityFactory.getUser(fromUserName);
			if (doc.asXML().indexOf("EventKey") > -1) {
				String key = root.elementText("Event");
				if (key.equals("subscribe")) {
					msg = subscribe(user, customer, msgType); // �����¼�
				} else if (key.equals("CLICK")) { // �Զ���˵�����¼�
					msg = menuEvent(root.elementText("EventKey"), customer,
							msgType);
				}
			} else if (doc.asXML().indexOf("MsgId") > -1) { // �ж��Ƿ����û�������Ϣ
				if (doc.asXML().indexOf("text") > -1) { // �û������ı���Ϣ
					String content = root.elementText("Content");
					if ("0".equals(content) || "?".equals(content)
							|| "��".equals(content)) {
						msg = subscribe(user, customer, msgType);
					} else {

					}
				}
			}
			if (msg == null) {
				msg = subscribe(user, customer, msgType);
			}
			String responseXML = convertMsg(msg, fromUserName, toUserName);
			logger.debug(responseXML);
			out.write(responseXML);
			out.flush();
		} catch (Exception ex) {
			logger.error("WEIXIN POST ERROR!");
			logger.error(ex);
			ex.printStackTrace();
		}
	}

	private BaseMessage subscribe(User user, String customer, String msgType) {
		BaseMessage result = null;

		return result;
	}

}
