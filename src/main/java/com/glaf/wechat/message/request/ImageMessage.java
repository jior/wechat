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
 * Í¼Æ¬ÏûÏ¢
 * 
 * @author jior
 */
public class ImageMessage extends BaseMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Í¼Æ¬Á´½Ó
	private String picUrl;

	public ImageMessage() {

	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Document toDocument() {
		Document doc = super.toDocument();
		Element root = doc.getRootElement();
		root.addElement("PicUrl").setText(picUrl);
		return doc;
	}
}
