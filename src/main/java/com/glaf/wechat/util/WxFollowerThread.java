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

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.glaf.core.context.ContextFactory;
import com.glaf.core.util.DateUtils;
import com.glaf.wechat.domain.WxFollower;
import com.glaf.wechat.service.WxFollowerService;
import com.glaf.wechat.service.WxUserService;

public class WxFollowerThread extends Thread {
	protected static final Log logger = LogFactory
			.getLog(WxFollowerThread.class);
	protected CountDownLatch latch;
	protected WxUserService wxUserService;
	protected WxFollowerService wxFollowerService;
	protected Long accountId;
	protected String actorId;
	protected String subscribe_get_url;
	protected String token;
	protected String openId;

	public WxFollowerThread() {

	}

	public WxUserService getWxUserService() {
		if (wxUserService == null) {
			wxUserService = ContextFactory.getBean("wxUserService");
		}
		return wxUserService;
	}

	public WxFollowerService getWxFollowerService() {
		if (wxFollowerService == null) {
			wxFollowerService = ContextFactory.getBean("wxFollowerService");
		}
		return wxFollowerService;
	}

	public WxFollowerThread(Long accountId, String actorId,
			String subscribe_get_url, String token, String openId,
			CountDownLatch latch) {
		this.accountId = accountId;
		this.actorId = actorId;
		this.subscribe_get_url = subscribe_get_url;
		this.token = token;
		this.openId = openId;
		this.latch = latch;
	}

	public void run() {
		try {
			ThreadCounter.add(accountId);
			WxFollower follower = getWxFollowerService().getWxFollowerByOpenId(
					accountId, openId);
			if (follower == null
					|| (System.currentTimeMillis() - follower.getLastModified() > DateUtils.DAY * 30)) {
				logger.debug("get openId from server: " + openId);
				JSONObject jsonObject = WechatUtils.getFollower(
						subscribe_get_url, token, openId);
				if (jsonObject != null) {
					WxFollower bean = new WxFollower();
					bean.setAccountId(accountId);
					bean.setActorId(actorId);
					bean.setOpenId(openId);
					bean.setSourceId(String.valueOf(accountId));
					bean.setCity(jsonObject.getString("city"));
					bean.setCountry(jsonObject.getString("country"));
					bean.setHeadimgurl(jsonObject.getString("headimgurl"));
					bean.setLanguage(jsonObject.getString("language"));
					String nickname = jsonObject.getString("nickname");
					bean.setNickName(nickname);
					bean.setProvince(jsonObject.getString("province"));
					bean.setSex(jsonObject.getString("sex"));
					bean.setSubscribeTime(jsonObject.getLong("subscribe_time"));
					getWxFollowerService().save(bean);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
			latch.countDown();
		}
	}

	public static void main(String[] args) {
		Date date = DateUtils.toDate("1970-01-01 00:00:00");
		long time = date.getTime();
		System.out.println("time:" + time);
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(1970, 0, 0);
		System.out.println("time:" + cal.getTime().getTime());
		cal.setTimeInMillis(1419173575L * 1000);
		System.out.println(DateUtils.getDateTime(cal.getTime()));
	}
}
