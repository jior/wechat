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

import com.glaf.wechat.domain.*;

public class WxModuleJsonFactory {

	public static WxModule jsonToObject(JSONObject jsonObject) {
		WxModule model = new WxModule();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("code")) {
			model.setCode(jsonObject.getString("code"));
		}
		if (jsonObject.containsKey("title")) {
			model.setTitle(jsonObject.getString("title"));
		}
		if (jsonObject.containsKey("content")) {
			model.setContent(jsonObject.getString("content"));
		}
		if (jsonObject.containsKey("link")) {
			model.setLink(jsonObject.getString("link"));
		}
		if (jsonObject.containsKey("listLink")) {
			model.setListLink(jsonObject.getString("listLink"));
		}
		if (jsonObject.containsKey("linkType")) {
			model.setLinkType(jsonObject.getString("linkType"));
		}
		if (jsonObject.containsKey("idField")) {
			model.setIdField(jsonObject.getString("idField"));
		}
		if (jsonObject.containsKey("subjectField")) {
			model.setSubjectField(jsonObject.getString("subjectField"));
		}
		if (jsonObject.containsKey("moduleId")) {
			model.setModuleId(jsonObject.getString("moduleId"));
		}
		if (jsonObject.containsKey("moduleName")) {
			model.setModuleName(jsonObject.getString("moduleName"));
		}
		if (jsonObject.containsKey("sql")) {
			model.setSql(jsonObject.getString("sql"));
		}
		if (jsonObject.containsKey("json")) {
			model.setJson(jsonObject.getString("json"));
		}
		if (jsonObject.containsKey("locked")) {
			model.setLocked(jsonObject.getInteger("locked"));
		}
		if (jsonObject.containsKey("sort")) {
			model.setSort(jsonObject.getInteger("sort"));
		}

		return model;
	}

	public static JSONObject toJsonObject(WxModule model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getCode() != null) {
			jsonObject.put("code", model.getCode());
		}
		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getLink() != null) {
			jsonObject.put("link", model.getLink());
		}
		if (model.getListLink() != null) {
			jsonObject.put("listLink", model.getListLink());
		}
		if (model.getLinkType() != null) {
			jsonObject.put("linkType", model.getLinkType());
		}
		if (model.getIdField() != null) {
			jsonObject.put("idField", model.getIdField());
		}
		if (model.getSubjectField() != null) {
			jsonObject.put("subjectField", model.getSubjectField());
		}
		if (model.getModuleId() != null) {
			jsonObject.put("moduleId", model.getModuleId());
		}
		if (model.getModuleName() != null) {
			jsonObject.put("moduleName", model.getModuleName());
		}
		if (model.getSql() != null) {
			jsonObject.put("sql", model.getSql());
		}
		if (model.getJson() != null) {
			jsonObject.put("json", model.getJson());
		}
		jsonObject.put("locked", model.getLocked());
		jsonObject.put("sort", model.getSort());
		return jsonObject;
	}

	public static ObjectNode toObjectNode(WxModule model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getCode() != null) {
			jsonObject.put("code", model.getCode());
		}
		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getLink() != null) {
			jsonObject.put("link", model.getLink());
		}
		if (model.getListLink() != null) {
			jsonObject.put("listLink", model.getListLink());
		}
		if (model.getLinkType() != null) {
			jsonObject.put("linkType", model.getLinkType());
		}
		if (model.getIdField() != null) {
			jsonObject.put("idField", model.getIdField());
		}
		if (model.getSubjectField() != null) {
			jsonObject.put("subjectField", model.getSubjectField());
		}
		if (model.getModuleId() != null) {
			jsonObject.put("moduleId", model.getModuleId());
		}
		if (model.getModuleName() != null) {
			jsonObject.put("moduleName", model.getModuleName());
		}
		if (model.getSql() != null) {
			jsonObject.put("sql", model.getSql());
		}
		if (model.getJson() != null) {
			jsonObject.put("json", model.getJson());
		}
		jsonObject.put("locked", model.getLocked());
		jsonObject.put("sort", model.getSort());
		return jsonObject;
	}

	public static JSONArray listToArray(java.util.List<WxModule> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxModule model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static java.util.List<WxModule> arrayToList(JSONArray array) {
		java.util.List<WxModule> list = new java.util.ArrayList<WxModule>();
		for (int i = 0, len = array.size(); i < len; i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxModule model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	private WxModuleJsonFactory() {

	}

}
