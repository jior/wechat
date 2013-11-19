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

public class WxUserQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected List<Long> accountIds;
	protected Integer userType;
	protected Long deptId;
	protected List<Long> deptIds;
	protected String type;
	protected Integer accountType;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;
	protected Date endDateGreaterThanOrEqual;
	protected Date endDateLessThanOrEqual;

	public WxUserQuery() {

	}

	public WxUserQuery actorId(String actorId) {
		if (actorId == null) {
			throw new RuntimeException("actorId is null");
		}
		this.actorId = actorId;
		return this;
	}

	public WxUserQuery actorIds(List<String> actorIds) {
		if (actorIds == null) {
			throw new RuntimeException("actorIds is empty ");
		}
		this.actorIds = actorIds;
		return this;
	}

	public WxUserQuery accountIds(List<Long> accountIds) {
		if (accountIds == null) {
			throw new RuntimeException("accountIds is empty ");
		}
		this.accountIds = accountIds;
		return this;
	}

	public WxUserQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxUserQuery createDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		if (createDateLessThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
		return this;
	}

	public WxUserQuery endDateGreaterThanOrEqual(Date endDateGreaterThanOrEqual) {
		if (endDateGreaterThanOrEqual == null) {
			throw new RuntimeException("endDate is null");
		}
		this.endDateGreaterThanOrEqual = endDateGreaterThanOrEqual;
		return this;
	}

	public WxUserQuery endDateLessThanOrEqual(Date endDateLessThanOrEqual) {
		if (endDateLessThanOrEqual == null) {
			throw new RuntimeException("endDate is null");
		}
		this.endDateLessThanOrEqual = endDateLessThanOrEqual;
		return this;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public String getActorId() {
		return actorId;
	}

	public List<String> getActorIds() {
		return actorIds;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public Long getDeptId() {
		return deptId;
	}

	public List<Long> getDeptIds() {
		return deptIds;
	}

	public Date getEndDateGreaterThanOrEqual() {
		return endDateGreaterThanOrEqual;
	}

	public Date getEndDateLessThanOrEqual() {
		return endDateLessThanOrEqual;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("accountId".equals(sortColumn)) {
				orderBy = "E.ACCOUNTID" + a_x;
			}

			if ("actorId".equals(sortColumn)) {
				orderBy = "E.ACTORID" + a_x;
			}

			if ("wxid".equals(sortColumn)) {
				orderBy = "E.WXID" + a_x;
			}

			if ("wxSourceId".equals(sortColumn)) {
				orderBy = "E.WXSOURCEID" + a_x;
			}

			if ("wxname".equals(sortColumn)) {
				orderBy = "E.WXNAME" + a_x;
			}

			if ("wxHeadImage".equals(sortColumn)) {
				orderBy = "E.WXHEADIMAGE" + a_x;
			}

			if ("yxid".equals(sortColumn)) {
				orderBy = "E.YXID" + a_x;
			}

			if ("yxSourceId".equals(sortColumn)) {
				orderBy = "E.YXSOURCEID" + a_x;
			}

			if ("yxname".equals(sortColumn)) {
				orderBy = "E.YXName" + a_x;
			}

			if ("yxHeadImage".equals(sortColumn)) {
				orderBy = "E.YXHEADIMAGE" + a_x;
			}

			if ("token".equals(sortColumn)) {
				orderBy = "E.TOKEN" + a_x;
			}

			if ("province".equals(sortColumn)) {
				orderBy = "E.PROVINCE" + a_x;
			}

			if ("city".equals(sortColumn)) {
				orderBy = "E.CITY" + a_x;
			}

			if ("area".equals(sortColumn)) {
				orderBy = "E.AREA" + a_x;
			}

			if ("name".equals(sortColumn)) {
				orderBy = "E.NAME" + a_x;
			}

			if ("mobile".equals(sortColumn)) {
				orderBy = "E.MOBILE" + a_x;
			}

			if ("mail".equals(sortColumn)) {
				orderBy = "E.MAIL" + a_x;
			}

			if ("telephone".equals(sortColumn)) {
				orderBy = "E.TELEPHONE" + a_x;
			}

			if ("userType".equals(sortColumn)) {
				orderBy = "E.USERTYPE" + a_x;
			}

			if ("accountType".equals(sortColumn)) {
				orderBy = "E.ACCOUNTTYPE" + a_x;
			}

			if ("deptId".equals(sortColumn)) {
				orderBy = "E.DEPTID" + a_x;
			}

			if ("type".equals(sortColumn)) {
				orderBy = "E.TYPE" + a_x;
			}

			if ("locked".equals(sortColumn)) {
				orderBy = "E.LOCKED" + a_x;
			}

			if ("lastLoginDate".equals(sortColumn)) {
				orderBy = "E.LASTLOGINDATE" + a_x;
			}

			if ("loginIP".equals(sortColumn)) {
				orderBy = "E.LASTLOGINIP" + a_x;
			}

			if ("remark".equals(sortColumn)) {
				orderBy = "E.REMARK" + a_x;
			}

			if ("createDate".equals(sortColumn)) {
				orderBy = "E.CREATEDATE" + a_x;
			}

			if ("endDate".equals(sortColumn)) {
				orderBy = "E.ENDDATE" + a_x;
			}

		}
		return orderBy;
	}

	public String getType() {
		return type;
	}

	public Integer getUserType() {
		return userType;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID");
		addColumn("accountId", "ACCOUNTID");
		addColumn("actorId", "ACTORID");
		addColumn("wxid", "WXID");
		addColumn("wxSourceId", "WXSOURCEID");
		addColumn("wxname", "WXNAME");
		addColumn("wxHeadImage", "WXHEADIMAGE");
		addColumn("yxid", "YXID");
		addColumn("yxSourceId", "YXSOURCEID");
		addColumn("yxname", "YXName");
		addColumn("yxHeadImage", "YXHEADIMAGE");
		addColumn("token", "TOKEN");
		addColumn("province", "PROVINCE");
		addColumn("city", "CITY");
		addColumn("area", "AREA");
		addColumn("name", "NAME");
		addColumn("mobile", "MOBILE");
		addColumn("mail", "MAIL");
		addColumn("telephone", "TELEPHONE");
		addColumn("userType", "USERTYPE");
		addColumn("accountType", "ACCOUNTTYPE");
		addColumn("deptId", "DEPTID");
		addColumn("type", "TYPE");
		addColumn("locked", "LOCKED");
		addColumn("lastLoginDate", "LASTLOGINDATE");
		addColumn("loginIP", "LASTLOGINIP");
		addColumn("remark", "REMARK");
		addColumn("createDate", "CREATEDATE");
		addColumn("endDate", "ENDDATE");
	}

	public WxUserQuery locked(Integer locked) {
		if (locked == null) {
			throw new RuntimeException("locked is null");
		}
		this.locked = locked;
		return this;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setActorIds(List<String> actorIds) {
		this.actorIds = actorIds;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setDeptIds(List<Long> deptIds) {
		this.deptIds = deptIds;
	}

	public void setEndDateGreaterThanOrEqual(Date endDateGreaterThanOrEqual) {
		this.endDateGreaterThanOrEqual = endDateGreaterThanOrEqual;
	}

	public void setEndDateLessThanOrEqual(Date endDateLessThanOrEqual) {
		this.endDateLessThanOrEqual = endDateLessThanOrEqual;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}