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

package com.glaf.wechat.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.util.StringTools;
import com.glaf.wechat.component.Menu;
import com.glaf.wechat.model.AccessToken;

/**
 * ����ƽ̨ͨ�ýӿڹ�����
 * 
 * @author jior
 */
public class WechatUtils {
	private static final Logger log = LoggerFactory
			.getLogger(WechatUtils.class);

	/**
	 * �����˵�
	 * 
	 * @param menu
	 *            �˵�ʵ��
	 * @param accessToken
	 *            ��Ч��access_token
	 * @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	 */
	public static int createMenu(String menu_create_url, Menu menu,
			String accessToken) {
		int result = 0;

		// ƴװ�����˵���url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// ���˵�����ת����json�ַ���
		String jsonMenu = menu.toJSONObject().toJSONString();
		// ���ýӿڴ����˵�
		JSONObject jsonObject = HttpClientUtils.executeRequest(url, "POST",
				jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInteger("errcode")) {
				result = jsonObject.getInteger("errcode");
				log.error("�����˵�ʧ�� errcode:{} errmsg:{}",
						jsonObject.getInteger("errcode"),
						jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	/**
	 * ��ȡaccess_token
	 * 
	 * @param accountId
	 *            ƾ֤
	 * @param appSecret
	 *            ��Կ
	 * @return
	 */
	public static AccessToken getAccessToken(String access_token_url,
			String accountId, String appSecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", accountId)
				.replace("APPSECRET", appSecret);
		JSONObject jsonObject = HttpClientUtils.executeRequest(requestUrl,
				"GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			log.debug(jsonObject.toJSONString());
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// ��ȡtokenʧ��
				log.error("��ȡtokenʧ�� errcode:{} errmsg:{}",
						jsonObject.getInteger("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	public static String getCategoryJsonSavePath(Long userId, Long acctountId) {
		String path = Constants.PUBLISH_JSON_PATH
				+ getImagePath(userId, acctountId);
		return path;
	}

	public static String getContentJsonSavePath(Long userId, Long acctountId) {
		String path = Constants.PUBLISH_JSON_PATH
				+ getImagePath(userId, acctountId);
		return path;
	}

	/**
	 * ��ȡ��ע��
	 * 
	 * @param accessToken
	 *            ��Ч��access_token
	 * @return ��ע��JSON
	 */
	public static JSONObject getFollower(String orgi_url, String accessToken,
			String openid) {
		String url = orgi_url.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("OPENID", openid);
		JSONObject jsonObject = HttpClientUtils
				.executeRequest(url, "GET", null);
		return jsonObject;
	}

	/**
	 * ��ȡ��ע���б�
	 * 
	 * @param accessToken
	 *            ��Ч��access_token
	 * @return ��ע���б�JSON
	 */
	public static JSONObject getFollowers(String orgi_url, String accessToken,
			String next_openid) {
		String url = orgi_url.replace("ACCESS_TOKEN", accessToken);
		if (StringUtils.isNotEmpty(next_openid)) {
			url = url.replace("NEXT_OPENID", next_openid);
		} else {
			url = StringTools.replace(url, "&next_openid=NEXT_OPENID", "");
		}
		JSONObject jsonObject = HttpClientUtils
				.executeRequest(url, "GET", null);
		return jsonObject;
	}

	public static String getImagePath(Long userId, Long acctountId) {
		String path = String.valueOf(userId) + "/" + String.valueOf(acctountId);
		return path;
	}

	/**
	 * ��ȡ�˵�
	 * 
	 * @param accessToken
	 *            ��Ч��access_token
	 * @return �˵�ʵ��JSON
	 */
	public static JSONObject getMenu(String orgi_url, String accessToken) {
		String url = orgi_url.replace("ACCESS_TOKEN", accessToken);
		// ���ýӿڴ����˵�
		JSONObject jsonObject = HttpClientUtils
				.executeRequest(url, "GET", null);
		return jsonObject;
	}

	public static String getServiceUrl(HttpServletRequest request) {
		String serviceUrl = "http://" + request.getServerName();
		if (request.getServerPort() != 80) {
			serviceUrl += ":" + request.getServerPort();
		}
		if (!"/".equals(request.getContextPath())) {
			serviceUrl += request.getContextPath();
		}
		return serviceUrl;
	}

}