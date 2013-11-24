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

package com.glaf.apps.member.query;

import java.util.*;

import com.glaf.core.query.DataQuery;

public class WxMemberQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long accountId;
	protected List<Long> ids;
	protected String cardNo;
	protected String cardNoLike;
	protected List<String> cardNos;
	protected String name;
	protected String nameLike;
	protected List<String> names;
	protected String telephoneLike;
	protected String mobileLike;
	protected String mailLike;
	protected String qqLike;
	protected String addressLike;
	protected String uuid;
	protected List<String> uuids;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxMemberQuery() {

	}

	public WxMemberQuery addressLike(String addressLike) {
		if (addressLike == null) {
			throw new RuntimeException("address is null");
		}
		this.addressLike = addressLike;
		return this;
	}

	public WxMemberQuery cardNo(String cardNo) {
		if (cardNo == null) {
			throw new RuntimeException("cardNo is null");
		}
		this.cardNo = cardNo;
		return this;
	}

	public WxMemberQuery cardNoLike(String cardNoLike) {
		if (cardNoLike == null) {
			throw new RuntimeException("cardNo is null");
		}
		this.cardNoLike = cardNoLike;
		return this;
	}

	public WxMemberQuery cardNos(List<String> cardNos) {
		if (cardNos == null) {
			throw new RuntimeException("cardNos is empty ");
		}
		this.cardNos = cardNos;
		return this;
	}

	public WxMemberQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxMemberQuery createDateLessThanOrEqual(
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

	public String getCardNo() {
		return cardNo;
	}

	public String getCardNoLike() {
		if (cardNoLike != null && cardNoLike.trim().length() > 0) {
			if (!cardNoLike.startsWith("%")) {
				cardNoLike = "%" + cardNoLike;
			}
			if (!cardNoLike.endsWith("%")) {
				cardNoLike = cardNoLike + "%";
			}
		}
		return cardNoLike;
	}

	public List<String> getCardNos() {
		return cardNos;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
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

	public String getName() {
		return name;
	}

	public String getNameLike() {
		if (nameLike != null && nameLike.trim().length() > 0) {
			if (!nameLike.startsWith("%")) {
				nameLike = "%" + nameLike;
			}
			if (!nameLike.endsWith("%")) {
				nameLike = nameLike + "%";
			}
		}
		return nameLike;
	}

	public List<String> getNames() {
		return names;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("cardNo".equals(sortColumn)) {
				orderBy = "E.CARDNO_" + a_x;
			}

			if ("name".equals(sortColumn)) {
				orderBy = "E.NAME_" + a_x;
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

			if ("status".equals(sortColumn)) {
				orderBy = "E.STATUS_" + a_x;
			}

			if ("uuid".equals(sortColumn)) {
				orderBy = "E.UUID_" + a_x;
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

	public Integer getStatus() {
		return status;
	}

	public Integer getStatusGreaterThanOrEqual() {
		return statusGreaterThanOrEqual;
	}

	public Integer getStatusLessThanOrEqual() {
		return statusLessThanOrEqual;
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

	public String getUuid() {
		return uuid;
	}

	public List<String> getUuids() {
		return uuids;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("cardNo", "CARDNO_");
		addColumn("name", "NAME_");
		addColumn("telephone", "TELEPHONE_");
		addColumn("mobile", "MOBILE_");
		addColumn("mail", "MAIL_");
		addColumn("qq", "qq_");
		addColumn("address", "ADDRESS_");
		addColumn("status", "STATUS_");
		addColumn("uuid", "UUID_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public WxMemberQuery mailLike(String mailLike) {
		if (mailLike == null) {
			throw new RuntimeException("mail is null");
		}
		this.mailLike = mailLike;
		return this;
	}

	public WxMemberQuery mobileLike(String mobileLike) {
		if (mobileLike == null) {
			throw new RuntimeException("mobile is null");
		}
		this.mobileLike = mobileLike;
		return this;
	}

	public WxMemberQuery name(String name) {
		if (name == null) {
			throw new RuntimeException("name is null");
		}
		this.name = name;
		return this;
	}

	public WxMemberQuery nameLike(String nameLike) {
		if (nameLike == null) {
			throw new RuntimeException("name is null");
		}
		this.nameLike = nameLike;
		return this;
	}

	public WxMemberQuery names(List<String> names) {
		if (names == null) {
			throw new RuntimeException("names is empty ");
		}
		this.names = names;
		return this;
	}

	public WxMemberQuery qqLike(String qqLike) {
		if (qqLike == null) {
			throw new RuntimeException("qq is null");
		}
		this.qqLike = qqLike;
		return this;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAddressLike(String addressLike) {
		this.addressLike = addressLike;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setCardNoLike(String cardNoLike) {
		this.cardNoLike = cardNoLike;
	}

	public void setCardNos(List<String> cardNos) {
		this.cardNos = cardNos;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setMailLike(String mailLike) {
		this.mailLike = mailLike;
	}

	public void setMobileLike(String mobileLike) {
		this.mobileLike = mobileLike;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public void setQqLike(String qqLike) {
		this.qqLike = qqLike;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStatusGreaterThanOrEqual(Integer statusGreaterThanOrEqual) {
		this.statusGreaterThanOrEqual = statusGreaterThanOrEqual;
	}

	public void setStatusLessThanOrEqual(Integer statusLessThanOrEqual) {
		this.statusLessThanOrEqual = statusLessThanOrEqual;
	}

	public void setTelephoneLike(String telephoneLike) {
		this.telephoneLike = telephoneLike;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUuids(List<String> uuids) {
		this.uuids = uuids;
	}

	public WxMemberQuery status(Integer status) {
		if (status == null) {
			throw new RuntimeException("status is null");
		}
		this.status = status;
		return this;
	}

	public WxMemberQuery statusGreaterThanOrEqual(
			Integer statusGreaterThanOrEqual) {
		if (statusGreaterThanOrEqual == null) {
			throw new RuntimeException("status is null");
		}
		this.statusGreaterThanOrEqual = statusGreaterThanOrEqual;
		return this;
	}

	public WxMemberQuery statusLessThanOrEqual(Integer statusLessThanOrEqual) {
		if (statusLessThanOrEqual == null) {
			throw new RuntimeException("status is null");
		}
		this.statusLessThanOrEqual = statusLessThanOrEqual;
		return this;
	}

	public WxMemberQuery telephoneLike(String telephoneLike) {
		if (telephoneLike == null) {
			throw new RuntimeException("telephone is null");
		}
		this.telephoneLike = telephoneLike;
		return this;
	}

	public WxMemberQuery uuid(String uuid) {
		if (uuid == null) {
			throw new RuntimeException("uuid is null");
		}
		this.uuid = uuid;
		return this;
	}

	public WxMemberQuery uuids(List<String> uuids) {
		if (uuids == null) {
			throw new RuntimeException("uuids is empty ");
		}
		this.uuids = uuids;
		return this;
	}

}