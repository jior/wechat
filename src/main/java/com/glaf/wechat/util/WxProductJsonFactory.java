package com.glaf.wechat.util;

import com.alibaba.fastjson.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.glaf.core.util.DateUtils;
import com.glaf.wechat.domain.*;

public class WxProductJsonFactory {

	public static WxProduct jsonToObject(JSONObject jsonObject) {
		WxProduct model = new WxProduct();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("name")) {
			model.setName(jsonObject.getString("name"));
		}
		if (jsonObject.containsKey("price")) {
			model.setPrice(jsonObject.getDouble("price"));
		}
		if (jsonObject.containsKey("newsNum")) {
			model.setNewsNum(jsonObject.getInteger("newsNum"));
		}
		if (jsonObject.containsKey("categoryNum")) {
			model.setCategoryNum(jsonObject.getInteger("categoryNum"));
		}
		if (jsonObject.containsKey("dayNum")) {
			model.setDayNum(jsonObject.getInteger("dayNum"));
		}
		if (jsonObject.containsKey("createBy")) {
			model.setCreateBy(jsonObject.getString("createBy"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}

		return model;
	}

	public static JSONObject toJsonObject(WxProduct model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
		}
		jsonObject.put("price", model.getPrice());
		jsonObject.put("newsNum", model.getNewsNum());
		jsonObject.put("categoryNum", model.getCategoryNum());
		jsonObject.put("dayNum", model.getDayNum());
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

	public static ObjectNode toObjectNode(WxProduct model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", model.getId());
		jsonObject.put("_id_", model.getId());
		jsonObject.put("_oid_", model.getId());
		if (model.getName() != null) {
			jsonObject.put("name", model.getName());
		}
		jsonObject.put("price", model.getPrice());
		jsonObject.put("newsNum", model.getNewsNum());
		jsonObject.put("categoryNum", model.getCategoryNum());
		jsonObject.put("dayNum", model.getDayNum());
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

	public static JSONArray listToArray(java.util.List<WxProduct> list) {
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (WxProduct model : list) {
				JSONObject jsonObject = model.toJsonObject();
				array.add(jsonObject);
			}
		}
		return array;
	}

	public static java.util.List<WxProduct> arrayToList(JSONArray array) {
		java.util.List<WxProduct> list = new java.util.ArrayList<WxProduct>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			WxProduct model = jsonToObject(jsonObject);
			list.add(model);
		}
		return list;
	}

	private WxProductJsonFactory() {

	}

}
