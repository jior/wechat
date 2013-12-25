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
package com.glaf.wechat.query;

import java.util.*;
import com.glaf.core.query.DataQuery;

public class WxModuleQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected String code;
	protected String codeLike;
	protected List<String> codes;
	protected String title;
	protected String titleLike;
	protected String linkType;
	protected String moduleId;
	protected List<String> moduleIds;
	protected String moduleNameLike;

	public WxModuleQuery() {

	}

	public String getCode() {
		return code;
	}

	public String getCodeLike() {
		if (codeLike != null && codeLike.trim().length() > 0) {
			if (!codeLike.startsWith("%")) {
				codeLike = "%" + codeLike;
			}
			if (!codeLike.endsWith("%")) {
				codeLike = codeLike + "%";
			}
		}
		return codeLike;
	}

	public List<String> getCodes() {
		return codes;
	}

	public String getTitle() {
		return title;
	}

	public String getTitleLike() {
		if (titleLike != null && titleLike.trim().length() > 0) {
			if (!titleLike.startsWith("%")) {
				titleLike = "%" + titleLike;
			}
			if (!titleLike.endsWith("%")) {
				titleLike = titleLike + "%";
			}
		}
		return titleLike;
	}

	public String getLinkType() {
		return linkType;
	}

	public String getModuleId() {
		return moduleId;
	}

	public List<String> getModuleIds() {
		return moduleIds;
	}

	public String getModuleNameLike() {
		if (moduleNameLike != null && moduleNameLike.trim().length() > 0) {
			if (!moduleNameLike.startsWith("%")) {
				moduleNameLike = "%" + moduleNameLike;
			}
			if (!moduleNameLike.endsWith("%")) {
				moduleNameLike = moduleNameLike + "%";
			}
		}
		return moduleNameLike;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public void setModuleIds(List<String> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public void setModuleNameLike(String moduleNameLike) {
		this.moduleNameLike = moduleNameLike;
	}

	public WxModuleQuery code(String code) {
		if (code == null) {
			throw new RuntimeException("code is null");
		}
		this.code = code;
		return this;
	}

	public WxModuleQuery codeLike(String codeLike) {
		if (codeLike == null) {
			throw new RuntimeException("code is null");
		}
		this.codeLike = codeLike;
		return this;
	}

	public WxModuleQuery codes(List<String> codes) {
		if (codes == null) {
			throw new RuntimeException("codes is empty ");
		}
		this.codes = codes;
		return this;
	}

	public WxModuleQuery title(String title) {
		if (title == null) {
			throw new RuntimeException("title is null");
		}
		this.title = title;
		return this;
	}

	public WxModuleQuery titleLike(String titleLike) {
		if (titleLike == null) {
			throw new RuntimeException("title is null");
		}
		this.titleLike = titleLike;
		return this;
	}

	public WxModuleQuery linkType(String linkType) {
		if (linkType == null) {
			throw new RuntimeException("linkType is null");
		}
		this.linkType = linkType;
		return this;
	}

	public WxModuleQuery moduleId(String moduleId) {
		if (moduleId == null) {
			throw new RuntimeException("moduleId is null");
		}
		this.moduleId = moduleId;
		return this;
	}

	public WxModuleQuery moduleIds(List<String> moduleIds) {
		if (moduleIds == null) {
			throw new RuntimeException("moduleIds is empty ");
		}
		this.moduleIds = moduleIds;
		return this;
	}

	public WxModuleQuery moduleNameLike(String moduleNameLike) {
		if (moduleNameLike == null) {
			throw new RuntimeException("moduleName is null");
		}
		this.moduleNameLike = moduleNameLike;
		return this;
	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("code".equals(sortColumn)) {
				orderBy = "E.CODE_" + a_x;
			}

			if ("title".equals(sortColumn)) {
				orderBy = "E.TITLE_" + a_x;
			}

			if ("content".equals(sortColumn)) {
				orderBy = "E.CONTENT_" + a_x;
			}

			if ("link".equals(sortColumn)) {
				orderBy = "E.LINK_" + a_x;
			}

			if ("listLink".equals(sortColumn)) {
				orderBy = "E.LISTLINK_" + a_x;
			}

			if ("linkType".equals(sortColumn)) {
				orderBy = "E.LINKTYPE_" + a_x;
			}

			if ("idField".equals(sortColumn)) {
				orderBy = "E.IDFIELD_" + a_x;
			}

			if ("subjectField".equals(sortColumn)) {
				orderBy = "E.SUBJECTFIELD_" + a_x;
			}

			if ("moduleId".equals(sortColumn)) {
				orderBy = "E.MODULEID_" + a_x;
			}

			if ("moduleName".equals(sortColumn)) {
				orderBy = "E.MODULENAME_" + a_x;
			}

			if ("sql".equals(sortColumn)) {
				orderBy = "E.SQL_" + a_x;
			}

			if ("json".equals(sortColumn)) {
				orderBy = "E.JSON_" + a_x;
			}

			if ("locked".equals(sortColumn)) {
				orderBy = "E.LOCKED_" + a_x;
			}

		}
		return orderBy;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("code", "CODE_");
		addColumn("title", "TITLE_");
		addColumn("content", "CONTENT_");
		addColumn("link", "LINK_");
		addColumn("listLink", "LISTLINK_");
		addColumn("linkType", "LINKTYPE_");
		addColumn("idField", "IDFIELD_");
		addColumn("subjectField", "SUBJECTFIELD_");
		addColumn("moduleId", "MODULEID_");
		addColumn("moduleName", "MODULENAME_");
		addColumn("sql", "SQL_");
		addColumn("json", "JSON_");
		addColumn("locked", "LOCKED_");
	}

}