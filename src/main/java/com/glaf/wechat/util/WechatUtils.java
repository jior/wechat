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
 * 公众平台通用接口工具类
 * 
 * @author jior
 */
public class WechatUtils {
	private static final Logger log = LoggerFactory
			.getLogger(WechatUtils.class);

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(String menu_create_url, Menu menu,
			String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = menu.toJSONObject().toJSONString();
		// 调用接口创建菜单
		JSONObject jsonObject = HttpClientUtils.executeRequest(url, "POST",
				jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInteger("errcode")) {
				result = jsonObject.getInteger("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}",
						jsonObject.getInteger("errcode"),
						jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	/**
	 * 获取access_token
	 * 
	 * @param accountId
	 *            凭证
	 * @param appSecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String access_token_url,
			String accountId, String appSecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", accountId)
				.replace("APPSECRET", appSecret);
		JSONObject jsonObject = HttpClientUtils.executeRequest(requestUrl,
				"GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			log.debug(jsonObject.toJSONString());
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
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
	 * 获取关注者
	 * 
	 * @param accessToken
	 *            有效的access_token
	 * @return 关注者JSON
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
	 * 获取关注者列表
	 * 
	 * @param accessToken
	 *            有效的access_token
	 * @return 关注者列表JSON
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
	 * 获取菜单
	 * 
	 * @param accessToken
	 *            有效的access_token
	 * @return 菜单实例JSON
	 */
	public static JSONObject getMenu(String orgi_url, String accessToken) {
		String url = orgi_url.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
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