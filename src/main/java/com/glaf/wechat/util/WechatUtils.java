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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.util.HashUtils;
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

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 菜单获取
	public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = menu.toJSONObject().toJSONString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

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
	 * @param appId
	 *            凭证
	 * @param appSecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appId, String appSecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appId).replace(
				"APPSECRET", appSecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
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

	/**
	 * 获取哈希分区后的路径
	 * 
	 * @param userId
	 * @return
	 */
	public static String getHashedPath(String userId) {
		String path = Math.abs(HashUtils.FNVHash1(userId) % 1024) + "/"
				+ DigestUtils.md5Hex(userId);
		return path;
	}

	public static String getCategoryJsonSavePath(String userId) {
		String path = Constants.PUBLISH_JSON_PATH + getHashedPath(userId);
		return path;
	}

	public static String getContentJsonSavePath(String userId) {
		String path = Constants.PUBLISH_JSON_PATH + getHashedPath(userId);
		return path;
	}

	/**
	 * 获取菜单
	 * 
	 * @param accessToken
	 *            有效的access_token
	 * @return 菜单实例JSON
	 */
	public static JSONObject getMenu(String accessToken) {
		String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);
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

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		HttpsURLConnection httpUrlConn = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			log.debug("requestUrl:" + requestUrl);
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;

			log.debug("response:" + buffer.toString());

			jsonObject = JSON.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			log.error("Weixin server connection timed out.");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("https request error:{}", ex);
		} finally {
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
			}
		}
		return jsonObject;
	}
}