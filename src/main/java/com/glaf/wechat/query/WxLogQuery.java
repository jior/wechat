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

public class WxLogQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long appId;
	protected List<Long> appIds;
	protected String account;
	protected String accountLike;
	protected List<String> accounts;
	protected Date createTimeGreaterThanOrEqual;
	protected Date createTimeLessThanOrEqual;
	protected Integer flag;
	protected String suffix;
	protected String ip;
	protected String ipLike;
	protected String operate;
	protected String operateLike;
	protected List<String> operates;

	public WxLogQuery() {

	}

	public WxLogQuery account(String account) {
		if (account == null) {
			throw new RuntimeException("account is null");
		}
		this.account = account;
		return this;
	}

	public WxLogQuery accountLike(String accountLike) {
		if (accountLike == null) {
			throw new RuntimeException("account is null");
		}
		this.accountLike = accountLike;
		return this;
	}

	public WxLogQuery accounts(List<String> accounts) {
		if (accounts == null) {
			throw new RuntimeException("accounts is empty ");
		}
		this.accounts = accounts;
		return this;
	}

	public WxLogQuery createTimeGreaterThanOrEqual(
			Date createTimeGreaterThanOrEqual) {
		if (createTimeGreaterThanOrEqual == null) {
			throw new RuntimeException("createTime is null");
		}
		this.createTimeGreaterThanOrEqual = createTimeGreaterThanOrEqual;
		return this;
	}

	public WxLogQuery createTimeLessThanOrEqual(Date createTimeLessThanOrEqual) {
		if (createTimeLessThanOrEqual == null) {
			throw new RuntimeException("createTime is null");
		}
		this.createTimeLessThanOrEqual = createTimeLessThanOrEqual;
		return this;
	}

	public WxLogQuery flag(Integer flag) {
		if (flag == null) {
			throw new RuntimeException("flag is null");
		}
		this.flag = flag;
		return this;
	}

	public String getAccount() {
		return account;
	}

	public String getAccountLike() {
		if (accountLike != null && accountLike.trim().length() > 0) {
			if (!accountLike.startsWith("%")) {
				accountLike = "%" + accountLike;
			}
			if (!accountLike.endsWith("%")) {
				accountLike = accountLike + "%";
			}
		}
		return accountLike;
	}

	public List<String> getAccounts() {
		return accounts;
	}

	public Long getAppId() {
		return appId;
	}

	public List<Long> getAppIds() {
		return appIds;
	}

	public Date getCreateTimeGreaterThanOrEqual() {
		return createTimeGreaterThanOrEqual;
	}

	public Date getCreateTimeLessThanOrEqual() {
		return createTimeLessThanOrEqual;
	}

	public Integer getFlag() {
		return flag;
	}

	public String getIp() {
		return ip;
	}

	public String getIpLike() {
		if (ipLike != null && ipLike.trim().length() > 0) {
			if (!ipLike.startsWith("%")) {
				ipLike = "%" + ipLike;
			}
			if (!ipLike.endsWith("%")) {
				ipLike = ipLike + "%";
			}
		}
		return ipLike;
	}

	public String getOperate() {
		return operate;
	}

	public String getOperateLike() {
		if (operateLike != null && operateLike.trim().length() > 0) {
			if (!operateLike.startsWith("%")) {
				operateLike = "%" + operateLike;
			}
			if (!operateLike.endsWith("%")) {
				operateLike = operateLike + "%";
			}
		}
		return operateLike;
	}

	public List<String> getOperates() {
		return operates;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("account".equals(sortColumn)) {
				orderBy = "E.ACCOUNT" + a_x;
			}

			if ("ip".equals(sortColumn)) {
				orderBy = "E.IP" + a_x;
			}

			if ("createTime".equals(sortColumn)) {
				orderBy = "E.CREATETIME" + a_x;
			}

			if ("operate".equals(sortColumn)) {
				orderBy = "E.OPERATE" + a_x;
			}

			if ("flag".equals(sortColumn)) {
				orderBy = "E.FLAG" + a_x;
			}

		}
		return orderBy;
	}

	public String getSuffix() {
		return suffix;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID");
		addColumn("account", "ACCOUNT");
		addColumn("ip", "IP");
		addColumn("createTime", "CREATETIME");
		addColumn("operate", "OPERATE");
		addColumn("flag", "FLAG");
	}

	public WxLogQuery ip(String ip) {
		if (ip == null) {
			throw new RuntimeException("ip is null");
		}
		this.ip = ip;
		return this;
	}

	public WxLogQuery ipLike(String ipLike) {
		if (ipLike == null) {
			throw new RuntimeException("ip is null");
		}
		this.ipLike = ipLike;
		return this;
	}

	public WxLogQuery operate(String operate) {
		if (operate == null) {
			throw new RuntimeException("operate is null");
		}
		this.operate = operate;
		return this;
	}

	public WxLogQuery operateLike(String operateLike) {
		if (operateLike == null) {
			throw new RuntimeException("operate is null");
		}
		this.operateLike = operateLike;
		return this;
	}

	public WxLogQuery operates(List<String> operates) {
		if (operates == null) {
			throw new RuntimeException("operates is empty ");
		}
		this.operates = operates;
		return this;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setAccountLike(String accountLike) {
		this.accountLike = accountLike;
	}

	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setAppIds(List<Long> appIds) {
		this.appIds = appIds;
	}

	public void setCreateTimeGreaterThanOrEqual(
			Date createTimeGreaterThanOrEqual) {
		this.createTimeGreaterThanOrEqual = createTimeGreaterThanOrEqual;
	}

	public void setCreateTimeLessThanOrEqual(Date createTimeLessThanOrEqual) {
		this.createTimeLessThanOrEqual = createTimeLessThanOrEqual;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setIpLike(String ipLike) {
		this.ipLike = ipLike;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public void setOperateLike(String operateLike) {
		this.operateLike = operateLike;
	}

	public void setOperates(List<String> operates) {
		this.operates = operates;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}