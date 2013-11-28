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

package com.glaf.apps.vote.service.impl;

import java.util.*;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.id.*;
import com.glaf.core.dao.*;
import com.glaf.apps.vote.mapper.*;
import com.glaf.apps.vote.domain.*;
import com.glaf.apps.vote.query.*;
import com.glaf.apps.vote.service.WxVoteResultService;

@Service("wxVoteResultService")
@Transactional(readOnly = true)
public class WxVoteResultServiceImpl implements WxVoteResultService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxVoteResultMapper wxVoteResultMapper;

	public WxVoteResultServiceImpl() {

	}

	public int count(WxVoteResultQuery query) {
		query.ensureInitialized();
		return wxVoteResultMapper.getWxVoteResultCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxVoteResultMapper.deleteWxVoteResultById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxVoteResultMapper.deleteWxVoteResultById(id);
			}
		}
	}

	public WxVoteResult getWxVoteResult(Long id) {
		if (id == null) {
			return null;
		}
		WxVoteResult wxVoteResult = wxVoteResultMapper.getWxVoteResultById(id);
		return wxVoteResult;
	}

	public int getWxVoteResultCountByQueryCriteria(WxVoteResultQuery query) {
		return wxVoteResultMapper.getWxVoteResultCount(query);
	}

	public List<WxVoteResult> getWxVoteResultsByQueryCriteria(int start,
			int pageSize, WxVoteResultQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxVoteResult> rows = sqlSessionTemplate.selectList(
				"getWxVoteResults", query, rowBounds);
		return rows;
	}

	public List<WxVoteResult> list(WxVoteResultQuery query) {
		query.ensureInitialized();
		List<WxVoteResult> list = wxVoteResultMapper.getWxVoteResults(query);
		return list;
	}

	@Transactional
	public void save(WxVoteResult wxVoteResult) {
		if (wxVoteResult.getId() == null) {
			wxVoteResult.setId(idGenerator.nextId());
			wxVoteResult.setVoteDate(new Date());
			wxVoteResultMapper.insertWxVoteResult(wxVoteResult);
		} else {
			wxVoteResultMapper.updateWxVoteResult(wxVoteResult);
		}
	}

	@javax.annotation.Resource
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@javax.annotation.Resource
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@javax.annotation.Resource
	public void setWxVoteResultMapper(WxVoteResultMapper wxVoteResultMapper) {
		this.wxVoteResultMapper = wxVoteResultMapper;
	}

}
