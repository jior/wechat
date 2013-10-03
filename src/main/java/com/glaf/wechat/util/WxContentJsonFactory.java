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
import com.glaf.wechat.domain.WxContent;

public class WxContentJsonFactory {

	public static java.util.List<WxContent> arrayToList(JSONArray array) {
		java.util.List<WxContent> list = new java.util.ArrayList<WxContent>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxContent model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	public static WxContent jsonToObject(JSONObject jsonObject) {
		WxContent model = new WxContent();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("categoryId")) {
			model.setCategoryId(jsonObject.getLong("categoryId"));
		}
		if (jsonObject.containsKey("title")) {
			model.setTitle(jsonObject.getString("title"));
		}
		if (jsonObject.containsKey("content")) {
			model.setContent(jsonObject.getString("content"));
		}
		if (jsonObject.containsKey("author")) {
			model.setAuthor(jsonObject.getString("author"));
		}
		if (jsonObject.containsKey("status")) {
			model.setStatus(jsonObject.getInteger("status"));
		}
		if (jsonObject.containsKey("priority")) {
			model.setPriority(jsonObject.getInteger("priority"));
		}
		if (jsonObject.containsKey("type")) {
			model.setType(jsonObject.getString("type"));
		}
		if (jsonObject.containsKey("uuid")) {
			model.setUuid(jsonObject.getString("uuid"));
		}
		if (jsonObject.containsKey("keywords")) {
			model.setKeywords(jsonObject.getString("keywords"));
		}
		if (jsonObject.containsKey("keywordsCount")) {
			model.setKeywordsCount(jsonObject.getInteger("keywordsCount"));
		}
		if (jsonObject.containsKey("summary")) {
			model.setSummary(jsonObject.getString("summary"));
		}
		if (jsonObject.containsKey("icon")) {
			model.setIcon(jsonObject.getString("icon"));
		}
		if (jsonObject.containsKey("bigIcon")) {
			model.setBigIcon(jsonObject.getString("bigIcon"));
		}
		if (jsonObject.containsKey("smallIcon")) {
			model.setSmallIcon(jsonObject.getString("smallIcon"));
		}
		if (jsonObject.containsKey("url")) {
			model.setUrl(jsonObject.getString("url"));
		}
		if (jsonObject.containsKey("createBy")) {
			model.setCreateBy(jsonObject.getString("createBy"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}

		return model;
	}

	public static JSONArray listToArray(java.util.List<WxContent> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxContent model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static JSONObject toJsonObject(WxContent model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		jsonObject.put("categoryId", model.getCategoryId());
		jsonObject.put("sort", model.getSort());

		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getAuthor() != null) {
			jsonObject.put("author", model.getAuthor());
		}
		jsonObject.put("status", model.getStatus());
		jsonObject.put("priority", model.getPriority());
		if (model.getType() != null) {
			jsonObject.put("type", model.getType());
		}
		if (model.getUuid() != null) {
			jsonObject.put("uuid", model.getUuid());
		}
		if (model.getKeywords() != null) {
			jsonObject.put("keywords", model.getKeywords());
		}
		jsonObject.put("keywordsCount", model.getKeywordsCount());
		if (model.getSummary() != null) {
			jsonObject.put("summary", model.getSummary());
		}
		if (model.getIcon() != null) {
			jsonObject.put("icon", model.getIcon());
		}
		if (model.getBigIcon() != null) {
			jsonObject.put("bigIcon", model.getBigIcon());
		}
		if (model.getSmallIcon() != null) {
			jsonObject.put("smallIcon", model.getSmallIcon());
		}
		if (jsonObject.containsKey("sort")) {
			model.setSort(jsonObject.getInteger("sort"));
		}
		if (model.getUrl() != null) {
			jsonObject.put("url", model.getUrl());
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

	public static ObjectNode toObjectNode(WxContent model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		jsonObject.put("categoryId", model.getCategoryId());
		jsonObject.put("sort", model.getSort());

		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getAuthor() != null) {
			jsonObject.put("author", model.getAuthor());
		}
		jsonObject.put("status", model.getStatus());
		jsonObject.put("priority", model.getPriority());
		if (model.getType() != null) {
			jsonObject.put("type", model.getType());
		}
		if (model.getUuid() != null) {
			jsonObject.put("uuid", model.getUuid());
		}
		if (model.getKeywords() != null) {
			jsonObject.put("keywords", model.getKeywords());
		}
		jsonObject.put("keywordsCount", model.getKeywordsCount());
		if (model.getSummary() != null) {
			jsonObject.put("summary", model.getSummary());
		}
		if (model.getIcon() != null) {
			jsonObject.put("icon", model.getIcon());
		}
		if (model.getBigIcon() != null) {
			jsonObject.put("bigIcon", model.getBigIcon());
		}
		if (model.getSmallIcon() != null) {
			jsonObject.put("smallIcon", model.getSmallIcon());
		}
		if (model.getUrl() != null) {
			jsonObject.put("url", model.getUrl());
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

	private WxContentJsonFactory() {

	}

}
