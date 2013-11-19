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
@Table(name = "WX_KEYWORDS")
public class WxKeywords implements Serializable, JSONable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected Long id;

	/**
	 * 微站公众号应用ID
	 */
	@Column(name = "ACCOUNTID_")
	protected Long accountId;

	@Column(name = "CATEGORYID_")
	protected Long categoryId;

	@Column(name = "CONTENTID_", length = 250)
	protected Long contentId;

	@Column(name = "KEYWORDS_", length = 250)
	protected String keywords;

	@Column(name = "KEYWORDSMATCHTYPE_", length = 250)
	protected String keywordsMatchType;

	@Column(name = "CREATEBY_", length = 50)
	protected String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE_")
	protected Date createDate;

	public WxKeywords() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxKeywords other = (WxKeywords) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Long getCategoryId() {
		return this.categoryId;
	}

	public Long getContentId() {
		return this.contentId;
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

	public String getKeywords() {
		return this.keywords;
	}

	public String getKeywordsMatchType() {
		return this.keywordsMatchType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public WxKeywords jsonToObject(JSONObject jsonObject) {
		return WxKeywordsJsonFactory.jsonToObject(jsonObject);
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
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

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setKeywordsMatchType(String keywordsMatchType) {
		this.keywordsMatchType = keywordsMatchType;
	}

	public JSONObject toJsonObject() {
		return WxKeywordsJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxKeywordsJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
