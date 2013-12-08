package com.glaf.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.domain.WxFollower;
import com.glaf.wechat.service.WxFollowerService;
import com.glaf.wechat.service.WxUserService;

public class WxFollowerThread extends Thread {
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
			String subscribe_get_url, String token, String openId) {
		this.accountId = accountId;
		this.actorId = actorId;
		this.subscribe_get_url = subscribe_get_url;
		this.token = token;
		this.openId = openId;
	}

	public void run() {
		JSONObject jsonObject = WechatUtils.getFollower(subscribe_get_url,
				token, openId);
		if (jsonObject != null) {
			boolean success = false;
			int retry = 0;
			while (retry < 3 && !success) {
				retry++;
				try {
					WxFollower bean = new WxFollower();
					bean.setAccountId(accountId);
					bean.setActorId(actorId);
					bean.setCity(jsonObject.getString("city"));
					bean.setCountry(jsonObject.getString("country"));
					bean.setHeadimgurl(jsonObject.getString("headimgurl"));
					bean.setLanguage(jsonObject.getString("language"));
					bean.setNickName(jsonObject.getString("nickname"));
					bean.setOpenId(openId);
					bean.setProvince(jsonObject.getString("province"));
					bean.setSex(jsonObject.getString("sex"));
					bean.setSubscribeTime(jsonObject.getLong("subscribe_time"));
					getWxFollowerService().save(bean);
					retry = 5;
					success = true;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}
