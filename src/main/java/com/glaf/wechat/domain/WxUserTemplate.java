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

/**
 * 用户模板实例 (用户指定栏目及类型的模板编号)
 */
@Entity
@Table(name = "WX_USER_TEMPLATE")
public class WxUserTemplate implements Serializable, JSONable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@Column(name = "ID_", nullable = false)
	protected Long id;

	/**
	 * 微站公众号应用ID
	 */
	@Column(name = "APPID_")
	protected Long appId;

	/**
	 * 栏目编号
	 */
	@Column(name = "CATEGORYID_")
	protected Long categoryId;

	/**
	 * 模板编号
	 */
	@Column(name = "templateId_")
	protected Long templateId;

	/**
	 * 模板类型
	 */
	@Column(name = "TYPE_", length = 50)
	protected String type;

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

	public WxUserTemplate() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxUserTemplate other = (WxUserTemplate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getAppId() {
		return appId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public Long getId() {
		return this.id;
	}

	public Long getTemplateId() {
		return this.templateId;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public WxUserTemplate jsonToObject(JSONObject jsonObject) {
		return WxUserTemplateJsonFactory.jsonToObject(jsonObject);
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JSONObject toJsonObject() {
		return WxUserTemplateJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxUserTemplateJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
