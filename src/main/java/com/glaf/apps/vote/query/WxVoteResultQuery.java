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

public class WxVoteResultQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected Long voteId;
	protected List<Long> voteIds;
	protected String ip;
	protected Date voteDateGreaterThanOrEqual;
	protected Date voteDateLessThanOrEqual;

	public WxVoteResultQuery() {

	}

	public String getActorId() {
		return actorId;
	}

	public List<String> getActorIds() {
		return actorIds;
	}

	public String getIp() {
		return ip;
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

			if ("result".equals(sortColumn)) {
				orderBy = "E.RESULT_" + a_x;
			}

			if ("ip".equals(sortColumn)) {
				orderBy = "E.IP_" + a_x;
			}

			if ("voteDate".equals(sortColumn)) {
				orderBy = "E.VOTEDATE_" + a_x;
			}

			if ("actorId".equals(sortColumn)) {
				orderBy = "E.ACTORID_" + a_x;
			}

		}
		return orderBy;
	}

	public Date getVoteDateGreaterThanOrEqual() {
		return voteDateGreaterThanOrEqual;
	}

	public Date getVoteDateLessThanOrEqual() {
		return voteDateLessThanOrEqual;
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
		addColumn("result", "RESULT_");
		addColumn("ip", "IP_");
		addColumn("voteDate", "VOTEDATE_");
		addColumn("actorId", "ACTORID_");
	}

	public WxVoteResultQuery ip(String ip) {
		if (ip == null) {
			throw new RuntimeException("ip is null");
		}
		this.ip = ip;
		return this;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setActorIds(List<String> actorIds) {
		this.actorIds = actorIds;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setVoteDateGreaterThanOrEqual(Date voteDateGreaterThanOrEqual) {
		this.voteDateGreaterThanOrEqual = voteDateGreaterThanOrEqual;
	}

	public void setVoteDateLessThanOrEqual(Date voteDateLessThanOrEqual) {
		this.voteDateLessThanOrEqual = voteDateLessThanOrEqual;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public void setVoteIds(List<Long> voteIds) {
		this.voteIds = voteIds;
	}

	public WxVoteResultQuery voteDateGreaterThanOrEqual(
			Date voteDateGreaterThanOrEqual) {
		if (voteDateGreaterThanOrEqual == null) {
			throw new RuntimeException("voteDate is null");
		}
		this.voteDateGreaterThanOrEqual = voteDateGreaterThanOrEqual;
		return this;
	}

	public WxVoteResultQuery voteDateLessThanOrEqual(
			Date voteDateLessThanOrEqual) {
		if (voteDateLessThanOrEqual == null) {
			throw new RuntimeException("voteDate is null");
		}
		this.voteDateLessThanOrEqual = voteDateLessThanOrEqual;
		return this;
	}

	public WxVoteResultQuery voteId(Long voteId) {
		if (voteId == null) {
			throw new RuntimeException("voteId is null");
		}
		this.voteId = voteId;
		return this;
	}

	public WxVoteResultQuery voteIds(List<Long> voteIds) {
		if (voteIds == null) {
			throw new RuntimeException("voteIds is empty ");
		}
		this.voteIds = voteIds;
		return this;
	}

}