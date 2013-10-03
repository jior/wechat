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

public class WxMenuQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected List<Long> ids;
	protected String name;
	protected String nameLike;
	protected List<String> names;
	protected String type;
	protected List<String> types;
	protected String key;
	protected String keyLike;
	protected List<String> keys;
	protected String urlLike;
	protected Integer sortGreaterThanOrEqual;
	protected Integer sortLessThanOrEqual;
	protected String uuid;
	protected List<String> uuids;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxMenuQuery() {

	}

	public Long getParentId() {
		return parentId;
	}

	public List<Long> getParentIds() {
		return parentIds;
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

	public String getType() {
		return type;
	}

	public List<String> getTypes() {
		return types;
	}

	public String getKey() {
		return key;
	}

	public String getKeyLike() {
		if (keyLike != null && keyLike.trim().length() > 0) {
			if (!keyLike.startsWith("%")) {
				keyLike = "%" + keyLike;
			}
			if (!keyLike.endsWith("%")) {
				keyLike = keyLike + "%";
			}
		}
		return keyLike;
	}

	public List<String> getKeys() {
		return keys;
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

	public Integer getSortGreaterThanOrEqual() {
		return sortGreaterThanOrEqual;
	}

	public Integer getSortLessThanOrEqual() {
		return sortLessThanOrEqual;
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

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setParentIds(List<Long> parentIds) {
		this.parentIds = parentIds;
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

	public void setType(String type) {
		this.type = type;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKeyLike(String keyLike) {
		this.keyLike = keyLike;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public void setUrlLike(String urlLike) {
		this.urlLike = urlLike;
	}

	public void setSortGreaterThanOrEqual(Integer sortGreaterThanOrEqual) {
		this.sortGreaterThanOrEqual = sortGreaterThanOrEqual;
	}

	public void setSortLessThanOrEqual(Integer sortLessThanOrEqual) {
		this.sortLessThanOrEqual = sortLessThanOrEqual;
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

	public WxMenuQuery parentId(Long parentId) {
		if (parentId == null) {
			throw new RuntimeException("parentId is null");
		}
		this.parentId = parentId;
		return this;
	}

	public WxMenuQuery name(String name) {
		if (name == null) {
			throw new RuntimeException("name is null");
		}
		this.name = name;
		return this;
	}

	public WxMenuQuery nameLike(String nameLike) {
		if (nameLike == null) {
			throw new RuntimeException("name is null");
		}
		this.nameLike = nameLike;
		return this;
	}

	public WxMenuQuery names(List<String> names) {
		if (names == null) {
			throw new RuntimeException("names is empty ");
		}
		this.names = names;
		return this;
	}

	public WxMenuQuery type(String type) {
		if (type == null) {
			throw new RuntimeException("type is null");
		}
		this.type = type;
		return this;
	}

	public WxMenuQuery types(List<String> types) {
		if (types == null) {
			throw new RuntimeException("types is empty ");
		}
		this.types = types;
		return this;
	}

	public WxMenuQuery key(String key) {
		if (key == null) {
			throw new RuntimeException("key is null");
		}
		this.key = key;
		return this;
	}

	public WxMenuQuery keyLike(String keyLike) {
		if (keyLike == null) {
			throw new RuntimeException("key is null");
		}
		this.keyLike = keyLike;
		return this;
	}

	public WxMenuQuery keys(List<String> keys) {
		if (keys == null) {
			throw new RuntimeException("keys is empty ");
		}
		this.keys = keys;
		return this;
	}

	public WxMenuQuery urlLike(String urlLike) {
		if (urlLike == null) {
			throw new RuntimeException("url is null");
		}
		this.urlLike = urlLike;
		return this;
	}

	public WxMenuQuery sortGreaterThanOrEqual(Integer sortGreaterThanOrEqual) {
		if (sortGreaterThanOrEqual == null) {
			throw new RuntimeException("sort is null");
		}
		this.sortGreaterThanOrEqual = sortGreaterThanOrEqual;
		return this;
	}

	public WxMenuQuery sortLessThanOrEqual(Integer sortLessThanOrEqual) {
		if (sortLessThanOrEqual == null) {
			throw new RuntimeException("sort is null");
		}
		this.sortLessThanOrEqual = sortLessThanOrEqual;
		return this;
	}

	public WxMenuQuery uuid(String uuid) {
		if (uuid == null) {
			throw new RuntimeException("uuid is null");
		}
		this.uuid = uuid;
		return this;
	}

	public WxMenuQuery uuids(List<String> uuids) {
		if (uuids == null) {
			throw new RuntimeException("uuids is empty ");
		}
		this.uuids = uuids;
		return this;
	}

	public WxMenuQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxMenuQuery createDateLessThanOrEqual(Date createDateLessThanOrEqual) {
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

			if ("parentId".equals(sortColumn)) {
				orderBy = "E.PARENT_" + a_x;
			}

			if ("name".equals(sortColumn)) {
				orderBy = "E.NAME_" + a_x;
			}

			if ("type".equals(sortColumn)) {
				orderBy = "E.TYPE_" + a_x;
			}

			if ("key".equals(sortColumn)) {
				orderBy = "E.KEY_" + a_x;
			}

			if ("url".equals(sortColumn)) {
				orderBy = "E.URL_" + a_x;
			}

			if ("sort".equals(sortColumn)) {
				orderBy = "E.SORT_" + a_x;
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
		addColumn("parentId", "PARENT_");
		addColumn("name", "NAME_");
		addColumn("type", "TYPE_");
		addColumn("key", "KEY_");
		addColumn("url", "URL_");
		addColumn("sort", "SORT_");
		addColumn("uuid", "UUID_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

}