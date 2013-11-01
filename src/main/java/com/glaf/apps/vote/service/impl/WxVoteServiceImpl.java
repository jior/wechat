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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.id.*;
import com.glaf.core.dao.*;
import com.glaf.apps.vote.mapper.*;
import com.glaf.apps.vote.domain.*;
import com.glaf.apps.vote.query.*;
import com.glaf.apps.vote.service.WxVoteService;

@Service("wxVoteService")
@Transactional(readOnly = true)
public class WxVoteServiceImpl implements WxVoteService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxVoteMapper wxVoteMapper;

	protected WxVoteItemMapper wxVoteItemMapper;

	protected WxVoteResultMapper wxVoteResultMapper;

	public WxVoteServiceImpl() {

	}

	public int count(WxVoteQuery query) {
		query.ensureInitialized();
		return wxVoteMapper.getWxVoteCount(query);
	}

	@Transactional
	public void deleteById(Long voteId) {
		if (voteId != null) {
			wxVoteItemMapper.deleteWxVoteItemByVoteId(voteId);
			wxVoteResultMapper.deleteWxVoteResultByVoteId(voteId);
			wxVoteMapper.deleteWxVoteById(voteId);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				this.deleteById(id);
			}
		}
	}

	public WxVote getWxVote(Long id) {
		if (id == null) {
			return null;
		}
		WxVote wxVote = wxVoteMapper.getWxVoteById(id);
		if (wxVote != null) {
			List<WxVoteItem> items = wxVoteItemMapper
					.getWxVoteItemsByVoteId(wxVote.getId());
			wxVote.setItems(items);
		}
		return wxVote;
	}

	/**
	 * 获取最新的一条投票结果
	 * 
	 * @param voteId
	 *            投票记录
	 * @param ip
	 *            IP地址
	 * @return
	 */
	public WxVoteResult getLatestVoteResult(Long voteId, String ip) {
		WxVoteResultQuery query = new WxVoteResultQuery();
		query.voteId(voteId);
		query.ip(ip);
		return wxVoteResultMapper.getLatestVoteResult(query);
	}

	public int getWxVoteCountByQueryCriteria(WxVoteQuery query) {
		return wxVoteMapper.getWxVoteCount(query);
	}

	public List<WxVote> getWxVotesByQueryCriteria(int start, int pageSize,
			WxVoteQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxVote> rows = sqlSessionTemplate.selectList("getWxVotes", query,
				rowBounds);
		return rows;
	}

	public List<WxVote> list(WxVoteQuery query) {
		query.ensureInitialized();
		List<WxVote> list = wxVoteMapper.getWxVotes(query);
		return list;
	}

	@Transactional
	public void save(WxVote wxVote) {
		if (wxVote.getId() == null) {
			wxVote.setId(idGenerator.nextId());
			wxVote.setCreateDate(new Date());
			wxVoteMapper.insertWxVote(wxVote);
		} else {
			wxVoteItemMapper.deleteWxVoteItemByVoteId(wxVote.getId());
			wxVoteMapper.updateWxVote(wxVote);
		}
		if (wxVote.getItems() != null && !wxVote.getItems().isEmpty()) {
			for (WxVoteItem item : wxVote.getItems()) {
				item.setId(idGenerator.nextId());
				item.setVoteId(wxVote.getId());
				wxVoteItemMapper.insertWxVoteItem(item);
			}
		} else {
			if (StringUtils.isNotEmpty(wxVote.getContent())) {
				int sort = 0;
				StringTokenizer token = new StringTokenizer(wxVote.getContent());
				while (token.hasMoreTokens()) {
					String tmp = token.nextToken();
					if (StringUtils.contains(tmp, "=")) {
						WxVoteItem item = new WxVoteItem();
						item.setId(idGenerator.nextId());
						item.setVoteId(wxVote.getId());
						item.setValue(tmp.substring(0, tmp.indexOf("=")));
						item.setName(tmp.substring(tmp.indexOf("=") + 1,
								tmp.length()));
						item.setSort(sort++);
						wxVoteItemMapper.insertWxVoteItem(item);
					}
				}
			}
		}
	}

	@Resource(name = "myBatisEntityDAO")
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@Resource(name = "myBatisDbIdGenerator")
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Resource
	public void setWxVoteItemMapper(WxVoteItemMapper wxVoteItemMapper) {
		this.wxVoteItemMapper = wxVoteItemMapper;
	}

	@Resource
	public void setWxVoteMapper(WxVoteMapper wxVoteMapper) {
		this.wxVoteMapper = wxVoteMapper;
	}

	@Resource
	public void setWxVoteResultMapper(WxVoteResultMapper wxVoteResultMapper) {
		this.wxVoteResultMapper = wxVoteResultMapper;
	}

}
