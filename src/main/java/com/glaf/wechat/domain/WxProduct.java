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
@Table(name = "WX_WXPRODUCT")
public class WxProduct implements Serializable, JSONable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected Long id;

	@Column(name = "NAME_", length = 100)
	protected String name;

	@Column(name = "PRICE_")
	protected Double price;

	@Column(name = "NEWSNUM_")
	protected Integer newsNum;

	@Column(name = "CATEGORYNUM_")
	protected Integer categoryNum;

	@Column(name = "DAYNUM_")
	protected Integer dayNum;

	@Column(name = "CREATEBY_", length = 50)
	protected String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE_")
	protected Date createDate;

	public WxProduct() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public Double getPrice() {
		return this.price;
	}

	public Integer getNewsNum() {
		return this.newsNum;
	}

	public Integer getCategoryNum() {
		return this.categoryNum;
	}

	public Integer getDayNum() {
		return this.dayNum;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setNewsNum(Integer newsNum) {
		this.newsNum = newsNum;
	}

	public void setCategoryNum(Integer categoryNum) {
		this.categoryNum = categoryNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxProduct other = (WxProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public WxProduct jsonToObject(JSONObject jsonObject) {
		return WxProductJsonFactory.jsonToObject(jsonObject);
	}

	public JSONObject toJsonObject() {
		return WxProductJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxProductJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
