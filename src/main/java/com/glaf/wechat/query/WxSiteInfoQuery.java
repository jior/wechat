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

public class WxSiteInfoQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long accountId;
	protected List<Long> accountIds;
	protected List<Long> ids;
	protected String linkman;
	protected String linkmanLike;
	protected String telephoneLike;
	protected String mobile;
	protected String mobileLike;
	protected String mail;
	protected String mailLike;
	protected String qq;
	protected String qqLike;
	protected String addressLike;
	protected String siteUrlLike;
	protected String remarkLike;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxSiteInfoQuery() {

	}

	public WxSiteInfoQuery accountId(Long accountId) {
		if (accountId == null) {
			throw new RuntimeException("accountId is null");
		}
		this.accountId = accountId;
		return this;
	}
	
	public WxSiteInfoQuery addressLike(String addressLike) {
		if (addressLike == null) {
			throw new RuntimeException("address is null");
		}
		this.addressLike = addressLike;
		return this;
	}

	public WxSiteInfoQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxSiteInfoQuery createDateLessThanOrEqual(
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

	public String getAddressLike() {
		if (addressLike != null && addressLike.trim().length() > 0) {
			if (!addressLike.startsWith("%")) {
				addressLike = "%" + addressLike;
			}
			if (!addressLike.endsWith("%")) {
				addressLike = addressLike + "%";
			}
		}
		return addressLike;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public String getLinkman() {
		return linkman;
	}

	public String getLinkmanLike() {
		if (linkmanLike != null && linkmanLike.trim().length() > 0) {
			if (!linkmanLike.startsWith("%")) {
				linkmanLike = "%" + linkmanLike;
			}
			if (!linkmanLike.endsWith("%")) {
				linkmanLike = linkmanLike + "%";
			}
		}
		return linkmanLike;
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

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("linkman".equals(sortColumn)) {
				orderBy = "E.LINKMAN_" + a_x;
			}

			if ("telephone".equals(sortColumn)) {
				orderBy = "E.TELEPHONE_" + a_x;
			}

			if ("mobile".equals(sortColumn)) {
				orderBy = "E.MOBILE_" + a_x;
			}

			if ("mail".equals(sortColumn)) {
				orderBy = "E.MAIL_" + a_x;
			}

			if ("qq".equals(sortColumn)) {
				orderBy = "E.qq_" + a_x;
			}

			if ("address".equals(sortColumn)) {
				orderBy = "E.ADDRESS_" + a_x;
			}

			if ("siteUrl".equals(sortColumn)) {
				orderBy = "E.SITEURL_" + a_x;
			}

			if ("remark".equals(sortColumn)) {
				orderBy = "E.REMARK_" + a_x;
			}

			if ("createBy".equals(sortColumn)) {
				orderBy = "E.CREATEBY_" + a_x;
			}

			if ("createDate".equals(sortColumn)) {
				orderBy = "E.CREATEDATE_" + a_x;
			}

		}
		return orderBy;
	}

	public String getQq() {
		return qq;
	}

	public String getQqLike() {
		if (qqLike != null && qqLike.trim().length() > 0) {
			if (!qqLike.startsWith("%")) {
				qqLike = "%" + qqLike;
			}
			if (!qqLike.endsWith("%")) {
				qqLike = qqLike + "%";
			}
		}
		return qqLike;
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

	public String getSiteUrlLike() {
		if (siteUrlLike != null && siteUrlLike.trim().length() > 0) {
			if (!siteUrlLike.startsWith("%")) {
				siteUrlLike = "%" + siteUrlLike;
			}
			if (!siteUrlLike.endsWith("%")) {
				siteUrlLike = siteUrlLike + "%";
			}
		}
		return siteUrlLike;
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
		addColumn("linkman", "LINKMAN_");
		addColumn("telephone", "TELEPHONE_");
		addColumn("mobile", "MOBILE_");
		addColumn("mail", "MAIL_");
		addColumn("qq", "qq_");
		addColumn("address", "ADDRESS_");
		addColumn("siteUrl", "SITEURL_");
		addColumn("remark", "REMARK_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public WxSiteInfoQuery linkman(String linkman) {
		if (linkman == null) {
			throw new RuntimeException("linkman is null");
		}
		this.linkman = linkman;
		return this;
	}

	public WxSiteInfoQuery linkmanLike(String linkmanLike) {
		if (linkmanLike == null) {
			throw new RuntimeException("linkman is null");
		}
		this.linkmanLike = linkmanLike;
		return this;
	}

	public WxSiteInfoQuery mail(String mail) {
		if (mail == null) {
			throw new RuntimeException("mail is null");
		}
		this.mail = mail;
		return this;
	}

	public WxSiteInfoQuery mailLike(String mailLike) {
		if (mailLike == null) {
			throw new RuntimeException("mail is null");
		}
		this.mailLike = mailLike;
		return this;
	}

	public WxSiteInfoQuery mobile(String mobile) {
		if (mobile == null) {
			throw new RuntimeException("mobile is null");
		}
		this.mobile = mobile;
		return this;
	}

	public WxSiteInfoQuery mobileLike(String mobileLike) {
		if (mobileLike == null) {
			throw new RuntimeException("mobile is null");
		}
		this.mobileLike = mobileLike;
		return this;
	}

	public WxSiteInfoQuery qq(String qq) {
		if (qq == null) {
			throw new RuntimeException("qq is null");
		}
		this.qq = qq;
		return this;
	}

	public WxSiteInfoQuery qqLike(String qqLike) {
		if (qqLike == null) {
			throw new RuntimeException("qq is null");
		}
		this.qqLike = qqLike;
		return this;
	}

	public WxSiteInfoQuery remarkLike(String remarkLike) {
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

	public void setAddressLike(String addressLike) {
		this.addressLike = addressLike;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public void setLinkmanLike(String linkmanLike) {
		this.linkmanLike = linkmanLike;
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

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setQqLike(String qqLike) {
		this.qqLike = qqLike;
	}

	public void setRemarkLike(String remarkLike) {
		this.remarkLike = remarkLike;
	}

	public void setSiteUrlLike(String siteUrlLike) {
		this.siteUrlLike = siteUrlLike;
	}

	public void setTelephoneLike(String telephoneLike) {
		this.telephoneLike = telephoneLike;
	}

	public WxSiteInfoQuery siteUrlLike(String siteUrlLike) {
		if (siteUrlLike == null) {
			throw new RuntimeException("siteUrl is null");
		}
		this.siteUrlLike = siteUrlLike;
		return this;
	}

	public WxSiteInfoQuery telephoneLike(String telephoneLike) {
		if (telephoneLike == null) {
			throw new RuntimeException("telephone is null");
		}
		this.telephoneLike = telephoneLike;
		return this;
	}

}