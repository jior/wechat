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
package com.glaf.wechat.sdk.message;

import java.util.Map;

import org.dom4j.Element;

/**
 * ��Ϣ������
 * 
 */
public class Message implements IMessage, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String msgType;// ��Ϣ���ͣ�text/image/location/link/voice��
	protected String fromUserName;// ���ͷ��ʺţ�һ��OpenID��
	protected String toUserName;// ������΢�ź�
	protected long msgId;// ��Ϣid��64λ�����ͣ�
	protected long createTime;// ��Ϣ����ʱ�� ��64λ�����ͣ�
	protected String contextPath;
	protected String customer;// �ͻ����
	protected Element root;
	protected Map<String, Object> requestParameters;

	public Message() {

	}

	public String getContextPath() {
		return contextPath;
	}

	public long getCreateTime() {
		return createTime;
	}

	public String getCustomer() {
		return customer;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public long getMsgId() {
		return msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public Map<String, Object> getRequestParameters() {
		return requestParameters;
	}

	public Element getRoot() {
		return root;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setRequestParameters(Map<String, Object> requestParameters) {
		this.requestParameters = requestParameters;
	}

	public void setRoot(Element root) {
		this.root = root;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

}
