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
package com.glaf.wechat.sdk.message.handler;

import java.util.Date;
import java.util.List;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.IMessage;
import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.ItemMusic;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseMusicMessage;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;
import com.glaf.wechat.sdk.message.ResponseTextMessage;

/**
 * abstract class for message handling
 * 
 */
public abstract class AbstractMessageHandler implements IMessageHandler,
		IMessage {

	protected Message message;

	// build response music message
	protected Message buildResponseMusicMessage(ItemMusic item) {
		ResponseMusicMessage msg = new ResponseMusicMessage();
		msg.setMsgType(MESSAGE_RESPONSE_MUSIC);
		msg.setCreateTime(getCurrentUnixTimestamp());
		msg.setFromUserName(message.getToUserName());
		msg.setToUserName(message.getFromUserName());
		msg.setMusic(item);
		msg.setFuncFlag(1);
		return msg;
	}

	// build response news message
	protected Message buildResponseNewsMessage(List<ItemArticle> items) {
		ResponseNewsMessage msg = new ResponseNewsMessage();
		msg.setMsgType(MESSAGE_RESPONSE_NEWS);
		msg.setCreateTime(getCurrentUnixTimestamp());
		msg.setFromUserName(message.getToUserName());
		msg.setToUserName(message.getFromUserName());
		msg.setArticleItems(items);
		msg.setCount(items.size());
		msg.setFuncFlag(1);
		return msg;
	}

	// build response text message
	protected Message buildResponseTextMessage(String content) {
		ResponseTextMessage msg = new ResponseTextMessage();
		msg.setMsgType(MESSAGE_RESPONSE_TEXT);
		msg.setContent(content);
		msg.setFromUserName(message.getToUserName());
		msg.setToUserName(message.getFromUserName());
		msg.setFuncFlag(1);
		msg.setCreateTime(getCurrentUnixTimestamp());
		return msg;
	}

	// get current Unix time
	public int getCurrentUnixTimestamp() {
		Date date = new Date();
		return (int) (date.getTime() / 1000);
	}

	@Override
	public Message handleMessage(Message message) {
		this.message = message;
		return handleSpecialMessage(message);
	}

	protected abstract Message handleSpecialMessage(Message message);

	// put common parse part here!
	@Override
	public void parseMessage(Message message, Element root) {
		message.setMsgType(root.elementText(TAG_MSGTYPE));
		message.setFromUserName(root.elementText(TAG_FROMUSERNAME));
		message.setToUserName(root.elementText(TAG_TOUSERNAME));

		parseSpecialMessage(message, root);
	}

	// subclass implements it
	protected abstract void parseSpecialMessage(Message message, Element root);

}
