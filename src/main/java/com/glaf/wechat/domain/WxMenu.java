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
import com.glaf.wechat.util.WxMenuJsonFactory;

/**
 * 
 * 菜单信息
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
	 * 微站公众号应用ID
	 */
	@Column(name = "ACCOUNTID_")
	protected Long accountId;

	/**
	 * 父节点编号
	 */
	@Column(name = "PARENT_")
	protected long parentId;

	/**
	 * 按钮名称，不超过16个字节，子菜单不超过40个字节
	 */
	@Column(name = "NAME_", length = 50)
	protected String name;

	/**
	 * 按钮类型，目前有click类型
	 */
	@Column(name = "TYPE_", length = 50)
	protected String type;

	/**
	 * 按钮KEY值，用于消息接口(event类型)推送，不超过128字节
	 */
	@Column(name = "KEY_", length = 200)
	protected String key;

	/**
	 * 链接地址
	 */
	@Column(name = "URL_", length = 500)
	protected String url;

	/**
	 * 图片链接地址
	 */
	@Column(name = "PICURL_", length = 500)
	protected String picUrl;

	/**
	 * 描述
	 */
	@Column(name = "DESC_", length = 500)
	protected String desc;

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
	 * 分组
	 */
	@Column(name = "GROUP_", length = 50)
	protected String group;

	/**
	 * 树编号
	 */
	@Column(name = "TREEID_", length = 500)
	protected String treeId;

	/**
	 * 顺序号
	 */
	@Column(name = "SORT_")
	protected int sort;

	/**
	 * 是否启用
	 */
	@Column(name = "LOCKED_")
	protected int locked;

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
	protected List<WxMenu> children = new ArrayList<WxMenu>();

	public WxMenu() {

	}

	public void addChild(WxMenu menu) {
		if (menu == null) {
			children = new ArrayList<WxMenu>();
		}
		children.add(menu);
	}

	public Long getAccountId() {
		return accountId;
	}

	public List<WxMenu> getChildren() {
		return children;
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

	public String getGroup() {
		return group;
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

	public String getPicUrl() {
		return picUrl;
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

	public WxMenu jsonToObject(JSONObject jsonObject) {
		return WxMenuJsonFactory.jsonToObject(jsonObject);
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setChildren(List<WxMenu> children) {
		this.children = children;
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

	public void setGroup(String group) {
		this.group = group;
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

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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
