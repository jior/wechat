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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.JSONable;
import com.glaf.core.identity.User;
import com.glaf.wechat.util.WxUserJsonFactory;

@Entity
@Table(name = "WX_USER")
public class WxUser implements Serializable, JSONable, User {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	protected long id;

	@Column(name = "ACTORID", length = 50)
	protected String actorId;

	@Column(name = "WXID", length = 200)
	protected String wxid;

	@Column(name = "WXSOURCEID", length = 200)
	protected String wxSourceId;

	@Column(name = "WXNAME", length = 200)
	protected String wxname;

	@Column(name = "WXHEADIMAGE", length = 250)
	protected String wxHeadImage;

	@Column(name = "YXID", length = 200)
	protected String yxid;

	@Column(name = "YXSOURCEID", length = 200)
	protected String yxSourceId;

	@Column(name = "YXNAME", length = 200)
	protected String yxname;

	@Column(name = "YXHEADIMAGE", length = 250)
	protected String yxHeadImage;

	@Column(name = "TOKEN", length = 100)
	protected String token;

	/**
	 * 微信应用编号
	 */
	@Column(name = "WXAPPID", length = 100)
	protected String wxAppId;

	/**
	 * 微信应用密锁
	 */
	@Column(name = "WXAPPSECRET", length = 100)
	protected String wxAppSecret;

	/**
	 * 易信应用编号
	 */
	@Column(name = "YXAPPID", length = 100)
	protected String yxAppId;

	/**
	 * 易信应用密锁
	 */
	@Column(name = "YXAPPSECRET", length = 100)
	protected String yxAppSecret;

	/**
	 * LBS信息距离(单位：米)
	 */
	@Column(name = "LBSPOSITION")
	protected Integer lbsPosition;

	@Column(name = "PROVINCE", length = 50)
	protected String province;

	@Column(name = "CITY", length = 200)
	protected String city;

	@Column(name = "AREA", length = 200)
	protected String area;

	@Column(name = "NAME", length = 50)
	protected String name;

	@Column(name = "MOBILE", length = 20)
	protected String mobile;

	@Column(name = "MAIL", length = 100)
	protected String mail;

	@Column(name = "TELEPHONE", length = 50)
	protected String telephone;

	@Column(name = "USERTYPE")
	protected int userType;

	@Column(name = "ACCOUNTTYPE")
	protected int accountType;

	@Column(name = "DEPTID")
	protected long deptId;

	@Column(name = "TYPE", length = 20)
	protected String type;

	@Column(name = "LOCKED")
	protected int locked;

	@Column(name = "REMARK", length = 250)
	protected String remark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	protected Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDATE")
	protected Date endDate;

	public WxUser() {

	}

	public int getAccountType() {
		return accountType;
	}

	public String getActorId() {
		return actorId;
	}

	public String getAdminFlag() {
		return null;
	}

	public String getArea() {
		return area;
	}

	public String getCity() {
		return city;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public long getDeptId() {
		return deptId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getFax() {
		return null;
	}

	public long getId() {
		return id;
	}

	public Date getLastLoginDate() {
		return null;
	}

	public Integer getLbsPosition() {
		return lbsPosition;
	}

	public int getLocked() {
		return locked;
	}

	public String getLoginIP() {
		return null;
	}

	public int getLoginRetry() {
		return 0;
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

	public String getPassword() {
		return null;
	}

	public String getProvince() {
		return province;
	}

	public String getRemark() {
		return remark;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public int getUserType() {
		return userType;
	}

	public String getWxAppId() {
		return wxAppId;
	}

	public String getWxAppSecret() {
		return wxAppSecret;
	}

	public String getWxHeadImage() {
		return wxHeadImage;
	}

	public String getWxid() {
		return wxid;
	}

	public String getWxname() {
		return wxname;
	}

	public String getWxSourceId() {
		return wxSourceId;
	}

	public String getYxAppId() {
		return yxAppId;
	}

	public String getYxAppSecret() {
		return yxAppSecret;
	}

	public String getYxHeadImage() {
		return yxHeadImage;
	}

	public String getYxid() {
		return yxid;
	}

	public String getYxname() {
		return yxname;
	}

	public String getYxSourceId() {
		return yxSourceId;
	}

	public boolean isSystemAdministrator() {
		return false;
	}

	public WxUser jsonToObject(JSONObject jsonObject) {
		return WxUserJsonFactory.jsonToObject(jsonObject);
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setAdminFlag(String adminFlag) {
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastLoginDate(Date lastLoginDate) {

	}

	public void setLbsPosition(Integer lbsPosition) {
		this.lbsPosition = lbsPosition;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public void setLoginIP(String loginIP) {

	}

	public void setLoginRetry(int loginRetry) {
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

	public void setPassword(String pwd) {
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}

	public void setWxHeadImage(String wxHeadImage) {
		this.wxHeadImage = wxHeadImage;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public void setWxname(String wxname) {
		this.wxname = wxname;
	}

	public void setWxSourceId(String wxSourceId) {
		this.wxSourceId = wxSourceId;
	}

	public void setYxAppId(String yxAppId) {
		this.yxAppId = yxAppId;
	}

	public void setYxAppSecret(String yxAppSecret) {
		this.yxAppSecret = yxAppSecret;
	}

	public void setYxHeadImage(String yxHeadImage) {
		this.yxHeadImage = yxHeadImage;
	}

	public void setYxid(String yxid) {
		this.yxid = yxid;
	}

	public void setYxname(String yxname) {
		this.yxname = yxname;
	}

	public void setYxSourceId(String yxSourceId) {
		this.yxSourceId = yxSourceId;
	}

	public JSONObject toJsonObject() {
		return WxUserJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxUserJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
