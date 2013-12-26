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

package com.glaf.wechat.domain;

import java.io.*;
import java.util.*;

import javax.persistence.*;

import com.alibaba.fastjson.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.glaf.core.base.*;
import com.glaf.wechat.util.*;

@Entity
@Table(name = "WX_MODULE")
public class WxModule implements Serializable, JSONable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected long id;

	@Column(name = "CODE_", length = 50)
	protected String code;

	@Column(name = "TITLE_", length = 200)
	protected String title;

	@Column(name = "CONTENT_", length = 255)
	protected String content;

	@Column(name = "LINK_", length = 250)
	protected String link;

	@Column(name = "LISTLINK_", length = 250)
	protected String listLink;

	@Column(name = "LINKTYPE_", length = 50)
	protected String linkType;

	@Column(name = "IDFIELD_", length = 50, nullable = false)
	protected String idField;

	@Column(name = "SUBJECTFIELD_", length = 50, nullable = false)
	protected String subjectField;

	@Column(name = "MODULEID_", length = 50, nullable = false)
	protected String moduleId;

	@Column(name = "MODULENAME_", length = 200, nullable = false)
	protected String moduleName;

	@Column(name = "SQL_", length = 2000, nullable = false)
	protected String sql;

	@Column(name = "JSON_", length = 500)
	protected String json;

	@Column(name = "LOCKED_", nullable = false)
	protected int locked;

	@Column(name = "SORT_")
	protected int sort;

	@javax.persistence.Transient
	protected List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

	public WxModule() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxModule other = (WxModule) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getCode() {
		return this.code;
	}

	public String getContent() {
		return this.content;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public long getId() {
		return this.id;
	}

	public String getIdField() {
		return this.idField;
	}

	public String getJson() {
		return this.json;
	}

	public String getLink() {
		return this.link;
	}

	public String getLinkType() {
		return this.linkType;
	}

	public String getListLink() {
		return this.listLink;
	}

	public int getLocked() {
		return this.locked;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public int getSort() {
		return sort;
	}

	public String getSql() {
		return this.sql;
	}

	public String getSubjectField() {
		return this.subjectField;
	}

	public String getTitle() {
		return this.title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	public WxModule jsonToObject(JSONObject jsonObject) {
		return WxModuleJsonFactory.jsonToObject(jsonObject);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public void setListLink(String listLink) {
		this.listLink = listLink;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setSubjectField(String subjectField) {
		this.subjectField = subjectField;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JSONObject toJsonObject() {
		return WxModuleJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxModuleJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
