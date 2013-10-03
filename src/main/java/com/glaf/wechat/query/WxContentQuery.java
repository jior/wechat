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

public class WxContentQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected List<Long> ids;
	protected Long categoryId;
	protected List<Long> categoryIds;
	protected String titleLike;
	protected String contentLike;
	protected Integer priority;
	protected Integer priorityGreaterThanOrEqual;
	protected Integer priorityLessThanOrEqual;
	protected String type;
	protected List<String> types;
	protected String uuid;
	protected List<String> uuids;
	protected String keywordsLike;
	protected String keywordsMatchType;
	protected String summaryLike;
	protected String urlLike;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxContentQuery() {

	}

	public WxContentQuery categoryId(Long categoryId) {
		if (categoryId == null) {
			throw new RuntimeException("categoryId is null");
		}
		this.categoryId = categoryId;
		return this;
	}

	public WxContentQuery categoryIds(List<Long> categoryIds) {
		if (categoryIds == null) {
			throw new RuntimeException("categoryIds is empty ");
		}
		this.categoryIds = categoryIds;
		return this;
	}

	public WxContentQuery contentLike(String contentLike) {
		if (contentLike == null) {
			throw new RuntimeException("content is null");
		}
		this.contentLike = contentLike;
		return this;
	}

	public WxContentQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxContentQuery createDateLessThanOrEqual(
			Date createDateLessThanOrEqual) {
		if (createDateLessThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
		return this;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public String getContentLike() {
		if (contentLike != null && contentLike.trim().length() > 0) {
			if (!contentLike.startsWith("%")) {
				contentLike = "%" + contentLike;
			}
			if (!contentLike.endsWith("%")) {
				contentLike = contentLike + "%";
			}
		}
		return contentLike;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public String getKeywordsLike() {
		if (keywordsLike != null && keywordsLike.trim().length() > 0) {
			if (!keywordsLike.startsWith("%")) {
				keywordsLike = "%" + keywordsLike;
			}
			if (!keywordsLike.endsWith("%")) {
				keywordsLike = keywordsLike + "%";
			}
		}
		return keywordsLike;
	}

	public String getKeywordsMatchType() {
		return keywordsMatchType;
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

			if ("title".equals(sortColumn)) {
				orderBy = "E.TITLE_" + a_x;
			}

			if ("content".equals(sortColumn)) {
				orderBy = "E.CONTENT_" + a_x;
			}

			if ("status".equals(sortColumn)) {
				orderBy = "E.STATUS_" + a_x;
			}

			if ("priority".equals(sortColumn)) {
				orderBy = "E.PRIORTY_" + a_x;
			}

			if ("type".equals(sortColumn)) {
				orderBy = "E.TYPE_" + a_x;
			}

			if ("uuid".equals(sortColumn)) {
				orderBy = "E.UUID_" + a_x;
			}

			if ("keywords".equals(sortColumn)) {
				orderBy = "E.KEYWORDS_" + a_x;
			}

			if ("keywordsCount".equals(sortColumn)) {
				orderBy = "E.KEYWORDSCOUNT_" + a_x;
			}

			if ("summary".equals(sortColumn)) {
				orderBy = "E.SUMMARY_" + a_x;
			}

			if ("icon".equals(sortColumn)) {
				orderBy = "E.ICON_" + a_x;
			}

			if ("bigIcon".equals(sortColumn)) {
				orderBy = "E.BIGICON_" + a_x;
			}

			if ("smallIcon".equals(sortColumn)) {
				orderBy = "E.SMALLICON_" + a_x;
			}

			if ("url".equals(sortColumn)) {
				orderBy = "E.URL_" + a_x;
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

	public Integer getPriority() {
		return priority;
	}

	public Integer getPriorityGreaterThanOrEqual() {
		return priorityGreaterThanOrEqual;
	}

	public Integer getPriorityLessThanOrEqual() {
		return priorityLessThanOrEqual;
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

	public String getSummaryLike() {
		if (summaryLike != null && summaryLike.trim().length() > 0) {
			if (!summaryLike.startsWith("%")) {
				summaryLike = "%" + summaryLike;
			}
			if (!summaryLike.endsWith("%")) {
				summaryLike = summaryLike + "%";
			}
		}
		return summaryLike;
	}

	public String getTitleLike() {
		if (titleLike != null && titleLike.trim().length() > 0) {
			if (!titleLike.startsWith("%")) {
				titleLike = "%" + titleLike;
			}
			if (!titleLike.endsWith("%")) {
				titleLike = titleLike + "%";
			}
		}
		return titleLike;
	}

	public String getType() {
		return type;
	}

	public List<String> getTypes() {
		return types;
	}

	public String getUrlLike() {
		if (urlLike != null && urlLike.trim().length() > 0) {
			if (!urlLike.startsWith("%")) {
				urlLike = "%" + urlLike;
			}
			if (!urlLike.endsWith("%")) {
				urlLike = urlLike + "%";
			}
		}
		return urlLike;
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
		addColumn("categoryId", "CATEGORYID_");
		addColumn("title", "TITLE_");
		addColumn("content", "CONTENT_");
		addColumn("status", "STATUS_");
		addColumn("priority", "PRIORTY_");
		addColumn("type", "TYPE_");
		addColumn("uuid", "UUID_");
		addColumn("keywords", "KEYWORDS_");
		addColumn("keywordsCount", "KEYWORDSCOUNT_");
		addColumn("summary", "SUMMARY_");
		addColumn("icon", "ICON_");
		addColumn("bigIcon", "BIGICON_");
		addColumn("smallIcon", "SMALLICON_");
		addColumn("url", "URL_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public WxContentQuery keywordsLike(String keywordsLike) {
		if (keywordsLike == null) {
			throw new RuntimeException("keywords is null");
		}
		this.keywordsLike = keywordsLike;
		return this;
	}

	public WxContentQuery keywordsMatchType(String keywordsMatchType) {
		if (keywordsMatchType == null) {
			throw new RuntimeException("keywordsMatchType is null");
		}
		this.keywordsMatchType = keywordsMatchType;
		return this;
	}

	public WxContentQuery priority(Integer priority) {
		if (priority == null) {
			throw new RuntimeException("priority is null");
		}
		this.priority = priority;
		return this;
	}

	public WxContentQuery priorityGreaterThanOrEqual(
			Integer priorityGreaterThanOrEqual) {
		if (priorityGreaterThanOrEqual == null) {
			throw new RuntimeException("priority is null");
		}
		this.priorityGreaterThanOrEqual = priorityGreaterThanOrEqual;
		return this;
	}

	public WxContentQuery priorityLessThanOrEqual(
			Integer priorityLessThanOrEqual) {
		if (priorityLessThanOrEqual == null) {
			throw new RuntimeException("priority is null");
		}
		this.priorityLessThanOrEqual = priorityLessThanOrEqual;
		return this;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public void setContentLike(String contentLike) {
		this.contentLike = contentLike;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setKeywordsLike(String keywordsLike) {
		this.keywordsLike = keywordsLike;
	}

	public void setKeywordsMatchType(String keywordsMatchType) {
		this.keywordsMatchType = keywordsMatchType;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setPriorityGreaterThanOrEqual(Integer priorityGreaterThanOrEqual) {
		this.priorityGreaterThanOrEqual = priorityGreaterThanOrEqual;
	}

	public void setPriorityLessThanOrEqual(Integer priorityLessThanOrEqual) {
		this.priorityLessThanOrEqual = priorityLessThanOrEqual;
	}

	public void setSummaryLike(String summaryLike) {
		this.summaryLike = summaryLike;
	}

	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public void setUrlLike(String urlLike) {
		this.urlLike = urlLike;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUuids(List<String> uuids) {
		this.uuids = uuids;
	}

	public WxContentQuery summaryLike(String summaryLike) {
		if (summaryLike == null) {
			throw new RuntimeException("summary is null");
		}
		this.summaryLike = summaryLike;
		return this;
	}

	public WxContentQuery titleLike(String titleLike) {
		if (titleLike == null) {
			throw new RuntimeException("title is null");
		}
		this.titleLike = titleLike;
		return this;
	}

	public WxContentQuery type(String type) {
		if (type == null) {
			throw new RuntimeException("type is null");
		}
		this.type = type;
		return this;
	}

	public WxContentQuery types(List<String> types) {
		if (types == null) {
			throw new RuntimeException("types is empty ");
		}
		this.types = types;
		return this;
	}

	public WxContentQuery urlLike(String urlLike) {
		if (urlLike == null) {
			throw new RuntimeException("url is null");
		}
		this.urlLike = urlLike;
		return this;
	}

	public WxContentQuery uuid(String uuid) {
		if (uuid == null) {
			throw new RuntimeException("uuid is null");
		}
		this.uuid = uuid;
		return this;
	}

	public WxContentQuery uuids(List<String> uuids) {
		if (uuids == null) {
			throw new RuntimeException("uuids is empty ");
		}
		this.uuids = uuids;
		return this;
	}

}