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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.JSONable;
import com.glaf.wechat.util.WxMenuJsonFactory;

/**
 * 
 * �˵���Ϣ
 * 
 */
@Entity
@Table(name = "WX_MENU")
public class WxMenu implements java.io.Serializable, JSONable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected long id;

	/**
	 * ���ڵ���
	 */
	@Column(name = "PARENT_")
	protected long parentId;

	/**
	 * ����
	 */
	@Column(name = "NAME_", length = 100)
	protected String name;

	/**
	 * ����
	 */
	@Column(name = "TYPE_", length = 50)
	protected String type;

	/**
	 * Key
	 */
	@Column(name = "KEY_", length = 50)
	protected String key;

	/**
	 * ���ӵ�ַ
	 */
	@Column(name = "URL_", length = 500)
	protected String url;

	/**
	 * ˳���
	 */
	@Column(name = "SORT_")
	protected int sort;

	/**
	 * �Ƿ�����
	 */
	@Column(name = "LOCKED_")
	protected int locked;

	/**
	 * UUID
	 */
	@Column(name = "UUID_", length = 50)
	protected String uuid;

	/**
	 * ������
	 */
	@Column(name = "CREATEBY_", length = 50)
	protected String createBy;

	/**
	 * ��������
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE_")
	protected Date createDate;

	/**
	 * ����޸���
	 */
	@Column(name = "LASTUPDATEBY_", length = 50)
	protected String lastUpdateBy;

	/**
	 * ����޸�����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTUPDATEDATE_")
	protected Date lastUpdateDate;

	public WxMenu() {

	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public long getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public int getLocked() {
		return locked;
	}

	public String getName() {
		return name;
	}

	public long getParentId() {
		return parentId;
	}

	public int getSort() {
		return sort;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getUuid() {
		return uuid;
	}

	public WxMenu jsonToObject(JSONObject jsonObject) {
		return WxMenuJsonFactory.jsonToObject(jsonObject);
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public JSONObject toJsonObject() {
		return WxMenuJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxMenuJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
