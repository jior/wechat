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

package com.glaf.wechat.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.JSONable;
import com.glaf.wechat.util.WxConfigJsonFactory;

/**
 * 
 * �ӿ�������Ϣ
 * 
 */
@Entity
@Table(name = "WX_CONFIG")
public class WxConfig implements java.io.Serializable, JSONable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected long id;

	/**
	 * �ص���ַ
	 */
	@Column(name = "CALLBACKURL_", length = 500)
	protected String callBackUrl;

	/**
	 * ����
	 */
	@Column(name = "TOKEN_", length = 100)
	protected String token;

	/**
	 * Ӧ�ñ��
	 */
	@Column(name = "APPID_", length = 100)
	protected String appId;

	/**
	 * Ӧ������
	 */
	@Column(name = "APPSECRET_", length = 100)
	protected String appSecret;

	/**
	 * Ӧ��״̬
	 */
	@Column(name = "APISTATUS_", length = 20)
	protected String apiStatus;

	/**
	 * Ĭ�ϻظ�
	 */
	@Column(name = "DEFAULTREPLY_", length = 2000)
	protected String defaultReply;

	/**
	 * LBS��Ϣ����
	 */
	@Column(name = "LBSPOSITION_")
	protected Integer lbsPosition;

	/**
	 * UUID
	 */
	@Column(name = "UUID_", length = 50)
	protected String uuid;

	/**
	 * ������
	 */
	@Column(name = "CREATEBY_", length = 50)
	protected String createBy;

	/**
	 * ��������
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE_")
	protected Date createDate;

	/**
	 * ����޸���
	 */
	@Column(name = "LASTUPDATEBY_", length = 50)
	protected String lastUpdateBy;

	/**
	 * ����޸�����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTUPDATEDATE_")
	protected Date lastUpdateDate;

	public WxConfig() {

	}

	public String getApiStatus() {
		return apiStatus;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getDefaultReply() {
		return defaultReply;
	}

	public long getId() {
		return id;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public Integer getLbsPosition() {
		return lbsPosition;
	}

	public String getToken() {
		return token;
	}

	public String getUuid() {
		return uuid;
	}

	public WxConfig jsonToObject(JSONObject jsonObject) {
		return WxConfigJsonFactory.jsonToObject(jsonObject);
	}

	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDefaultReply(String defaultReply) {
		this.defaultReply = defaultReply;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setLbsPosition(Integer lbsPosition) {
		this.lbsPosition = lbsPosition;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public JSONObject toJsonObject() {
		return WxConfigJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxConfigJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
