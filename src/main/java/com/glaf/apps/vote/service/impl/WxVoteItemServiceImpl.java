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
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.id.*;
import com.glaf.core.dao.*;
import com.glaf.apps.vote.mapper.*;
import com.glaf.apps.vote.domain.*;
import com.glaf.apps.vote.query.*;
import com.glaf.apps.vote.service.WxVoteItemService;

@Service("wxVoteItemService")
@Transactional(readOnly = true)
public class WxVoteItemServiceImpl implements WxVoteItemService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxVoteItemMapper wxVoteItemMapper;

	public WxVoteItemServiceImpl() {

	}

	public int count(WxVoteItemQuery query) {
		query.ensureInitialized();
		return wxVoteItemMapper.getWxVoteItemCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxVoteItemMapper.deleteWxVoteItemById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxVoteItemMapper.deleteWxVoteItemById(id);
			}
		}
	}

	public WxVoteItem getWxVoteItem(Long id) {
		if (id == null) {
			return null;
		}
		WxVoteItem wxVoteItem = wxVoteItemMapper.getWxVoteItemById(id);
		return wxVoteItem;
	}

	public int getWxVoteItemCountByQueryCriteria(WxVoteItemQuery query) {
		return wxVoteItemMapper.getWxVoteItemCount(query);
	}

	public List<WxVoteItem> getWxVoteItemsByQueryCriteria(int start,
			int pageSize, WxVoteItemQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxVoteItem> rows = sqlSessionTemplate.selectList("getWxVoteItems",
				query, rowBounds);
		return rows;
	}

	public List<WxVoteItem> list(WxVoteItemQuery query) {
		query.ensureInitialized();
		List<WxVoteItem> list = wxVoteItemMapper.getWxVoteItems(query);
		return list;
	}

	@Transactional
	public void save(WxVoteItem wxVoteItem) {
		if (wxVoteItem.getId() == null) {
			wxVoteItem.setId(idGenerator.nextId("wx_vote_item"));
			// wxVoteItem.setCreateDate(new Date());
			// wxVoteItem.setDeleteFlag(0);
			wxVoteItemMapper.insertWxVoteItem(wxVoteItem);
		} else {
			wxVoteItemMapper.updateWxVoteItem(wxVoteItem);
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

}
