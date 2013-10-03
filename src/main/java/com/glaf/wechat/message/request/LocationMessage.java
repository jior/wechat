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

package com.glaf.wechat.message.request;

import org.dom4j.Document;
import org.dom4j.Element;

import com.glaf.wechat.message.BaseMessage;

/**
 * 地理位置消息
 * 
 * @author jior
 */
public class LocationMessage extends BaseMessage implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 地理位置维度
	private String locationX;
	// 地理位置经度
	private String locationY;
	// 地图缩放大小
	private String scale;
	// 地理位置信息
	private String label;

	public LocationMessage() {

	}

	public String getLabel() {
		return label;
	}

	public String getLocationX() {
		return locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public Document toDocument() {
		Document doc = super.toDocument();
		Element root = doc.getRootElement();
		root.addElement("Location_X").setText(locationX);
		root.addElement("Location_Y").setText(locationY);
		root.addElement("Scale").setText(scale);
		root.addElement("Label").setText(label);
		return doc;
	}

}