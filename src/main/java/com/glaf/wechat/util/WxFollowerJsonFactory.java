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

import com.alibaba.fastjson.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.util.DateUtils;
import com.glaf.wechat.domain.*;

public class WxFollowerJsonFactory {

	public static java.util.List<WxFollower> arrayToList(JSONArray array) {
		java.util.List<WxFollower> list = new java.util.ArrayList<WxFollower>();
		for (int i = 0, len = array.size(); i < len; i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxFollower model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	public static WxFollower jsonToObject(JSONObject jsonObject) {
		WxFollower model = new WxFollower();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("accountId")) {
			model.setAccountId(jsonObject.getLong("accountId"));
		}
		if (jsonObject.containsKey("actorId")) {
			model.setActorId(jsonObject.getString("actorId"));
		}
		if (jsonObject.containsKey("openId")) {
			model.setOpenId(jsonObject.getString("openId"));
		}
		if (jsonObject.containsKey("nickName")) {
			model.setNickName(jsonObject.getString("nickName"));
		}
		if (jsonObject.containsKey("sex")) {
			model.setSex(jsonObject.getString("sex"));
		}
		if (jsonObject.containsKey("mobile")) {
			model.setMobile(jsonObject.getString("mobile"));
		}
		if (jsonObject.containsKey("mail")) {
			model.setMail(jsonObject.getString("mail"));
		}
		if (jsonObject.containsKey("telephone")) {
			model.setTelephone(jsonObject.getString("telephone"));
		}
		if (jsonObject.containsKey("headimgurl")) {
			model.setHeadimgurl(jsonObject.getString("headimgurl"));
		}
		if (jsonObject.containsKey("province")) {
			model.setProvince(jsonObject.getString("province"));
		}
		if (jsonObject.containsKey("city")) {
			model.setCity(jsonObject.getString("city"));
		}
		if (jsonObject.containsKey("country")) {
			model.setCountry(jsonObject.getString("country"));
		}
		if (jsonObject.containsKey("language")) {
			model.setLanguage(jsonObject.getString("language"));
		}
		if (jsonObject.containsKey("locked")) {
			model.setLocked(jsonObject.getInteger("locked"));
		}
		if (jsonObject.containsKey("remark")) {
			model.setRemark(jsonObject.getString("remark"));
		}
		if (jsonObject.containsKey("subscribeTime")) {
			model.setSubscribeTime(jsonObject.getLong("subscribeTime"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}

		return model;
	}

	public static JSONArray listToArray(java.util.List<WxFollower> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxFollower model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static JSONObject toJsonObject(WxFollower model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		jsonObject.put("accountId", model.getAccountId());
		if (model.getActorId() != null) {
			jsonObject.put("actorId", model.getActorId());
		}
		if (model.getOpenId() != null) {
			jsonObject.put("openId", model.getOpenId());
		}
		if (model.getNickName() != null) {
			jsonObject.put("nickName", model.getNickName());
		}
		if (model.getSex() != null) {
			jsonObject.put("sex", model.getSex());
		}
		if (model.getMobile() != null) {
			jsonObject.put("mobile", model.getMobile());
		}
		if (model.getMail() != null) {
			jsonObject.put("mail", model.getMail());
		}
		if (model.getTelephone() != null) {
			jsonObject.put("telephone", model.getTelephone());
		}
		if (model.getHeadimgurl() != null) {
			jsonObject.put("headimgurl", model.getHeadimgurl());
		}
		if (model.getProvince() != null) {
			jsonObject.put("province", model.getProvince());
		}
		if (model.getCity() != null) {
			jsonObject.put("city", model.getCity());
		}
		if (model.getCountry() != null) {
			jsonObject.put("country", model.getCountry());
		}
		if (model.getLanguage() != null) {
			jsonObject.put("language", model.getLanguage());
		}
		jsonObject.put("locked", model.getLocked());
		if (model.getRemark() != null) {
			jsonObject.put("remark", model.getRemark());
		}
		jsonObject.put("subscribeTime", model.getSubscribeTime());
		if (model.getCreateDate() != null) {
			jsonObject.put("createDate",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_date",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_datetime",
					DateUtils.getDateTime(model.getCreateDate()));
		}
		return jsonObject;
	}

	public static ObjectNode toObjectNode(WxFollower model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		jsonObject.put("accountId", model.getAccountId());
		if (model.getActorId() != null) {
			jsonObject.put("actorId", model.getActorId());
		}
		if (model.getOpenId() != null) {
			jsonObject.put("openId", model.getOpenId());
		}
		if (model.getNickName() != null) {
			jsonObject.put("nickName", model.getNickName());
		}
		if (model.getSex() != null) {
			jsonObject.put("sex", model.getSex());
		}
		if (model.getMobile() != null) {
			jsonObject.put("mobile", model.getMobile());
		}
		if (model.getMail() != null) {
			jsonObject.put("mail", model.getMail());
		}
		if (model.getTelephone() != null) {
			jsonObject.put("telephone", model.getTelephone());
		}
		if (model.getHeadimgurl() != null) {
			jsonObject.put("headimgurl", model.getHeadimgurl());
		}
		if (model.getProvince() != null) {
			jsonObject.put("province", model.getProvince());
		}
		if (model.getCity() != null) {
			jsonObject.put("city", model.getCity());
		}
		if (model.getCountry() != null) {
			jsonObject.put("country", model.getCountry());
		}
		if (model.getLanguage() != null) {
			jsonObject.put("language", model.getLanguage());
		}
		jsonObject.put("locked", model.getLocked());
		if (model.getRemark() != null) {
			jsonObject.put("remark", model.getRemark());
		}
		jsonObject.put("subscribeTime", model.getSubscribeTime());
		if (model.getCreateDate() != null) {
			jsonObject.put("createDate",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_date",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_datetime",
					DateUtils.getDateTime(model.getCreateDate()));
		}
		return jsonObject;
	}

	private WxFollowerJsonFactory() {

	}

}
