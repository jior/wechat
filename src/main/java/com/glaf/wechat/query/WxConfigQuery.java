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

public class WxConfigQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected List<Long> ids;
	protected String callBackUrlLike;
	protected String token;
	protected String appId;
	protected String apiStatus;
	protected String defaultReplyLike;
	protected String uuid;
	protected List<String> uuids;
	protected List<String> createBys;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxConfigQuery() {

	}

	public String getCallBackUrlLike() {
		if (callBackUrlLike != null && callBackUrlLike.trim().length() > 0) {
			if (!callBackUrlLike.startsWith("%")) {
				callBackUrlLike = "%" + callBackUrlLike;
			}
			if (!callBackUrlLike.endsWith("%")) {
				callBackUrlLike = callBackUrlLike + "%";
			}
		}
		return callBackUrlLike;
	}

	public String getToken() {
		return token;
	}

	public String getAppId() {
		return appId;
	}

	public String getApiStatus() {
		return apiStatus;
	}

	public String getDefaultReplyLike() {
		if (defaultReplyLike != null && defaultReplyLike.trim().length() > 0) {
			if (!defaultReplyLike.startsWith("%")) {
				defaultReplyLike = "%" + defaultReplyLike;
			}
			if (!defaultReplyLike.endsWith("%")) {
				defaultReplyLike = defaultReplyLike + "%";
			}
		}
		return defaultReplyLike;
	}

	public String getUuid() {
		return uuid;
	}

	public List<String> getUuids() {
		return uuids;
	}

	public List<String> getCreateBys() {
		return createBys;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public void setCallBackUrlLike(String callBackUrlLike) {
		this.callBackUrlLike = callBackUrlLike;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}

	public void setDefaultReplyLike(String defaultReplyLike) {
		this.defaultReplyLike = defaultReplyLike;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUuids(List<String> uuids) {
		this.uuids = uuids;
	}

	public void setCreateBys(List<String> createBys) {
		this.createBys = createBys;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public WxConfigQuery callBackUrlLike(String callBackUrlLike) {
		if (callBackUrlLike == null) {
			throw new RuntimeException("callBackUrl is null");
		}
		this.callBackUrlLike = callBackUrlLike;
		return this;
	}

	public WxConfigQuery token(String token) {
		if (token == null) {
			throw new RuntimeException("token is null");
		}
		this.token = token;
		return this;
	}

	public WxConfigQuery appId(String appId) {
		if (appId == null) {
			throw new RuntimeException("appId is null");
		}
		this.appId = appId;
		return this;
	}

	public WxConfigQuery apiStatus(String apiStatus) {
		if (apiStatus == null) {
			throw new RuntimeException("apiStatus is null");
		}
		this.apiStatus = apiStatus;
		return this;
	}

	public WxConfigQuery defaultReplyLike(String defaultReplyLike) {
		if (defaultReplyLike == null) {
			throw new RuntimeException("defaultReply is null");
		}
		this.defaultReplyLike = defaultReplyLike;
		return this;
	}

	public WxConfigQuery uuid(String uuid) {
		if (uuid == null) {
			throw new RuntimeException("uuid is null");
		}
		this.uuid = uuid;
		return this;
	}

	public WxConfigQuery uuids(List<String> uuids) {
		if (uuids == null) {
			throw new RuntimeException("uuids is empty ");
		}
		this.uuids = uuids;
		return this;
	}

	public WxConfigQuery createBys(List<String> createBys) {
		if (createBys == null) {
			throw new RuntimeException("createBys is empty ");
		}
		this.createBys = createBys;
		return this;
	}

	public WxConfigQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxConfigQuery createDateLessThanOrEqual(
			Date createDateLessThanOrEqual) {
		if (createDateLessThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
		return this;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("callBackUrl".equals(sortColumn)) {
				orderBy = "E.CALLBACKURL_" + a_x;
			}

			if ("token".equals(sortColumn)) {
				orderBy = "E.TOKEN_" + a_x;
			}

			if ("appId".equals(sortColumn)) {
				orderBy = "E.APPID_" + a_x;
			}

			if ("appSecret".equals(sortColumn)) {
				orderBy = "E.APPSECRET_" + a_x;
			}

			if ("apiStatus".equals(sortColumn)) {
				orderBy = "E.APISTATUS_" + a_x;
			}

			if ("defaultReply".equals(sortColumn)) {
				orderBy = "E.DEFAULTREPLY_" + a_x;
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

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("callBackUrl", "CALLBACKURL_");
		addColumn("token", "TOKEN_");
		addColumn("appId", "APPID_");
		addColumn("appSecret", "APPSECRET_");
		addColumn("apiStatus", "APISTATUS_");
		addColumn("defaultReply", "DEFAULTREPLY_");
		addColumn("uuid", "UUID_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

}