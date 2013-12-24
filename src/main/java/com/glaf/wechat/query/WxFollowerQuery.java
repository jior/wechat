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

package com.glaf.wechat.query;

import java.util.*;
import com.glaf.core.query.DataQuery;

public class WxFollowerQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long accountId;
	protected List<Long> accountIds;
	protected String sourceId;
	protected String openId;
	protected List<String> openIds;
	protected String nickNameLike;
	protected String sex;
	protected String mobile;
	protected String mobileLike;
	protected String mail;
	protected String mailLike;
	protected String telephoneLike;
	protected String province;
	protected String city;
	protected String country;
	protected String language;
	protected String remarkLike;
	protected Long subscribeTimeGreaterThanOrEqual;
	protected Long subscribeTimeLessThanOrEqual;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxFollowerQuery() {

	}

	public WxFollowerQuery accountId(Long accountId) {
		if (accountId == null) {
			throw new RuntimeException("accountId is null");
		}
		this.accountId = accountId;
		return this;
	}

	public WxFollowerQuery accountIds(List<Long> accountIds) {
		if (accountIds == null) {
			throw new RuntimeException("accountIds is empty ");
		}
		this.accountIds = accountIds;
		return this;
	}

	public WxFollowerQuery city(String city) {
		if (city == null) {
			throw new RuntimeException("city is null");
		}
		this.city = city;
		return this;
	}

	public WxFollowerQuery country(String country) {
		if (country == null) {
			throw new RuntimeException("country is null");
		}
		this.country = country;
		return this;
	}

	public WxFollowerQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxFollowerQuery createDateLessThanOrEqual(
			Date createDateLessThanOrEqual) {
		if (createDateLessThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
		return this;
	}

	public Long getAccountId() {
		return accountId;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public String getActorId() {
		return actorId;
	}

	public List<String> getActorIds() {
		return actorIds;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public String getLanguage() {
		return language;
	}

	public Integer getLocked() {
		return locked;
	}

	public String getMail() {
		return mail;
	}

	public String getMailLike() {
		if (mailLike != null && mailLike.trim().length() > 0) {
			if (!mailLike.startsWith("%")) {
				mailLike = "%" + mailLike;
			}
			if (!mailLike.endsWith("%")) {
				mailLike = mailLike + "%";
			}
		}
		return mailLike;
	}

	public String getMobile() {
		return mobile;
	}

	public String getMobileLike() {
		if (mobileLike != null && mobileLike.trim().length() > 0) {
			if (!mobileLike.startsWith("%")) {
				mobileLike = "%" + mobileLike;
			}
			if (!mobileLike.endsWith("%")) {
				mobileLike = mobileLike + "%";
			}
		}
		return mobileLike;
	}

	public String getNickNameLike() {
		if (nickNameLike != null && nickNameLike.trim().length() > 0) {
			if (!nickNameLike.startsWith("%")) {
				nickNameLike = "%" + nickNameLike;
			}
			if (!nickNameLike.endsWith("%")) {
				nickNameLike = nickNameLike + "%";
			}
		}
		return nickNameLike;
	}

	public String getOpenId() {
		return openId;
	}

	public List<String> getOpenIds() {
		return openIds;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("accountId".equals(sortColumn)) {
				orderBy = "E.ACCOUNTID_" + a_x;
			}

			if ("actorId".equals(sortColumn)) {
				orderBy = "E.ACTORID_" + a_x;
			}

			if ("openId".equals(sortColumn)) {
				orderBy = "E.OPENID_" + a_x;
			}

			if ("nickName".equals(sortColumn)) {
				orderBy = "E.NICKNAME_" + a_x;
			}

			if ("sex".equals(sortColumn)) {
				orderBy = "E.SEX_" + a_x;
			}

			if ("mobile".equals(sortColumn)) {
				orderBy = "E.MOBILE_" + a_x;
			}

			if ("mail".equals(sortColumn)) {
				orderBy = "E.MAIL_" + a_x;
			}

			if ("telephone".equals(sortColumn)) {
				orderBy = "E.TELEPHONE_" + a_x;
			}

			if ("headimgurl".equals(sortColumn)) {
				orderBy = "E.HEADIMGURL_" + a_x;
			}

			if ("province".equals(sortColumn)) {
				orderBy = "E.PROVINCE_" + a_x;
			}

			if ("city".equals(sortColumn)) {
				orderBy = "E.CITY_" + a_x;
			}

			if ("country".equals(sortColumn)) {
				orderBy = "E.COUNTRY_" + a_x;
			}

			if ("language".equals(sortColumn)) {
				orderBy = "E.LANGUAGE_" + a_x;
			}

			if ("locked".equals(sortColumn)) {
				orderBy = "E.LOCKED_" + a_x;
			}

			if ("remark".equals(sortColumn)) {
				orderBy = "E.REMARK" + a_x;
			}

			if ("subscribeTime".equals(sortColumn)) {
				orderBy = "E.SUBSCRIBETIME_" + a_x;
			}

			if ("createDate".equals(sortColumn)) {
				orderBy = "E.CREATEDATE_" + a_x;
			}

		}
		return orderBy;
	}

	public String getProvince() {
		return province;
	}

	public String getRemarkLike() {
		if (remarkLike != null && remarkLike.trim().length() > 0) {
			if (!remarkLike.startsWith("%")) {
				remarkLike = "%" + remarkLike;
			}
			if (!remarkLike.endsWith("%")) {
				remarkLike = remarkLike + "%";
			}
		}
		return remarkLike;
	}

	public String getSex() {
		return sex;
	}

	public String getSourceId() {
		return sourceId;
	}

	public Long getSubscribeTimeGreaterThanOrEqual() {
		return subscribeTimeGreaterThanOrEqual;
	}

	public Long getSubscribeTimeLessThanOrEqual() {
		return subscribeTimeLessThanOrEqual;
	}

	public String getTelephoneLike() {
		if (telephoneLike != null && telephoneLike.trim().length() > 0) {
			if (!telephoneLike.startsWith("%")) {
				telephoneLike = "%" + telephoneLike;
			}
			if (!telephoneLike.endsWith("%")) {
				telephoneLike = telephoneLike + "%";
			}
		}
		return telephoneLike;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("accountId", "ACCOUNTID_");
		addColumn("actorId", "ACTORID_");
		addColumn("openId", "OPENID_");
		addColumn("nickName", "NICKNAME_");
		addColumn("sex", "SEX_");
		addColumn("mobile", "MOBILE_");
		addColumn("mail", "MAIL_");
		addColumn("telephone", "TELEPHONE_");
		addColumn("headimgurl", "HEADIMGURL_");
		addColumn("province", "PROVINCE_");
		addColumn("city", "CITY_");
		addColumn("country", "COUNTRY_");
		addColumn("language", "LANGUAGE_");
		addColumn("locked", "LOCKED_");
		addColumn("remark", "REMARK");
		addColumn("subscribeTime", "SUBSCRIBETIME_");
		addColumn("createDate", "CREATEDATE_");
	}

	public WxFollowerQuery language(String language) {
		if (language == null) {
			throw new RuntimeException("language is null");
		}
		this.language = language;
		return this;
	}

	public WxFollowerQuery locked(Integer locked) {
		if (locked == null) {
			throw new RuntimeException("locked is null");
		}
		this.locked = locked;
		return this;
	}

	public WxFollowerQuery mail(String mail) {
		if (mail == null) {
			throw new RuntimeException("mail is null");
		}
		this.mail = mail;
		return this;
	}

	public WxFollowerQuery mailLike(String mailLike) {
		if (mailLike == null) {
			throw new RuntimeException("mail is null");
		}
		this.mailLike = mailLike;
		return this;
	}

	public WxFollowerQuery mobile(String mobile) {
		if (mobile == null) {
			throw new RuntimeException("mobile is null");
		}
		this.mobile = mobile;
		return this;
	}

	public WxFollowerQuery mobileLike(String mobileLike) {
		if (mobileLike == null) {
			throw new RuntimeException("mobile is null");
		}
		this.mobileLike = mobileLike;
		return this;
	}

	public WxFollowerQuery nickNameLike(String nickNameLike) {
		if (nickNameLike == null) {
			throw new RuntimeException("nickName is null");
		}
		this.nickNameLike = nickNameLike;
		return this;
	}

	public WxFollowerQuery openId(String openId) {
		if (openId == null) {
			throw new RuntimeException("openId is null");
		}
		this.openId = openId;
		return this;
	}

	public WxFollowerQuery openIds(List<String> openIds) {
		if (openIds == null) {
			throw new RuntimeException("openIds is empty ");
		}
		this.openIds = openIds;
		return this;
	}

	public WxFollowerQuery province(String province) {
		if (province == null) {
			throw new RuntimeException("province is null");
		}
		this.province = province;
		return this;
	}

	public WxFollowerQuery remarkLike(String remarkLike) {
		if (remarkLike == null) {
			throw new RuntimeException("remark is null");
		}
		this.remarkLike = remarkLike;
		return this;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setMailLike(String mailLike) {
		this.mailLike = mailLike;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setMobileLike(String mobileLike) {
		this.mobileLike = mobileLike;
	}

	public void setNickNameLike(String nickNameLike) {
		this.nickNameLike = nickNameLike;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setOpenIds(List<String> openIds) {
		this.openIds = openIds;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setRemarkLike(String remarkLike) {
		this.remarkLike = remarkLike;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public void setSubscribeTimeGreaterThanOrEqual(
			Long subscribeTimeGreaterThanOrEqual) {
		this.subscribeTimeGreaterThanOrEqual = subscribeTimeGreaterThanOrEqual;
	}

	public void setSubscribeTimeLessThanOrEqual(
			Long subscribeTimeLessThanOrEqual) {
		this.subscribeTimeLessThanOrEqual = subscribeTimeLessThanOrEqual;
	}

	public void setTelephoneLike(String telephoneLike) {
		this.telephoneLike = telephoneLike;
	}

	public WxFollowerQuery sex(String sex) {
		if (sex == null) {
			throw new RuntimeException("sex is null");
		}
		this.sex = sex;
		return this;
	}

	public WxFollowerQuery sourceId(String sourceId) {
		if (sourceId == null) {
			throw new RuntimeException("sourceId is null");
		}
		this.sourceId = sourceId;
		return this;
	}

	public WxFollowerQuery subscribeTimeGreaterThanOrEqual(
			Long subscribeTimeGreaterThanOrEqual) {
		if (subscribeTimeGreaterThanOrEqual == null) {
			throw new RuntimeException("subscribeTime is null");
		}
		this.subscribeTimeGreaterThanOrEqual = subscribeTimeGreaterThanOrEqual;
		return this;
	}

	public WxFollowerQuery subscribeTimeLessThanOrEqual(
			Long subscribeTimeLessThanOrEqual) {
		if (subscribeTimeLessThanOrEqual == null) {
			throw new RuntimeException("subscribeTime is null");
		}
		this.subscribeTimeLessThanOrEqual = subscribeTimeLessThanOrEqual;
		return this;
	}

	public WxFollowerQuery telephoneLike(String telephoneLike) {
		if (telephoneLike == null) {
			throw new RuntimeException("telephone is null");
		}
		this.telephoneLike = telephoneLike;
		return this;
	}

}