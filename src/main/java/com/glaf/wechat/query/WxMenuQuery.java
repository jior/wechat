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
	protected Long appId;
	protected List<Long> appIds;
	protected List<Long> ids;
	protected String group;
	protected String name;
	protected String nameLike;
	protected List<String> names;
	protected String key;
	protected String keyLike;
	protected List<String> keys;
	protected String urlLike;
	protected Integer sortGreaterThanOrEqual;
	protected Integer sortLessThanOrEqual;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxMenuQuery() {

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

	public Long getAppId() {
		return appId;
	}

	public List<Long> getAppIds() {
		return appIds;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public String getGroup() {
		return group;
	}

	public List<Long> getIds() {
		return ids;
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

			if ("createBy".equals(sortColumn)) {
				orderBy = "E.CREATEBY_" + a_x;
			}

			if ("createDate".equals(sortColumn)) {
				orderBy = "E.CREATEDATE_" + a_x;
			}

		}
		return orderBy;
	}

	public Long getParentId() {
		return parentId;
	}

	public List<Long> getParentIds() {
		return parentIds;
	}

	public Integer getSortGreaterThanOrEqual() {
		return sortGreaterThanOrEqual;
	}

	public Integer getSortLessThanOrEqual() {
		return sortLessThanOrEqual;
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

	public WxMenuQuery group(String group) {
		if (group == null) {
			throw new RuntimeException("group is null");
		}
		this.group = group;
		return this;
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
		addColumn("treeId", "TREEID_");
		addColumn("locked", "LOCKED_");
		addColumn("icon", "ICON_");
		addColumn("iconCls", "ICONCLS_");
		addColumn("group", "GROUP_");
		addColumn("desc", "DESC_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
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

	public WxMenuQuery parentId(Long parentId) {
		if (parentId == null) {
			throw new RuntimeException("parentId is null");
		}
		this.parentId = parentId;
		return this;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setAppIds(List<Long> appIds) {
		this.appIds = appIds;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setParentIds(List<Long> parentIds) {
		this.parentIds = parentIds;
	}

	public void setSortGreaterThanOrEqual(Integer sortGreaterThanOrEqual) {
		this.sortGreaterThanOrEqual = sortGreaterThanOrEqual;
	}

	public void setSortLessThanOrEqual(Integer sortLessThanOrEqual) {
		this.sortLessThanOrEqual = sortLessThanOrEqual;
	}

	public void setUrlLike(String urlLike) {
		this.urlLike = urlLike;
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

	public WxMenuQuery urlLike(String urlLike) {
		if (urlLike == null) {
			throw new RuntimeException("url is null");
		}
		this.urlLike = urlLike;
		return this;
	}

}