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

import org.dom4j.Document;
import org.dom4j.Element;

import com.glaf.wechat.message.BaseMessage;

/**
 * ������Ϣ
 * 
 * @author jior
 */
public class MusicMessage extends BaseMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// ����
	private Music music;

	public MusicMessage() {

	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public Document toDocument() {
		Document doc = super.toDocument();
		Element root = doc.getRootElement();
		if (music != null) {
			Element e = root.addElement("Music");
			e.addElement("Title").setText(music.getTitle());
			e.addElement("Description").setText(music.getDescription());
			e.addElement("MusicUrl").setText(music.getMusicUrl());
			e.addElement("HQMusicUrl").setText(music.getMusicHQUrl());
		}
		return doc;
	}
}