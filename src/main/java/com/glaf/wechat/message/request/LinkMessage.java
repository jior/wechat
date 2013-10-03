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
 * 链接消息
 * 
 * @author jior
 */
public class LinkMessage extends BaseMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 消息标题
	private String title;
	// 消息描述
	private String description;
	// 消息链接
	private String url;

	public LinkMessage() {

	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Document toDocument() {
		Document doc = super.toDocument();
		Element root = doc.getRootElement();
		root.addElement("Title").setText(title);
		root.addElement("Description").setText(description);
		root.addElement("Url").setText(url);
		return doc;
	}
}
