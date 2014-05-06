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

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 菜单
 * 
 * @author jior
 */
public class Menu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List<Button> buttons;

	public Menu() {

	}

	public void addButton(Button button) {
		if (buttons == null) {
			buttons = new java.util.concurrent.CopyOnWriteArrayList<Button>();
		}
		buttons.add(button);
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public JSONObject toJSONObject() {
		JSONObject result = new JSONObject();
		if (buttons != null && buttons.size() > 0) {
			JSONArray array = new JSONArray();
			for (Button button : buttons) {
				JSONObject json = button.toJSONObject();
				array.add(json);
			}
			result.put("button", array);
		}
		return result;
	}
}