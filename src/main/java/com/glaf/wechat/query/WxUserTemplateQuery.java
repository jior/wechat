package com.glaf.wechat.query;

import java.util.*;

import com.glaf.core.query.DataQuery;

public class WxUserTemplateQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected List<Long> ids;
	protected Long categoryId;
	protected List<Long> categoryIds;
	protected Long templateId;
	protected List<Long> templateIds;
	protected String type;
	protected List<String> createBys;

	public WxUserTemplateQuery() {

	}

	public WxUserTemplateQuery categoryId(Long categoryId) {
		if (categoryId == null) {
			throw new RuntimeException("categoryId is null");
		}
		this.categoryId = categoryId;
		return this;
	}

	public WxUserTemplateQuery categoryIds(List<Long> categoryIds) {
		if (categoryIds == null) {
			throw new RuntimeException("categoryIds is empty ");
		}
		this.categoryIds = categoryIds;
		return this;
	}

	public WxUserTemplateQuery createBys(List<String> createBys) {
		if (createBys == null) {
			throw new RuntimeException("createBys is empty ");
		}
		this.createBys = createBys;
		return this;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public String getCreateBy() {
		return createBy;
	}

	public List<String> getCreateBys() {
		return createBys;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public List<Long> getIds() {
		return ids;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("categoryId".equals(sortColumn)) {
				orderBy = "E.CATEGORYID_" + a_x;
			}

			if ("templateId".equals(sortColumn)) {
				orderBy = "E.TEMPLATEID_" + a_x;
			}

			if ("type".equals(sortColumn)) {
				orderBy = "E.TYPE_" + a_x;
			}

			if ("createBy".equals(sortColumn)) {
				orderBy = "E.CREATEBY_" + a_x;
			}

			if ("createDate".equals(sortColumn)) {
				orderBy = "E.CREATEDATE_" + a_x;
			}

		}
		return orderBy;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public List<Long> getTemplateIds() {
		return templateIds;
	}

	public String getType() {
		return type;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("categoryId", "CATEGORYID_");
		addColumn("templateId", "TEMPLATEID_");
		addColumn("type", "TYPE_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateBys(List<String> createBys) {
		this.createBys = createBys;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public void setTemplateIds(List<Long> templateIds) {
		this.templateIds = templateIds;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WxUserTemplateQuery templateId(Long templateId) {
		if (templateId == null) {
			throw new RuntimeException("templateId is null");
		}
		this.templateId = templateId;
		return this;
	}

	public WxUserTemplateQuery templateIds(List<Long> templateIds) {
		if (templateIds == null) {
			throw new RuntimeException("templateIds is empty ");
		}
		this.templateIds = templateIds;
		return this;
	}

	public WxUserTemplateQuery type(String type) {
		if (type == null) {
			throw new RuntimeException("type is null");
		}
		this.type = type;
		return this;
	}

}