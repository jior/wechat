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

public class WxFileQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long appId;
	protected List<Long> appIds;
	protected List<Long> ids;
	protected Long categoryId;
	protected List<Long> categoryIds;
	protected String titleLike;
	protected String contentLike;
	protected String filenameLike;
	protected String pathLike;
	protected String uuid;
	protected List<String> uuids;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxFileQuery() {

	}

	public WxFileQuery categoryId(Long categoryId) {
		if (categoryId == null) {
			throw new RuntimeException("categoryId is null");
		}
		this.categoryId = categoryId;
		return this;
	}

	public WxFileQuery categoryIds(List<Long> categoryIds) {
		if (categoryIds == null) {
			throw new RuntimeException("categoryIds is empty ");
		}
		this.categoryIds = categoryIds;
		return this;
	}

	public WxFileQuery contentLike(String contentLike) {
		if (contentLike == null) {
			throw new RuntimeException("content is null");
		}
		this.contentLike = contentLike;
		return this;
	}

	public WxFileQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxFileQuery createDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		if (createDateLessThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
		return this;
	}

	public WxFileQuery filenameLike(String filenameLike) {
		if (filenameLike == null) {
			throw new RuntimeException("filename is null");
		}
		this.filenameLike = filenameLike;
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

	public String getFilenameLike() {
		if (filenameLike != null && filenameLike.trim().length() > 0) {
			if (!filenameLike.startsWith("%")) {
				filenameLike = "%" + filenameLike;
			}
			if (!filenameLike.endsWith("%")) {
				filenameLike = filenameLike + "%";
			}
		}
		return filenameLike;
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

			if ("filename".equals(sortColumn)) {
				orderBy = "E.FILENAME_" + a_x;
			}

			if ("originalFilename".equals(sortColumn)) {
				orderBy = "E.ORIGINALFILENAME_" + a_x;
			}

			if ("type".equals(sortColumn)) {
				orderBy = "E.TYPE_" + a_x;
			}

			if ("size".equals(sortColumn)) {
				orderBy = "E.SIZE_" + a_x;
			}

			if ("path".equals(sortColumn)) {
				orderBy = "E.PATH_" + a_x;
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

	public String getPathLike() {
		if (pathLike != null && pathLike.trim().length() > 0) {
			if (!pathLike.startsWith("%")) {
				pathLike = "%" + pathLike;
			}
			if (!pathLike.endsWith("%")) {
				pathLike = pathLike + "%";
			}
		}
		return pathLike;
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
		addColumn("filename", "FILENAME_");
		addColumn("type", "TYPE_");
		addColumn("size", "SIZE_");
		addColumn("originalFilename", "ORIGINALFILENAME_");
		addColumn("desc", "DESC_");
		addColumn("locked", "LOCKED_");
		addColumn("path", "PATH_");
		addColumn("uuid", "UUID_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public WxFileQuery pathLike(String pathLike) {
		if (pathLike == null) {
			throw new RuntimeException("path is null");
		}
		this.pathLike = pathLike;
		return this;
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

	public void setFilenameLike(String filenameLike) {
		this.filenameLike = filenameLike;
	}

	public void setPathLike(String pathLike) {
		this.pathLike = pathLike;
	}

	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUuids(List<String> uuids) {
		this.uuids = uuids;
	}

	public WxFileQuery titleLike(String titleLike) {
		if (titleLike == null) {
			throw new RuntimeException("title is null");
		}
		this.titleLike = titleLike;
		return this;
	}

	public WxFileQuery uuid(String uuid) {
		if (uuid == null) {
			throw new RuntimeException("uuid is null");
		}
		this.uuid = uuid;
		return this;
	}

	public WxFileQuery uuids(List<String> uuids) {
		if (uuids == null) {
			throw new RuntimeException("uuids is empty ");
		}
		this.uuids = uuids;
		return this;
	}

}