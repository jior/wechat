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

package com.glaf.wechat.component;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 按钮的基类
 * 
 * @author jior
 */
public class Button implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String type;

	private String key;

	private List<Button> children;

	public Button() {

	}

	public void addChild(Button b) {
		if (children == null) {
			children = new ArrayList<Button>();
		}
		children.add(b);
	}

	public List<Button> getChildren() {
		return children;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setChildren(List<Button> children) {
		this.children = children;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubButtons(Button[] buttons) {
		if (children == null) {
			children = new ArrayList<Button>();
		}
		if (buttons != null && buttons.length > 0) {
			for (Button b : buttons) {
				children.add(b);
			}
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public JSONObject toJSONObject() {
		JSONObject result = new JSONObject();
		result.put("name", this.getName());
		if (this.getChildren() != null && !this.getChildren().isEmpty()) {
			JSONArray childrenArray = new JSONArray();
			for (Button child : this.getChildren()) {
				JSONObject o = new JSONObject();
				o.put("name", child.getName());
				o.put("type", child.getType());
				o.put("key", child.getKey());
				childrenArray.add(o);
			}
			result.put("sub_button", childrenArray);
		} else {
			result.put("type", this.getType());
			result.put("key", this.getKey());
		}
		return result;
	}
}