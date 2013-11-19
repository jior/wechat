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

public class WxUserJsonFactory {

	public static WxUser jsonToObject(JSONObject jsonObject) {
		WxUser model = new WxUser();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		 
		if (jsonObject.containsKey("actorId")) {
			model.setActorId(jsonObject.getString("actorId"));
		}
		if (jsonObject.containsKey("wxid")) {
			model.setWxid(jsonObject.getString("wxid"));
		}
		if (jsonObject.containsKey("wxSourceId")) {
			model.setWxSourceId(jsonObject.getString("wxSourceId"));
		}
		if (jsonObject.containsKey("wxname")) {
			model.setWxname(jsonObject.getString("wxname"));
		}
		if (jsonObject.containsKey("wxHeadImage")) {
			model.setWxHeadImage(jsonObject.getString("wxHeadImage"));
		}
		if (jsonObject.containsKey("yxid")) {
			model.setYxid(jsonObject.getString("yxid"));
		}
		if (jsonObject.containsKey("yxSourceId")) {
			model.setYxSourceId(jsonObject.getString("yxSourceId"));
		}
		if (jsonObject.containsKey("yxname")) {
			model.setYxname(jsonObject.getString("yxname"));
		}
		if (jsonObject.containsKey("yxHeadImage")) {
			model.setYxHeadImage(jsonObject.getString("yxHeadImage"));
		}
		if (jsonObject.containsKey("token")) {
			model.setToken(jsonObject.getString("token"));
		}
		if (jsonObject.containsKey("province")) {
			model.setProvince(jsonObject.getString("province"));
		}
		if (jsonObject.containsKey("city")) {
			model.setCity(jsonObject.getString("city"));
		}
		if (jsonObject.containsKey("area")) {
			model.setArea(jsonObject.getString("area"));
		}
		if (jsonObject.containsKey("name")) {
			model.setName(jsonObject.getString("name"));
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
		if (jsonObject.containsKey("userType")) {
			model.setUserType(jsonObject.getInteger("userType"));
		}
		if (jsonObject.containsKey("accountType")) {
			model.setAccountType(jsonObject.getInteger("accountType"));
		}
		if (jsonObject.containsKey("deptId")) {
			model.setDeptId(jsonObject.getLong("deptId"));
		}
		if (jsonObject.containsKey("type")) {
			model.setType(jsonObject.getString("type"));
		}
		if (jsonObject.containsKey("locked")) {
			model.setLocked(jsonObject.getInteger("locked"));
		}
		 
		if (jsonObject.containsKey("remark")) {
			model.setRemark(jsonObject.getString("remark"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}
		if (jsonObject.containsKey("endDate")) {
			model.setEndDate(jsonObject.getDate("endDate"));
		}

		return model;
	}

	public static JSONObject toJsonObject(WxUser model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
	 
		
		if (model.getActorId() != null) {
			jsonObject.put("actorId", model.getActorId());
		}
		if (model.getWxid() != null) {
			jsonObject.put("wxid", model.getWxid());
		}
		if (model.getWxSourceId() != null) {
			jsonObject.put("wxSourceId", model.getWxSourceId());
		}
		if (model.getWxname() != null) {
			jsonObject.put("wxname", model.getWxname());
		}
		if (model.getWxHeadImage() != null) {
			jsonObject.put("wxHeadImage", model.getWxHeadImage());
		}
		if (model.getYxid() != null) {
			jsonObject.put("yxid", model.getYxid());
		}
		if (model.getYxSourceId() != null) {
			jsonObject.put("yxSourceId", model.getYxSourceId());
		}
		if (model.getYxname() != null) {
			jsonObject.put("yxname", model.getYxname());
		}
		if (model.getYxHeadImage() != null) {
			jsonObject.put("yxHeadImage", model.getYxHeadImage());
		}
		if (model.getToken() != null) {
			jsonObject.put("token", model.getToken());
		}
		if (model.getProvince() != null) {
			jsonObject.put("province", model.getProvince());
		}
		if (model.getCity() != null) {
			jsonObject.put("city", model.getCity());
		}
		if (model.getArea() != null) {
			jsonObject.put("area", model.getArea());
		}
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
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
		jsonObject.put("userType", model.getUserType());
		jsonObject.put("accountType", model.getAccountType());
		jsonObject.put("deptId", model.getDeptId());
		if (model.getType() != null) {
			jsonObject.put("type", model.getType());
		}
		jsonObject.put("locked", model.getLocked());
		 
		if (model.getRemark() != null) {
			jsonObject.put("remark", model.getRemark());
		}
		if (model.getCreateDate() != null) {
			jsonObject.put("createDate",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_date",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_datetime",
					DateUtils.getDateTime(model.getCreateDate()));
		}
		if (model.getEndDate() != null) {
			jsonObject.put("endDate", DateUtils.getDate(model.getEndDate()));
			jsonObject.put("endDate_date",
					DateUtils.getDate(model.getEndDate()));
			jsonObject.put("endDate_datetime",
					DateUtils.getDateTime(model.getEndDate()));
		}
		return jsonObject;
	}

	public static ObjectNode toObjectNode(WxUser model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
	 
		if (model.getActorId() != null) {
			jsonObject.put("actorId", model.getActorId());
		}
		if (model.getWxid() != null) {
			jsonObject.put("wxid", model.getWxid());
		}
		if (model.getWxSourceId() != null) {
			jsonObject.put("wxSourceId", model.getWxSourceId());
		}
		if (model.getWxname() != null) {
			jsonObject.put("wxname", model.getWxname());
		}
		if (model.getWxHeadImage() != null) {
			jsonObject.put("wxHeadImage", model.getWxHeadImage());
		}
		if (model.getYxid() != null) {
			jsonObject.put("yxid", model.getYxid());
		}
		if (model.getYxSourceId() != null) {
			jsonObject.put("yxSourceId", model.getYxSourceId());
		}
		if (model.getYxname() != null) {
			jsonObject.put("yxname", model.getYxname());
		}
		if (model.getYxHeadImage() != null) {
			jsonObject.put("yxHeadImage", model.getYxHeadImage());
		}
		if (model.getToken() != null) {
			jsonObject.put("token", model.getToken());
		}
		if (model.getProvince() != null) {
			jsonObject.put("province", model.getProvince());
		}
		if (model.getCity() != null) {
			jsonObject.put("city", model.getCity());
		}
		if (model.getArea() != null) {
			jsonObject.put("area", model.getArea());
		}
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
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
		jsonObject.put("userType", model.getUserType());
		jsonObject.put("accountType", model.getAccountType());
		jsonObject.put("deptId", model.getDeptId());
		if (model.getType() != null) {
			jsonObject.put("type", model.getType());
		}
		jsonObject.put("locked", model.getLocked());
		 
		if (model.getRemark() != null) {
			jsonObject.put("remark", model.getRemark());
		}
		if (model.getCreateDate() != null) {
			jsonObject.put("createDate",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_date",
					DateUtils.getDate(model.getCreateDate()));
			jsonObject.put("createDate_datetime",
					DateUtils.getDateTime(model.getCreateDate()));
		}
		if (model.getEndDate() != null) {
			jsonObject.put("endDate", DateUtils.getDate(model.getEndDate()));
			jsonObject.put("endDate_date",
					DateUtils.getDate(model.getEndDate()));
			jsonObject.put("endDate_datetime",
					DateUtils.getDateTime(model.getEndDate()));
		}
		return jsonObject;
	}

	public static JSONArray listToArray(java.util.List<WxUser> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxUser model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static java.util.List<WxUser> arrayToList(JSONArray array) {
		java.util.List<WxUser> list = new java.util.ArrayList<WxUser>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxUser model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	private WxUserJsonFactory() {

	}

}
