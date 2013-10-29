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
 * 栏目信息
 * 
 */
@Entity
@Table(name = "WX_CATEGORY")
public class WxCategory implements java.io.Serializable, JSONable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号，主键
	 */
	@Id
	@Column(name = "ID_", nullable = false)
	protected long id;

	/**
	 * 父节点编号
	 */
	@Column(name = "PARENT_")
	protected long parentId;

	/**
	 * 树编号
	 */
	@Column(name = "TREEID_", length = 500)
	protected String treeId;

	/**
	 * 序号
	 */
	@Column(name = "SORT_")
	protected int sort;

	/**
	 * 图标
	 */
	@Column(name = "ICON_", length = 150)
	protected String icon;

	/**
	 * 图标样式
	 */
	@Column(name = "ICONCLS_", length = 50)
	protected String iconCls;

	/**
	 * 封面图标
	 */
	@Column(name = "COVERICON_", length = 150)
	protected String coverIcon;

	/**
	 * 是否首页显示
	 */
	@Column(name = "INDEXSHOW_")
	protected int indexShow;

	/**
	 * 是否启用
	 */
	@Column(name = "LOCKED_")
	protected int locked;

	/**
	 * 名称
	 */
	@Column(name = "NAME_", length = 100)
	protected String name;

	/**
	 * 编码
	 */
	@Column(name = "CODE_", length = 50)
	protected String code;

	/**
	 * 描述
	 */
	@Column(name = "DESC_", length = 500)
	protected String desc;

	/**
	 * 栏目类型(category, menu)
	 */
	@Column(name = "TYPE_", length = 50)
	protected String type;

	/**
	 * 事件类型
	 */
	@Column(name = "EVENTTYPE_", length = 50)
	protected String eventType;

	/**
	 * 链接地址
	 */
	@Column(name = "URL_", length = 500)
	protected String url;

	/**
	 * UUID
	 */
	@Column(name = "UUID_", length = 50)
	protected String uuid;

	/**
	 * 创建人
	 */
	@Column(name = "CREATEBY_", length = 50)
	protected String createBy;

	/**
	 * 创建日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE_")
	protected Date createDate;

	/**
	 * 最后修改人
	 */
	@Column(name = "LASTUPDATEBY_", length = 50)
	protected String lastUpdateBy;

	/**
	 * 最后修改日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTUPDATEDATE_")
	protected Date lastUpdateDate;

	@javax.persistence.Transient
	protected int deep;

	@javax.persistence.Transient
	protected List<WxCategory> children = new ArrayList<WxCategory>();

	@javax.persistence.Transient
	protected List<WxContent> pptList = new ArrayList<WxContent>();

	public WxCategory() {

	}

	public void addChild(WxCategory child) {
		if (children == null) {
			children = new ArrayList<WxCategory>();
		}
		children.add(child);
	}

	public void addPPT(WxContent ppt) {
		if (pptList == null) {
			pptList = new ArrayList<WxContent>();
		}
		pptList.add(ppt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxCategory other = (WxCategory) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public List<WxCategory> getChildren() {
		return children;
	}

	public String getCode() {
		return code;
	}

	public String getCoverIcon() {
		return coverIcon;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public int getDeep() {
		return deep;
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

	public List<WxContent> getPptList() {
		return pptList;
	}

	public int getSort() {
		return sort;
	}

	public String getStringId() {
		return String.valueOf(id);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
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

	public void setCoverIcon(String coverIcon) {
		this.coverIcon = coverIcon;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDeep(int deep) {
		this.deep = deep;
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

	public void setPptList(List<WxContent> pptList) {
		this.pptList = pptList;
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
