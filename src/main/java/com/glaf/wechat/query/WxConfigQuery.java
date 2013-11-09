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
	protected Long appId;
	protected List<Long> appIds;
	protected List<Long> ids;
	protected String callBackUrlLike;
	protected String apiStatus;
	protected String defaultReplyLike;
	protected List<String> createBys;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxConfigQuery() {

	}

	public WxConfigQuery apiStatus(String apiStatus) {
		if (apiStatus == null) {
			throw new RuntimeException("apiStatus is null");
		}
		this.apiStatus = apiStatus;
		return this;
	}

	public WxConfigQuery appId(Long appId) {
		if (appId == null) {
			throw new RuntimeException("appId is null");
		}
		this.appId = appId;
		return this;
	}

	public WxConfigQuery callBackUrlLike(String callBackUrlLike) {
		if (callBackUrlLike == null) {
			throw new RuntimeException("callBackUrl is null");
		}
		this.callBackUrlLike = callBackUrlLike;
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

	public WxConfigQuery defaultReplyLike(String defaultReplyLike) {
		if (defaultReplyLike == null) {
			throw new RuntimeException("defaultReply is null");
		}
		this.defaultReplyLike = defaultReplyLike;
		return this;
	}

	public String getApiStatus() {
		return apiStatus;
	}

	public Long getAppId() {
		return appId;
	}

	public List<Long> getAppIds() {
		return appIds;
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

	public List<String> getCreateBys() {
		return createBys;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
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

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("callBackUrl".equals(sortColumn)) {
				orderBy = "E.CALLBACKURL_" + a_x;
			}

			if ("appId".equals(sortColumn)) {
				orderBy = "E.APPID_" + a_x;
			}

			if ("apiStatus".equals(sortColumn)) {
				orderBy = "E.APISTATUS_" + a_x;
			}

			if ("defaultReply".equals(sortColumn)) {
				orderBy = "E.DEFAULTREPLY_" + a_x;
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
		addColumn("wxAppId", "WXAPPID_");
		addColumn("wxAppSecret", "WXAPPSECRET_");
		addColumn("yxAppId", "YXAPPID_");
		addColumn("yxAppSecret", "YXAPPSECRET_");
		addColumn("apiStatus", "APISTATUS_");
		addColumn("defaultReply", "DEFAULTREPLY_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setAppIds(List<Long> appIds) {
		this.appIds = appIds;
	}

	public void setCallBackUrlLike(String callBackUrlLike) {
		this.callBackUrlLike = callBackUrlLike;
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

	public void setDefaultReplyLike(String defaultReplyLike) {
		this.defaultReplyLike = defaultReplyLike;
	}

}