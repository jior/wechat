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
package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.ItemMusic;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseMusicMessage;

/**
 * message response music handler
 * 
 */
public class MusicResponseMessageHandler extends AbstractResponseMessageHandler {

	@Override
	public String response(Message message) {
		ResponseMusicMessage musicMessage = (ResponseMusicMessage) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemMusic itemMusic = musicMessage.getMusic();
		item.append(wrapperContent(TAG_TITLE, itemMusic.getTitle(), true))
				.append(wrapperContent(TAG_DESCRIPTION,
						itemMusic.getDescription(), true))
				.append(wrapperContent(TAG_URL, itemMusic.getMusicUrl(), true))
				.append(wrapperContent(TAG_HQMUSICURL,
						itemMusic.getHqMusicUrl(), true));
		String music = wrapperContent(TAG_MUSIC, item.toString(), false);
		buffer.append(
				wrapperContent(TAG_TOUSERNAME, musicMessage.getToUserName(),
						true))
				.append(wrapperContent(TAG_FROMUSERNAME,
						musicMessage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME,
						musicMessage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, musicMessage.getMsgType(),
						true))
				.append(music)
				.append(wrapperContent(TAG_FUNCFLAG, musicMessage.getFuncFlag()
						+ "", false));
		return wrapperXmlContent(buffer.toString());
	}

}
