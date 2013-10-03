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
import com.glaf.wechat.domain.WxMessage;

public class WxMessageJsonFactory {

	public static java.util.List<WxMessage> arrayToList(JSONArray array) {
		java.util.List<WxMessage> list = new java.util.ArrayList<WxMessage>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxMessage model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	public static WxMessage jsonToObject(JSONObject jsonObject) {
		WxMessage model = new WxMessage();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("name")) {
			model.setName(jsonObject.getString("name"));
		}
		if (jsonObject.containsKey("mobile")) {
			model.setMobile(jsonObject.getString("mobile"));
		}
		if (jsonObject.containsKey("title")) {
			model.setTitle(jsonObject.getString("title"));
		}
		if (jsonObject.containsKey("content")) {
			model.setContent(jsonObject.getString("content"));
		}
		if (jsonObject.containsKey("uuid")) {
			model.setUuid(jsonObject.getString("uuid"));
		}
		if (jsonObject.containsKey("createBy")) {
			model.setCreateBy(jsonObject.getString("createBy"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}

		return model;
	}

	public static JSONArray listToArray(java.util.List<WxMessage> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxMessage model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static JSONObject toJsonObject(WxMessage model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
		}
		if (model.getMobile() != null) {
			jsonObject.put("mobile", model.getMobile());
		}
		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getUuid() != null) {
			jsonObject.put("uuid", model.getUuid());
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

	public static ObjectNode toObjectNode(WxMessage model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
		}
		if (model.getMobile() != null) {
			jsonObject.put("mobile", model.getMobile());
		}
		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getUuid() != null) {
			jsonObject.put("uuid", model.getUuid());
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

	private WxMessageJsonFactory() {

	}

}
