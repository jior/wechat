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

package com.glaf.wechat.message.response;

/**
 * 音乐model
 * 
 * @author jior
 * 
 */
public class Music implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 音乐名称
	private String title;
	// 音乐描述
	private String description;
	// 音乐链接
	private String musicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String musicHQUrl;

	public Music() {

	}

	public String getDescription() {
		return description;
	}

	public String getMusicHQUrl() {
		return musicHQUrl;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMusicHQUrl(String musicHQUrl) {
		this.musicHQUrl = musicHQUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
