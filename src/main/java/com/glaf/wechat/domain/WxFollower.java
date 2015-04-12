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

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.JSONable;
import com.glaf.wechat.util.WxFollowerJsonFactory;

public class WxFollower implements Serializable, JSONable {
	private static final long serialVersionUID = 1L;

	protected Long id;

	protected Long accountId;

	protected String actorId;

	protected String sourceId;

	protected String openId;

	protected String nickName;

	protected String sex;

	protected String mobile;

	protected String mail;

	protected String telephone;

	protected String headimgurl;

	protected String province;

	protected String city;

	protected String country;

	protected String language;

	protected Integer locked;

	protected String remark;

	protected Long subscribeTime;

	protected Integer subscribeYear;

	protected Integer subscribeMonth;

	protected Integer subscribeDay;

	protected Long unsubscribeTime;

	protected Long lastModified;

	protected Date createDate;

	protected String tableName;

	public WxFollower() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxFollower other = (WxFollower) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getAccountId() {
		return this.accountId;
	}

	public String getActorId() {
		return this.actorId;
	}

	public String getCity() {
		return this.city;
	}

	public String getCountry() {
		return this.country;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public String getHeadimgurl() {
		return this.headimgurl;
	}

	public Long getId() {
		return this.id;
	}

	public String getLanguage() {
		return this.language;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public Integer getLocked() {
		return this.locked;
	}

	public String getMail() {
		return this.mail;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getNickName() {
		return this.nickName;
	}

	public String getOpenId() {
		return this.openId;
	}

	public String getProvince() {
		return this.province;
	}

	public String getRemark() {
		return this.remark;
	}

	public String getSex() {
		return this.sex;
	}

	public String getSourceId() {
		return sourceId;
	}

	public Integer getSubscribeDay() {
		return subscribeDay;
	}

	public Integer getSubscribeMonth() {
		return subscribeMonth;
	}

	public Long getSubscribeTime() {
		return this.subscribeTime;
	}

	public Integer getSubscribeYear() {
		return subscribeYear;
	}

	public String getTableName() {
		return tableName;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public Long getUnsubscribeTime() {
		return unsubscribeTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public WxFollower jsonToObject(JSONObject jsonObject) {
		return WxFollowerJsonFactory.jsonToObject(jsonObject);
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public void setSubscribeDay(Integer subscribeDay) {
		this.subscribeDay = subscribeDay;
	}

	public void setSubscribeMonth(Integer subscribeMonth) {
		this.subscribeMonth = subscribeMonth;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public void setSubscribeYear(Integer subscribeYear) {
		this.subscribeYear = subscribeYear;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setUnsubscribeTime(Long unsubscribeTime) {
		this.unsubscribeTime = unsubscribeTime;
	}

	public JSONObject toJsonObject() {
		return WxFollowerJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxFollowerJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
