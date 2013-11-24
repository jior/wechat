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

package com.glaf.apps.member.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.apps.member.domain.WxMember;
import com.glaf.core.util.DateUtils;

public class WxMemberJsonFactory {

	public static java.util.List<WxMember> arrayToList(JSONArray array) {
		java.util.List<WxMember> list = new java.util.ArrayList<WxMember>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxMember model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	public static WxMember jsonToObject(JSONObject jsonObject) {
		WxMember model = new WxMember();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("accountId")) {
			model.setAccountId(jsonObject.getLong("accountId"));
		}
		if (jsonObject.containsKey("cardNo")) {
			model.setCardNo(jsonObject.getString("cardNo"));
		}
		if (jsonObject.containsKey("name")) {
			model.setName(jsonObject.getString("name"));
		}
		if (jsonObject.containsKey("telephone")) {
			model.setTelephone(jsonObject.getString("telephone"));
		}
		if (jsonObject.containsKey("mobile")) {
			model.setMobile(jsonObject.getString("mobile"));
		}
		if (jsonObject.containsKey("mail")) {
			model.setMail(jsonObject.getString("mail"));
		}
		if (jsonObject.containsKey("qq")) {
			model.setQq(jsonObject.getString("qq"));
		}
		if (jsonObject.containsKey("address")) {
			model.setAddress(jsonObject.getString("address"));
		}
		if (jsonObject.containsKey("balance")) {
			model.setBalance(jsonObject.getDouble("balance"));
		}
		if (jsonObject.containsKey("status")) {
			model.setStatus(jsonObject.getInteger("status"));
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

	public static JSONArray listToArray(java.util.List<WxMember> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxMember model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static JSONObject toJsonObject(WxMember model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getAccountId() != null) {
			jsonObject.put("accountId", model.getAccountId());
		}
		if (model.getCardNo() != null) {
			jsonObject.put("cardNo", model.getCardNo());
		}
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
		}
		if (model.getTelephone() != null) {
			jsonObject.put("telephone", model.getTelephone());
		}
		if (model.getMobile() != null) {
			jsonObject.put("mobile", model.getMobile());
		}
		if (model.getMail() != null) {
			jsonObject.put("mail", model.getMail());
		}
		if (model.getQq() != null) {
			jsonObject.put("qq", model.getQq());
		}
		if (model.getAddress() != null) {
			jsonObject.put("address", model.getAddress());
		}
		jsonObject.put("balance", model.getBalance());
		jsonObject.put("status", model.getStatus());
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

	public static ObjectNode toObjectNode(WxMember model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getAccountId() != null) {
			jsonObject.put("accountId", model.getAccountId());
		}
		if (model.getCardNo() != null) {
			jsonObject.put("cardNo", model.getCardNo());
		}
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
		}
		if (model.getTelephone() != null) {
			jsonObject.put("telephone", model.getTelephone());
		}
		if (model.getMobile() != null) {
			jsonObject.put("mobile", model.getMobile());
		}
		if (model.getMail() != null) {
			jsonObject.put("mail", model.getMail());
		}
		if (model.getQq() != null) {
			jsonObject.put("qq", model.getQq());
		}
		if (model.getAddress() != null) {
			jsonObject.put("address", model.getAddress());
		}
		jsonObject.put("balance", model.getBalance());
		jsonObject.put("status", model.getStatus());
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

	private WxMemberJsonFactory() {

	}

}
