package com.glaf.wechat.query;

import java.util.*;

import com.glaf.core.query.DataQuery;

public class WxKeywordsQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long accountId;
	protected List<Long> accountIds;
	protected List<Long> ids;
	protected Long categoryId;
	protected List<Long> categoryIds;
	protected Long contentId;
	protected List<Long> contentIds;
	protected String keywords;
	protected String keywordsLike;
	protected String keywordsMatchType;
	protected List<String> createBys;
	protected Date createDateGreaterThanOrEqual;
	protected Date createDateLessThanOrEqual;

	public WxKeywordsQuery() {

	}

	public WxKeywordsQuery categoryId(Long categoryId) {
		if (categoryId == null) {
			throw new RuntimeException("categoryId is null");
		}
		this.categoryId = categoryId;
		return this;
	}

	public WxKeywordsQuery categoryIds(List<Long> categoryIds) {
		if (categoryIds == null) {
			throw new RuntimeException("categoryIds is empty ");
		}
		this.categoryIds = categoryIds;
		return this;
	}

	public WxKeywordsQuery contentId(Long contentId) {
		if (contentId == null) {
			throw new RuntimeException("contentId is null");
		}
		this.contentId = contentId;
		return this;
	}

	public WxKeywordsQuery contentIds(List<Long> contentIds) {
		if (contentIds == null) {
			throw new RuntimeException("contentIds is empty ");
		}
		this.contentIds = contentIds;
		return this;
	}

	public WxKeywordsQuery createBys(List<String> createBys) {
		if (createBys == null) {
			throw new RuntimeException("createBys is empty ");
		}
		this.createBys = createBys;
		return this;
	}

	public WxKeywordsQuery createDate(Date createDate) {
		if (createDate == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDate = createDate;
		return this;
	}

	public WxKeywordsQuery createDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		if (createDateGreaterThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
		return this;
	}

	public WxKeywordsQuery createDateLessThanOrEqual(
			Date createDateLessThanOrEqual) {
		if (createDateLessThanOrEqual == null) {
			throw new RuntimeException("createDate is null");
		}
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
		return this;
	}

	public Long getAccountId() {
		return accountId;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public Long getContentId() {
		return contentId;
	}

	public List<Long> getContentIds() {
		return contentIds;
	}

	public List<String> getCreateBys() {
		return createBys;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getCreateDateGreaterThanOrEqual() {
		return createDateGreaterThanOrEqual;
	}

	public Date getCreateDateLessThanOrEqual() {
		return createDateLessThanOrEqual;
	}

	public List<Long> getIds() {
		return ids;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getKeywordsLike() {
		if (keywordsLike != null && keywordsLike.trim().length() > 0) {
			if (!keywordsLike.startsWith("%")) {
				keywordsLike = "%" + keywordsLike;
			}
			if (!keywordsLike.endsWith("%")) {
				keywordsLike = keywordsLike + "%";
			}
		}
		return keywordsLike;
	}

	public String getKeywordsMatchType() {
		return keywordsMatchType;
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

			if ("contentId".equals(sortColumn)) {
				orderBy = "E.CONTENTID_" + a_x;
			}

			if ("keywords".equals(sortColumn)) {
				orderBy = "E.KEYWORDS_" + a_x;
			}

			if ("keywordsMatchType".equals(sortColumn)) {
				orderBy = "E.KEYWORDSMATCHTYPE_" + a_x;
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

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("categoryId", "CATEGORYID_");
		addColumn("contentId", "CONTENTID_");
		addColumn("keywords", "KEYWORDS_");
		addColumn("keywordsMatchType", "KEYWORDSMATCHTYPE_");
		addColumn("createBy", "CREATEBY_");
		addColumn("createDate", "CREATEDATE_");
	}

	public WxKeywordsQuery keywords(String keywords) {
		if (keywords == null) {
			throw new RuntimeException("keywords is null");
		}
		this.keywords = keywords;
		return this;
	}

	public WxKeywordsQuery keywordsLike(String keywordsLike) {
		if (keywordsLike == null) {
			throw new RuntimeException("keywordsLike is null");
		}
		this.keywordsLike = keywordsLike;
		return this;
	}

	public WxKeywordsQuery keywordsMatchType(String keywordsMatchType) {
		if (keywordsMatchType == null) {
			throw new RuntimeException("keywordsMatchType is null");
		}
		this.keywordsMatchType = keywordsMatchType;
		return this;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public void setContentIds(List<Long> contentIds) {
		this.contentIds = contentIds;
	}

	public void setCreateBys(List<String> createBys) {
		this.createBys = createBys;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreateDateGreaterThanOrEqual(
			Date createDateGreaterThanOrEqual) {
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual) {
		this.createDateLessThanOrEqual = createDateLessThanOrEqual;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setKeywordsLike(String keywordsLike) {
		this.keywordsLike = keywordsLike;
	}

	public void setKeywordsMatchType(String keywordsMatchType) {
		this.keywordsMatchType = keywordsMatchType;
	}

}