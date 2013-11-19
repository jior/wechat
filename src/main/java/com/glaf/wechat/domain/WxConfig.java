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
	 * ΢վ���ں�Ӧ��ID
	 */
	@Column(name = "ACCOUNTID_")
	protected Long accountId;

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
	@Column(name = "WXAPPID_", length = 100)
	protected String wxAppId;

	/**
	 * Ӧ������
	 */
	@Column(name = "WXAPPSECRET_", length = 100)
	protected String wxAppSecret;

	/**
	 * Ӧ�ñ��
	 */
	@Column(name = "YXAPPID_", length = 100)
	protected String yxAppId;

	/**
	 * Ӧ������
	 */
	@Column(name = "YXAPPSECRET_", length = 100)
	protected String yxAppSecret;

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
	 * LBS��Ϣ����(��λ����)
	 */
	@Column(name = "LBSPOSITION_")
	protected Integer lbsPosition;

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

	public Long getAccountId() {
		return accountId;
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

	public String getWxAppId() {
		return wxAppId;
	}

	public String getWxAppSecret() {
		return wxAppSecret;
	}

	public String getYxAppId() {
		return yxAppId;
	}

	public String getYxAppSecret() {
		return yxAppSecret;
	}

	public WxConfig jsonToObject(JSONObject jsonObject) {
		return WxConfigJsonFactory.jsonToObject(jsonObject);
	}

	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}

	public void setYxAppId(String yxAppId) {
		this.yxAppId = yxAppId;
	}

	public void setYxAppSecret(String yxAppSecret) {
		this.yxAppSecret = yxAppSecret;
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
