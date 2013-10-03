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

package com.glaf.wechat.message;

import org.dom4j.*;

/**
 * 消息基类（普通用户 -> 公众帐号）
 * 
 * @author jior
 */
public class BaseMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 开发者微信号
	private String toUserName;

	// 发送方帐号（一个OpenID）
	private String fromUserName;

	// 消息创建时间 （64位长整型）
	private long createTime;

	// 消息类型（text/image/location/link/voice）
	private String msgType;

	// 消息id（64位长整型）
	private long msgId;

	// 位0x0001被标志时，代表刚收到的消息
	private int funcFlag;

	public BaseMessage() {

	}

	public long getCreateTime() {
		return createTime;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public int getFuncFlag() {
		return funcFlag;
	}

	public long getMsgId() {
		return msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public Document toDocument() {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		root.addElement("ToUserName").setText(toUserName);
		root.addElement("FromUserName").setText(fromUserName);
		root.addElement("CreateTime").setText(String.valueOf(createTime));
		root.addElement("MsgType").setText(msgType);
		root.addElement("MsgId").setText(String.valueOf(msgId));
		root.addElement("FuncFlag").setText(String.valueOf(funcFlag));
		return doc;
	}
}
