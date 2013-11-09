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
import com.glaf.wechat.domain.WxFile;

public class WxFileJsonFactory {

	public static java.util.List<WxFile> arrayToList(JSONArray array) {
		java.util.List<WxFile> list = new java.util.ArrayList<WxFile>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxFile model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	public static WxFile jsonToObject(JSONObject jsonObject) {
		WxFile model = new WxFile();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("appId")) {
			model.setAppId(jsonObject.getLong("appId"));
		}
		if (jsonObject.containsKey("categoryId")) {
			model.setCategoryId(jsonObject.getLong("categoryId"));
		}
		if (jsonObject.containsKey("title")) {
			model.setTitle(jsonObject.getString("title"));
		}
		if (jsonObject.containsKey("desc")) {
			model.setDesc(jsonObject.getString("desc"));
		}
		if (jsonObject.containsKey("content")) {
			model.setContent(jsonObject.getString("content"));
		}
		if (jsonObject.containsKey("filename")) {
			model.setFilename(jsonObject.getString("filename"));
		}
		if (jsonObject.containsKey("path")) {
			model.setPath(jsonObject.getString("path"));
		}
		if (jsonObject.containsKey("locked")) {
			model.setLocked(jsonObject.getInteger("locked"));
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

	public static JSONArray listToArray(java.util.List<WxFile> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxFile model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static JSONObject toJsonObject(WxFile model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		jsonObject.put("categoryId", model.getCategoryId());
		if (model.getAppId() != null) {
			jsonObject.put("appId", model.getAppId());
		}
		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getDesc() != null) {
			jsonObject.put("desc", model.getDesc());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getFilename() != null) {
			jsonObject.put("filename", model.getFilename());
		}

		if (model.getOriginalFilename() != null) {
			jsonObject.put("originalFilename", model.getOriginalFilename());
		}

		if (model.getType() != null) {
			jsonObject.put("type", model.getType());
		}

		if (model.getSize() != 0) {
			jsonObject.put("size", model.getSize());
		}

		if (model.getPath() != null) {
			jsonObject.put("path", model.getPath());
		}
		jsonObject.put("locked", model.getLocked());
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

	public static ObjectNode toObjectNode(WxFile model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		jsonObject.put("categoryId", model.getCategoryId());
		if (model.getAppId() != null) {
			jsonObject.put("appId", model.getAppId());
		}
		if (model.getTitle() != null) {
			jsonObject.put("title", model.getTitle());
		}
		if (model.getDesc() != null) {
			jsonObject.put("desc", model.getDesc());
		}
		if (model.getContent() != null) {
			jsonObject.put("content", model.getContent());
		}
		if (model.getFilename() != null) {
			jsonObject.put("filename", model.getFilename());
		}
		if (model.getOriginalFilename() != null) {
			jsonObject.put("originalFilename", model.getOriginalFilename());
		}

		if (model.getType() != null) {
			jsonObject.put("type", model.getType());
		}

		if (model.getSize() != 0) {
			jsonObject.put("size", model.getSize());
		}
		if (model.getPath() != null) {
			jsonObject.put("path", model.getPath());
		}
		jsonObject.put("locked", model.getLocked());
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

	private WxFileJsonFactory() {

	}

}
