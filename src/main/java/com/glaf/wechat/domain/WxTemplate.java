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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.JSONable;
import com.glaf.wechat.util.WxTemplateJsonFactory;

/**
 * 
 * 模板信息
 * 
 */
@Entity
@Table(name = "WX_TEMPLATE")
public class WxTemplate implements java.io.Serializable, JSONable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected long id;

	/**
	 * 栏目编号
	 */
	@Column(name = "CATEGORYID_")
	protected long categoryId;

	/**
	 * 名称
	 */
	@Column(name = "NAME_", length = 100)
	protected String name;

	/**
	 * 模板类型(html,ftl)
	 */
	@Column(name = "TEMPLATETYPE_", length = 50)
	protected String templateType;

	/**
	 * 描述
	 */
	@Column(name = "DESC_", length = 500)
	protected String desc;

	@Lob
	@Column(name = "CONTENT_", length = 4000)
	protected String content;

	/**
	 * 模板图片
	 */
	@Column(name = "SKINIMAGE_", length = 250)
	protected String skinImage;

	/**
	 * 类型（首页0|列表1|详细2）
	 */
	@Column(name = "TYPE_", length = 50)
	protected String type;

	/**
	 * 存放路径，相对于应用根目录的路径
	 */
	@Column(name = "PATH_", length = 500)
	protected String path;

	/**
	 * 是否默认模板
	 */
	@Column(name = "DEFAULTFLAG_")
	protected int defaultFlag;

	/**
	 * 是否启用
	 */
	@Column(name = "LOCKED_")
	protected int locked;

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

	public WxTemplate() {

	}

	public long getCategoryId() {
		return categoryId;
	}

	public String getContent() {
		return content;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public int getDefaultFlag() {
		return defaultFlag;
	}

	public String getDesc() {
		return desc;
	}

	public long getId() {
		return id;
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

	public String getSkinImage() {
		return skinImage;
	}

	public String getTemplateType() {
		return templateType;
	}

	public String getType() {
		return type;
	}

 

	public String getUuid() {
		return uuid;
	}

	public WxTemplate jsonToObject(JSONObject jsonObject) {
		return WxTemplateJsonFactory.jsonToObject(jsonObject);
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setSkinImage(String skinImage) {
		this.skinImage = skinImage;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public void setType(String type) {
		this.type = type;
	}

	 

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public JSONObject toJsonObject() {
		return WxTemplateJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxTemplateJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
