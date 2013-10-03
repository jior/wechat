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
 * ��Ϣ���ࣨ��ͨ�û� -> �����ʺţ�
 * 
 * @author jior
 */
public class BaseMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// ������΢�ź�
	private String toUserName;

	// ���ͷ��ʺţ�һ��OpenID��
	private String fromUserName;

	// ��Ϣ����ʱ�� ��64λ�����ͣ�
	private long createTime;

	// ��Ϣ���ͣ�text/image/location/link/voice��
	private String msgType;

	// ��Ϣid��64λ�����ͣ�
	private long msgId;

	// λ0x0001����־ʱ��������յ�����Ϣ
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
