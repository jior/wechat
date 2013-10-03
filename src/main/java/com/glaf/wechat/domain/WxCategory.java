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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.glaf.wechat.util.WxCategoryJsonFactory;

/**
 * 
 * ��Ŀ��Ϣ
 * 
 */
@Entity
@Table(name = "WX_CATEGORY")
public class WxCategory implements java.io.Serializable, JSONable {

	private static final long serialVersionUID = 1L;

	/**
	 * ��ţ�����
	 */
	@Id
	@Column(name = "ID_", nullable = false)
	protected long id;

	/**
	 * ���ڵ���
	 */
	@Column(name = "PARENT_")
	protected long parentId;

	/**
	 * �����
	 */
	@Column(name = "TREEID_", length = 500)
	protected String treeId;

	/**
	 * ���
	 */
	@Column(name = "SORT_")
	protected int sort;

	/**
	 * ͼ��
	 */
	@Column(name = "ICON_", length = 150)
	protected String icon;

	/**
	 * ͼ����ʽ
	 */
	@Column(name = "ICONCLS_", length = 50)
	protected String iconCls;

	/**
	 * ����ͼ��
	 */
	@Column(name = "COVERICON_", length = 150)
	protected String coverIcon;

	/**
	 * �Ƿ���ҳ��ʾ
	 */
	@Column(name = "INDEXSHOW_")
	protected int indexShow;

	/**
	 * �Ƿ�����
	 */
	@Column(name = "LOCKED_")
	protected int locked;

	/**
	 * ����
	 */
	@Column(name = "NAME_", length = 100)
	protected String name;

	/**
	 * ����
	 */
	@Column(name = "CODE_", length = 50)
	protected String code;

	/**
	 * ����
	 */
	@Column(name = "DESC_", length = 500)
	protected String desc;

	/**
	 * ��Ŀ����(category, menu)
	 */
	@Column(name = "TYPE_", length = 50)
	protected String type;

	/**
	 * �¼�����
	 */
	@Column(name = "EVENTTYPE_", length = 50)
	protected String eventType;

	/**
	 * ���ӵ�ַ
	 */
	@Column(name = "URL_", length = 500)
	protected String url;

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

	@javax.persistence.Transient
	protected List<WxCategory> children = new ArrayList<WxCategory>();

	public WxCategory() {

	}

	public void addChild(WxCategory child) {
		if (children == null) {
			children = new ArrayList<WxCategory>();
		}
		children.add(child);
	}

	public List<WxCategory> getChildren() {
		return children;
	}

	public String getCode() {
		return code;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getDesc() {
		return desc;
	}

	public String getEventType() {
		return eventType;
	}

	public String getIcon() {
		return icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	public long getId() {
		return id;
	}

	 

	public int getIndexShow() {
		return indexShow;
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

	public String getTreeId() {
		return treeId;
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

	public WxCategory jsonToObject(JSONObject jsonObject) {
		return WxCategoryJsonFactory.jsonToObject(jsonObject);
	}

	public void setChildren(List<WxCategory> children) {
		this.children = children;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setId(long id) {
		this.id = id;
	}

	 

	public String getCoverIcon() {
		return coverIcon;
	}

	public void setCoverIcon(String coverIcon) {
		this.coverIcon = coverIcon;
	}

	public void setIndexShow(int indexShow) {
		this.indexShow = indexShow;
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

	public void setTreeId(String treeId) {
		this.treeId = treeId;
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
		return WxCategoryJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxCategoryJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
