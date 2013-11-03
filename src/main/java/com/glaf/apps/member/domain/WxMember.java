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

package com.glaf.apps.member.domain;

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
import com.glaf.apps.member.util.WxMemberJsonFactory;
import com.glaf.core.base.JSONable;

/**
 * 
 * 会员信息
 * 
 */
@Entity
@Table(name = "WX_MEMBER")
public class WxMember implements java.io.Serializable, JSONable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected long id;

	/**
	 * 会员卡号
	 */
	@Column(name = "CARDNO_", length = 50)
	protected String cardNo;

	/**
	 * 姓名
	 */
	@Column(name = "NAME_", length = 50)
	protected String name;

	/**
	 * 联系电话
	 */
	@Column(name = "TELEPHONE_", length = 50)
	protected String telephone;

	/**
	 * 手机
	 */
	@Column(name = "MOBILE_", length = 50)
	protected String mobile;

	/**
	 * mail
	 */
	@Column(name = "MAIL_", length = 50)
	protected String mail;

	/**
	 * QQ
	 */
	@Column(name = "QQ_", length = 50)
	protected String qq;

	/**
	 * 联系地址
	 */
	@Column(name = "ADDRESS_", length = 50)
	protected String address;

	/**
	 * 余额
	 */
	@Column(name = "BALANCE_")
	protected Double balance;

	/**
	 * 状态
	 */
	@Column(name = "STATUS_")
	protected int status;

	/**
	 * UUID
	 */
	@Column(name = "UUID_", length = 50)
	protected String uuid;

	/**
	 * 创建人
	 */
	@Column(name = "CREATEBY_", length = 50)
	protected String createBy;

	/**
	 * 创建日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE_")
	protected Date createDate;

	/**
	 * 最后修改人
	 */
	@Column(name = "LASTUPDATEBY_", length = 50)
	protected String lastUpdateBy;

	/**
	 * 最后修改日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTUPDATEDATE_")
	protected Date lastUpdateDate;

	public WxMember() {

	}

	public String getAddress() {
		return address;
	}

	public Double getBalance() {
		return balance;
	}

	public String getCardNo() {
		return cardNo;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
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

	public String getMail() {
		return mail;
	}

	public String getMobile() {
		return mobile;
	}

	public String getName() {
		return name;
	}

	public String getQq() {
		return qq;
	}

	public int getStatus() {
		return status;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getUuid() {
		return uuid;
	}

	public WxMember jsonToObject(JSONObject jsonObject) {
		return WxMemberJsonFactory.jsonToObject(jsonObject);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public JSONObject toJsonObject() {
		return WxMemberJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxMemberJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
