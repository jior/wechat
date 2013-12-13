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

package com.glaf.apps.vote.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import com.alibaba.fastjson.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.glaf.core.base.*;
import com.glaf.apps.vote.util.*;

@Entity
@Table(name = "WX_VOTE_RESULT")
public class WxVoteResult implements Serializable, JSONable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected Long id;

	@Column(name = "VOTEID_")
	protected Long voteId;

	@Column(name = "RESULT_", length = 500)
	protected String result;

	@Column(name = "IP_", length = 200)
	protected String ip;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VOTEDATE_")
	protected Date voteDate;

	@Column(name = "ACTORID_", length = 50)
	protected String actorId;

	public WxVoteResult() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVoteId() {
		return this.voteId;
	}

	public String getResult() {
		return this.result;
	}

	public String getIp() {
		return this.ip;
	}

	public Date getVoteDate() {
		return this.voteDate;
	}

	public String getActorId() {
		return this.actorId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxVoteResult other = (WxVoteResult) obj;
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

	public WxVoteResult jsonToObject(JSONObject jsonObject) {
		return WxVoteResultJsonFactory.jsonToObject(jsonObject);
	}

	public JSONObject toJsonObject() {
		return WxVoteResultJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return WxVoteResultJsonFactory.toObjectNode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
