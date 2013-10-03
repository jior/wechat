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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.JSONable;
import com.glaf.wechat.util.WxContentJsonFactory;

/**
 * 
 * 文章信息
 * 
 */
@Entity
@Table(name = "WX_CONTENT")
public class WxContent implements java.io.Serializable, JSONable {

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
	 * 标题
	 */
	@Column(name = "TITLE_", length = 200)
	protected String title;

	/**
	 * 内容
	 */
	@Lob
	@Column(name = "CONTENT_", length = 2048)
	protected String content;

	/**
	 * 作者
	 */
	@Column(name = "AUTHOR_", length = 50)
	protected String author;

	/**
	 * 状态
	 */
	@Column(name = "STATUS_")
	protected int status;

	/**
	 * 序号
	 */
	@Column(name = "SORT_")
	protected int sort;

	/**
	 * 优先级
	 */
	@Column(name = "PRIORTY_")
	protected int priority;

	/**
	 * 类别（关注时回复F，默认回复D，关键字回复K，LBS回复L）
	 */
	@Column(name = "TYPE_", length = 20)
	protected String type;

	/**
	 * UUID
	 */
	@Column(name = "UUID_", length = 50)
	protected String uuid;

	/**
	 * 关键字
	 */
	@Column(name = "KEYWORDS_", length = 250)
	protected String keywords;

	/**
	 * 关键字数量
	 */
	@Column(name = "KEYWORDSCOUNT_")
	protected int keywordsCount;

	/**
	 * 关键字匹配类型
	 */
	@Column(name = "KEYWORDSMATCHTYPE_", length = 10)
	protected String keywordsMatchType;

	/**
	 * 关联的内容编号，多个之间用逗号隔开
	 */
	@Column(name = "RELATIONIDS_", length = 100)
	protected String relationIds;

	/**
	 * 摘要
	 */
	@Column(name = "SUMMARY_", length = 250)
	protected String summary;

	/**
	 * 封面图标
	 */
	@Column(name = "ICON_", length = 150)
	protected String icon;

	/**
	 * 大图标
	 */
	@Column(name = "BIGICON_", length = 150)
	protected String bigIcon;

	/**
	 * 小图标
	 */
	@Column(name = "SMALLICON_", length = 150)
	protected String smallIcon;

	/**
	 * 原文链接
	 */
	@Column(name = "URL_", length = 500)
	protected String url;

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
	protected List<WxContent> relations = new ArrayList<WxContent>();

	public WxContent() {

	}

	public String getAuthor() {
		return author;
	}

	public String getBigIcon() {
		return bigIcon;
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

	public String getIcon() {
		return icon;
	}

	public long getId() {
		return id;
	}

	public String getKeywords() {
		return keywords;
	}

	public int getKeywordsCount() {
		return keywordsCount;
	}

	public String getKeywordsMatchType() {
		return keywordsMatchType;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public int getPriority() {
		return priority;
	}

	public String getRelationIds() {
		return relationIds;
	}

	public List<WxContent> getRelations() {
		return relations;
	}

	public String getSmallIcon() {
		return smallIcon;
	}

	public int getSort() {
		return sort;
	}

	public int getStatus() {
		return status;
	}

	public String getSummary() {
		return summary;
	}

	public String getTitle() {
		return title;
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

	public WxContent jsonToObject(JSONObject jsonObject) {
		return WxContentJsonFactory.jsonToObject(jsonObject);
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
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

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setKeywordsCount(int keywordsCount) {
		this.keywordsCount = keywordsCount;
	}

	public void setKeywordsMatchType(String keywordsMatchType) {
		this.keywordsMatchType = keywordsMatchType;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setRelationIds(String relationIds) {
		this.relationIds = relationIds;
	}

	public void setRelations(List<WxContent> relations) {
		this.relations = relations;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return WxContentJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxContentJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
