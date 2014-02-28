package com.glaf.wechat.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.util.DateUtils;
import com.glaf.wechat.domain.WxKeywords;

public class WxKeywordsJsonFactory {

	public static java.util.List<WxKeywords> arrayToList(JSONArray array) {
		java.util.List<WxKeywords> list = new java.util.ArrayList<WxKeywords>();
		for (int i = 0, len = array.size(); i < len; i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxKeywords model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	public static WxKeywords jsonToObject(JSONObject jsonObject) {
		WxKeywords model = new WxKeywords();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("accountId")) {
			model.setAccountId(jsonObject.getLong("accountId"));
		}
		if (jsonObject.containsKey("categoryId")) {
			model.setCategoryId(jsonObject.getLong("categoryId"));
		}
		if (jsonObject.containsKey("contentId")) {
			model.setContentId(jsonObject.getLong("contentId"));
		}
		if (jsonObject.containsKey("keywords")) {
			model.setKeywords(jsonObject.getString("keywords"));
		}
		if (jsonObject.containsKey("keywordsMatchType")) {
			model.setKeywordsMatchType(jsonObject
					.getString("keywordsMatchType"));
		}
		if (jsonObject.containsKey("createBy")) {
			model.setCreateBy(jsonObject.getString("createBy"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}

		return model;
	}

	public static JSONArray listToArray(java.util.List<WxKeywords> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxKeywords model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static JSONObject toJsonObject(WxKeywords model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getAccountId() != null) {
			jsonObject.put("accountId", model.getAccountId());
		}
		jsonObject.put("categoryId", model.getCategoryId());
		if (model.getContentId() != null) {
			jsonObject.put("contentId", model.getContentId());
		}
		if (model.getKeywords() != null) {
			jsonObject.put("keywords", model.getKeywords());
		}
		if (model.getKeywordsMatchType() != null) {
			jsonObject.put("keywordsMatchType", model.getKeywordsMatchType());
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
		return jsonObject;
	}

	public static ObjectNode toObjectNode(WxKeywords model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		jsonObject.put("categoryId", model.getCategoryId());
		if (model.getAccountId() != null) {
			jsonObject.put("accountId", model.getAccountId());
		}
		if (model.getContentId() != null) {
			jsonObject.put("contentId", model.getContentId());
		}
		if (model.getKeywords() != null) {
			jsonObject.put("keywords", model.getKeywords());
		}
		if (model.getKeywordsMatchType() != null) {
			jsonObject.put("keywordsMatchType", model.getKeywordsMatchType());
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
		return jsonObject;
	}

	private WxKeywordsJsonFactory() {

	}

}
