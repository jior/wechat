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
import com.glaf.core.util.FileUtils;
import com.glaf.wechat.util.WxFileJsonFactory;

/**
 * 
 * 文件信息（图片，声像资料）
 * 
 */
@Entity
@Table(name = "WX_FILE")
public class WxFile implements java.io.Serializable, JSONable {

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
	 * 栏目编号
	 */
	@Column(name = "CATEGORYID_")
	protected long categoryId;

	/**
	 * 文件标题
	 */
	@Column(name = "TITLE_", length = 200)
	protected String title;

	/**
	 * 描述
	 */
	@Column(name = "DESC_", length = 500)
	protected String desc;

	/**
	 * 内容
	 */
	@Lob
	@Column(name = "CONTENT_", length = 2048)
	protected String content;

	/**
	 * 文件名称
	 */
	@Column(name = "FILENAME_", length = 200)
	protected String filename;

	@Column(name = "ORIGINALFILENAME_", length = 200)
	protected String originalFilename;

	/**
	 * 存放路径，相对于应用根目录的路径
	 */
	@Column(name = "PATH_", length = 500)
	protected String path;

	@Column(name = "SIZE_")
	protected long size;

	/**
	 * 文件类型
	 */
	@Column(name = "TYPE_", length = 50)
	protected String type;

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

	@javax.persistence.Transient
	protected boolean image;

	@javax.persistence.Transient
	protected String fileExt;

	public WxFile() {

	}

	public String getFileExt() {
		if (filename != null) {
			fileExt = FileUtils.getFileExt(filename.toLowerCase());
		}
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Long getAccountId() {
		return accountId;
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

	public String getDesc() {
		return desc;
	}

	public String getFilename() {
		return filename;
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

	public String getOriginalFilename() {
		return originalFilename;
	}

	public String getPath() {
		return path;
	}

	public long getSize() {
		return size;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public String getUuid() {
		return uuid;
	}

	public boolean isImage() {
		image = false;
		if (path != null
				&& (path.toLowerCase().endsWith(".jpg")
						|| path.toLowerCase().endsWith(".gif")
						|| path.toLowerCase().endsWith(".png") || path
						.toLowerCase().endsWith(".bmp"))) {
			image = true;
		}
		return image;
	}

	public WxFile jsonToObject(JSONObject jsonObject) {
		return WxFileJsonFactory.jsonToObject(jsonObject);
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setImage(boolean isImage) {
		this.image = isImage;
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

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public JSONObject toJsonObject() {
		return WxFileJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxFileJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
