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

public class WxTemplateQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long appId;
	protected List<Long> appIds;
	protected List<Long> ids;
	protected Long categoryId;
	protected List<Long> categoryIds;
	protected String type;
	protected Integer defaultFlag;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxTemplateQuery() {

	}

	public WxTemplateQuery categoryId(Long categoryId) {
		if (categoryId == null) {
			throw new RuntimeException("categoryId is null");
		}
		this.categoryId = categoryId;
		return this;
	}

	public WxTemplateQuery categoryIds(List<Long> categoryIds) {
		if (categoryIds == null) {
			throw new RuntimeException("categoryIds is empty ");
		}
		this.categoryIds = categoryIds;
		return this;
	}

	public WxTemplateQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxTemplateQuery createDateLessThanOrEqual(
			Date createDateLessThanOrEqual) {
		if (createDateLessThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
		return this;
	}

	public WxTemplateQuery defaultFlag(Integer defaultFlag) {
		if (defaultFlag == null) {
			throw new RuntimeException("defaultFlag is null");
		}
		this.defaultFlag = defaultFlag;
		return this;
	}

	public Long getAppId() {
		return appId;
	}

	public List<Long> getAppIds() {
		return appIds;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("categoryId".equals(sortColumn)) {
				orderBy = "E.CATEGORYID_" + a_x;
			}

			if ("skinImage".equals(sortColumn)) {
				orderBy = "E.SKINIMAGE_" + a_x;
			}

			if ("type".equals(sortColumn)) {
				orderBy = "E.TYPE_" + a_x;
			}

			if ("defaultFlag".equals(sortColumn)) {
				orderBy = "E.DEFAULTFLAG_" + a_x;
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

	public String getType() {
		return type;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("categoryId", "CATEGORYID_");
		addColumn("skinImage", "SKINIMAGE_");
		addColumn("type", "TYPE_");
		addColumn("path", "PATH_");
		addColumn("defaultFlag", "DEFAULTFLAG_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setAppIds(List<Long> appIds) {
		this.appIds = appIds;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WxTemplateQuery type(String type) {
		if (type == null) {
			throw new RuntimeException("type is null");
		}
		this.type = type;
		return this;
	}

}