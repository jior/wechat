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
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.core.util.UUID32;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.mapper.WxContentMapper;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.*;

@Service("wxContentService")
@Transactional(readOnly = true)
public class WxContentServiceImpl implements WxContentService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxContentMapper wxContentMapper;

	protected WxKeywordsService wxKeywordsService;

	public WxContentServiceImpl() {

	}

	public int count(WxContentQuery query) {
		query.ensureInitialized();
		return wxContentMapper.getWxContentCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxContentMapper.deleteWxContentById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxContentMapper.deleteWxContentById(id);
			}
		}
	}

	public WxContent getWxContent(Long id) {
		if (id == null) {
			return null;
		}
		WxContent wxContent = wxContentMapper.getWxContentById(id);
		return wxContent;
	}

	public int getWxContentCountByQueryCriteria(WxContentQuery query) {
		return wxContentMapper.getWxContentCount(query);
	}

	public List<WxContent> getWxContentsByQueryCriteria(int start,
			int pageSize, WxContentQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxContent> rows = sqlSessionTemplate.selectList("getWxContents",
				query, rowBounds);
		return rows;
	}

	public List<WxContent> list(WxContentQuery query) {
		query.ensureInitialized();
		List<WxContent> list = wxContentMapper.getWxContents(query);
		return list;
	}

	@Transactional
	public void save(WxContent wxContent) {
		String keywords = wxContent.getKeywords();
		if (StringUtils.isNotEmpty(keywords)) {
			StringTokenizer token = new StringTokenizer(keywords);
			wxContent.setKeywordsCount(token.countTokens());
		}
		if (wxContent.getId() == 0) {
			wxContent.setId(idGenerator.nextId());
			wxContent.setCreateDate(new Date());
			wxContent.setUuid(UUID32.getUUID());
			wxContentMapper.insertWxContent(wxContent);
		} else {
			wxContent.setLastUpdateDate(new Date());
			wxContentMapper.updateWxContent(wxContent);
		}
		wxKeywordsService.saveAll(wxContent.getCategoryId(), wxContent);
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
	public void setWxContentMapper(WxContentMapper wxContentMapper) {
		this.wxContentMapper = wxContentMapper;
	}

	@Resource
	public void setWxKeywordsService(WxKeywordsService wxKeywordsService) {
		this.wxKeywordsService = wxKeywordsService;
	}

}
