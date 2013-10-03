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

package com.glaf.wechat.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.core.util.UUID32;
import com.glaf.wechat.domain.WxCover;
import com.glaf.wechat.mapper.WxCoverMapper;
import com.glaf.wechat.query.WxCoverQuery;
import com.glaf.wechat.service.*;

@Service("wxCoverService")
@Transactional(readOnly = true)
public class WxCoverServiceImpl implements WxCoverService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxCoverMapper wxCoverMapper;

	public WxCoverServiceImpl() {

	}

	public int count(WxCoverQuery query) {
		query.ensureInitialized();
		return wxCoverMapper.getWxCoverCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxCoverMapper.deleteWxCoverById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxCoverMapper.deleteWxCoverById(id);
			}
		}
	}

	public WxCover getWxCover(Long id) {
		if (id == null) {
			return null;
		}
		WxCover wxCover = wxCoverMapper.getWxCoverById(id);
		return wxCover;
	}

	/**
	 * 获取用户封面信息
	 * 
	 * @param createBy
	 * @return
	 */
	public WxCover getWxCoverByUser(String createBy) {
		WxCoverQuery query = new WxCoverQuery();
		query.createBy(createBy);
		List<WxCover> list = wxCoverMapper.getWxCovers(query);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public int getWxCoverCountByQueryCriteria(WxCoverQuery query) {
		return wxCoverMapper.getWxCoverCount(query);
	}

	public List<WxCover> getWxCoversByQueryCriteria(int start, int pageSize,
			WxCoverQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxCover> rows = sqlSessionTemplate.selectList("getWxCovers",
				query, rowBounds);
		return rows;
	}

	public List<WxCover> list(WxCoverQuery query) {
		List<WxCover> list = wxCoverMapper.getWxCovers(query);
		return list;
	}

	@Transactional
	public void save(WxCover wxCover) {
		if (wxCover.getId() == 0) {
			wxCover.setId(idGenerator.nextId());
			wxCover.setCreateDate(new Date());
			wxCover.setUuid(UUID32.getUUID());
			wxCoverMapper.insertWxCover(wxCover);
		} else {
			wxCover.setLastUpdateDate(new Date());
			wxCoverMapper.updateWxCover(wxCover);
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
	public void setWxCoverMapper(WxCoverMapper wxCoverMapper) {
		this.wxCoverMapper = wxCoverMapper;
	}

}
