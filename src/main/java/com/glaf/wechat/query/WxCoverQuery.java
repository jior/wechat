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

public class WxCoverQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected List<Long> ids;
	protected String uuid;
	protected List<String> uuids;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxCoverQuery() {

	}

	public String getUuid() {
		return uuid;
	}

	public List<String> getUuids() {
		return uuids;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUuids(List<String> uuids) {
		this.uuids = uuids;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public WxCoverQuery uuid(String uuid) {
		if (uuid == null) {
			throw new RuntimeException("uuid is null");
		}
		this.uuid = uuid;
		return this;
	}

	public WxCoverQuery uuids(List<String> uuids) {
		if (uuids == null) {
			throw new RuntimeException("uuids is empty ");
		}
		this.uuids = uuids;
		return this;
	}

	public WxCoverQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxCoverQuery createDateLessThanOrEqual(Date createDateLessThanOrEqual) {
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

			if ("bigIcon".equals(sortColumn)) {
				orderBy = "E.BIGICON_" + a_x;
			}

			if ("smallIcon".equals(sortColumn)) {
				orderBy = "E.SMALLICON_" + a_x;
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
		addColumn("bigIcon", "BIGICON_");
		addColumn("smallIcon", "SMALLICON_");
		addColumn("uuid", "UUID_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

}