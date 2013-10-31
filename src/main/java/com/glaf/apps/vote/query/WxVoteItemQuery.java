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

package com.glaf.apps.vote.query;

import java.util.*;
import com.glaf.core.query.DataQuery;

public class WxVoteItemQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected List<Long> ids;
	protected Long voteId;
	protected List<Long> voteIds;

	public WxVoteItemQuery() {

	}

	public String getOrderBy() {
		if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

			if ("voteId".equals(sortColumn)) {
				orderBy = "E.VOTEID_" + a_x;
			}

			if ("name".equals(sortColumn)) {
				orderBy = "E.NAME_" + a_x;
			}

			if ("value".equals(sortColumn)) {
				orderBy = "E.VALUE_" + a_x;
			}

		}
		return orderBy;
	}

	public Long getVoteId() {
		return voteId;
	}

	public List<Long> getVoteIds() {
		return voteIds;
	}

	@Override
	public void initQueryColumns() {
		super.initQueryColumns();
		addColumn("id", "ID_");
		addColumn("voteId", "VOTEID_");
		addColumn("name", "NAME_");
		addColumn("value", "VALUE_");
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public void setVoteIds(List<Long> voteIds) {
		this.voteIds = voteIds;
	}

	public WxVoteItemQuery voteId(Long voteId) {
		if (voteId == null) {
			throw new RuntimeException("voteId is null");
		}
		this.voteId = voteId;
		return this;
	}

	public WxVoteItemQuery voteIds(List<Long> voteIds) {
		if (voteIds == null) {
			throw new RuntimeException("voteIds is empty ");
		}
		this.voteIds = voteIds;
		return this;
	}

}