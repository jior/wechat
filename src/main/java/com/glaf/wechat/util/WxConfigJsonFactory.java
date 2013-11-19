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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.util.DateUtils;
import com.glaf.wechat.domain.WxConfig;

public class WxConfigJsonFactory {

	public static java.util.List<WxConfig> arrayToList(JSONArray array) {
		java.util.List<WxConfig> list = new java.util.ArrayList<WxConfig>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxConfig model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	public static WxConfig jsonToObject(JSONObject jsonObject) {
		WxConfig model = new WxConfig();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("accountId")) {
			model.setAccountId(jsonObject.getLong("accountId"));
		}
		if (jsonObject.containsKey("callBackUrl")) {
			model.setCallBackUrl(jsonObject.getString("callBackUrl"));
		}
		if (jsonObject.containsKey("token")) {
			model.setToken(jsonObject.getString("token"));
		}
		if (jsonObject.containsKey("accountId")) {
			model.setAccountId(jsonObject.getLong("accountId"));
		}
		if (jsonObject.containsKey("wxAppId")) {
			model.setWxAppId(jsonObject.getString("wxAppId"));
		}
		if (jsonObject.containsKey("wxAppSecret")) {
			model.setWxAppSecret(jsonObject.getString("wxAppSecret"));
		}
		if (jsonObject.containsKey("yxAppId")) {
			model.setYxAppId(jsonObject.getString("yxAppId"));
		}
		if (jsonObject.containsKey("yxAppSecret")) {
			model.setYxAppSecret(jsonObject.getString("yxAppSecret"));
		}
		if (jsonObject.containsKey("apiStatus")) {
			model.setApiStatus(jsonObject.getString("apiStatus"));
		}
		if (jsonObject.containsKey("defaultReply")) {
			model.setDefaultReply(jsonObject.getString("defaultReply"));
		}
		if (jsonObject.containsKey("createBy")) {
			model.setCreateBy(jsonObject.getString("createBy"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}
		if (jsonObject.containsKey("lbsPosition")) {
			model.setLbsPosition(jsonObject.getInteger("lbsPosition"));
		}

		return model;
	}

	public static JSONArray listToArray(java.util.List<WxConfig> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxConfig model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static JSONObject toJsonObject(WxConfig model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getCallBackUrl() != null) {
			jsonObject.put("callBackUrl", model.getCallBackUrl());
		}
		if (model.getToken() != null) {
			jsonObject.put("token", model.getToken());
		}
		if (model.getAccountId() != null) {
			jsonObject.put("accountId", model.getAccountId());
		}
		if (model.getWxAppId() != null) {
			jsonObject.put("wxAppId", model.getWxAppId());
		}
		if (model.getWxAppSecret() != null) {
			jsonObject.put("wxAppSecret", model.getWxAppSecret());
		}
		if (model.getYxAppId() != null) {
			jsonObject.put("yxAppId", model.getYxAppId());
		}
		if (model.getYxAppSecret() != null) {
			jsonObject.put("yxAppSecret", model.getYxAppSecret());
		}
		if (model.getApiStatus() != null) {
			jsonObject.put("apiStatus", model.getApiStatus());
		}
		if (model.getDefaultReply() != null) {
			jsonObject.put("defaultReply", model.getDefaultReply());
		}
		if (model.getLbsPosition() != null) {
			jsonObject.put("lbsPosition", model.getLbsPosition());
		}
		if (model.getCreateBy() != null) {
			jsonObject.put("createBy", model.getCreateBy());
		}
		if (model.getCreateDate() != null) {
			jsonObject.put("createDate",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_date",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_datetime",
					DateUtils.getDateTime(model.getCreateDate()));
		}
		if (model.getLastUpdateDate() != null) {
			jsonObject.put("lastUpdateDate",
					DateUtils.getDate(model.getLastUpdateDate()));
			jsonObject.put("lastUpdateDate_date",
					DateUtils.getDate(model.getLastUpdateDate()));
			jsonObject.put("lastUpdateDate_datetime",
					DateUtils.getDateTime(model.getLastUpdateDate()));
		}
		if (model.getLastUpdateBy() != null) {
			jsonObject.put("lastUpdateBy", model.getLastUpdateBy());
		}
		return jsonObject;
	}

	public static ObjectNode toObjectNode(WxConfig model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getCallBackUrl() != null) {
			jsonObject.put("callBackUrl", model.getCallBackUrl());
		}
		if (model.getToken() != null) {
			jsonObject.put("token", model.getToken());
		}
		if (model.getAccountId() != null) {
			jsonObject.put("accountId", model.getAccountId());
		}
		if (model.getWxAppId() != null) {
			jsonObject.put("wxAppId", model.getWxAppId());
		}
		if (model.getWxAppSecret() != null) {
			jsonObject.put("wxAppSecret", model.getWxAppSecret());
		}
		if (model.getYxAppId() != null) {
			jsonObject.put("yxAppId", model.getYxAppId());
		}
		if (model.getYxAppSecret() != null) {
			jsonObject.put("yxAppSecret", model.getYxAppSecret());
		}
		if (model.getApiStatus() != null) {
			jsonObject.put("apiStatus", model.getApiStatus());
		}
		if (model.getDefaultReply() != null) {
			jsonObject.put("defaultReply", model.getDefaultReply());
		}
		if (model.getLbsPosition() != null) {
			jsonObject.put("lbsPosition", model.getLbsPosition());
		}
		if (model.getCreateBy() != null) {
			jsonObject.put("createBy", model.getCreateBy());
		}
		if (model.getCreateDate() != null) {
			jsonObject.put("createDate",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_date",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_datetime",
					DateUtils.getDateTime(model.getCreateDate()));
		}
		if (model.getLastUpdateDate() != null) {
			jsonObject.put("lastUpdateDate",
					DateUtils.getDate(model.getLastUpdateDate()));
			jsonObject.put("lastUpdateDate_date",
					DateUtils.getDate(model.getLastUpdateDate()));
			jsonObject.put("lastUpdateDate_datetime",
					DateUtils.getDateTime(model.getLastUpdateDate()));
		}
		if (model.getLastUpdateBy() != null) {
			jsonObject.put("lastUpdateBy", model.getLastUpdateBy());
		}
		return jsonObject;
	}

	private WxConfigJsonFactory() {

	}

}
