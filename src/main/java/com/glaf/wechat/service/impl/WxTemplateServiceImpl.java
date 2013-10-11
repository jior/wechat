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
import com.glaf.wechat.domain.WxTemplate;
import com.glaf.wechat.mapper.WxTemplateMapper;
import com.glaf.wechat.query.WxTemplateQuery;
import com.glaf.wechat.service.*;

@Service("wxTemplateService")
@Transactional(readOnly = true)
public class WxTemplateServiceImpl implements WxTemplateService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxTemplateMapper wxTemplateMapper;

	public WxTemplateServiceImpl() {

	}

	public int count(WxTemplateQuery query) {
		query.ensureInitialized();
		return wxTemplateMapper.getWxTemplateCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxTemplateMapper.deleteWxTemplateById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxTemplateMapper.deleteWxTemplateById(id);
			}
		}
	}

	public WxTemplate getWxTemplate(Long id) {
		if (id == null) {
			return null;
		}
		WxTemplate wxTemplate = wxTemplateMapper.getWxTemplateById(id);
		return wxTemplate;
	}

	public WxTemplate getWxTemplateByUUID(String uuid) {
		return wxTemplateMapper.getWxTemplateByUUID(uuid);
	}

	public int getWxTemplateCountByQueryCriteria(WxTemplateQuery query) {
		return wxTemplateMapper.getWxTemplateCount(query);
	}

	/**
	 * 获取某个栏目指定类型的模板
	 * 
	 * @param createBy
	 * @param type
	 * @param categoryId
	 * @return
	 */
	public List<WxTemplate> getTemplates(String createBy, String type,
			Long categoryId) {
		WxTemplateQuery query = new WxTemplateQuery();
		query.categoryId(categoryId);
		query.createBy(createBy);
		query.type(type);
		return this.list(query);
	}

	public List<WxTemplate> getWxTemplatesByQueryCriteria(int start,
			int pageSize, WxTemplateQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxTemplate> rows = sqlSessionTemplate.selectList("getWxTemplates",
				query, rowBounds);
		return rows;
	}

	public List<WxTemplate> list(WxTemplateQuery query) {
		query.ensureInitialized();
		List<WxTemplate> list = wxTemplateMapper.getWxTemplates(query);
		return list;
	}

	@Transactional
	public void save(WxTemplate wxTemplate) {
		if (wxTemplate.getId() == 0) {
			wxTemplate.setId(idGenerator.nextId());
			wxTemplate.setCreateDate(new Date());
			wxTemplate.setUuid(UUID32.getUUID());
			wxTemplateMapper.insertWxTemplate(wxTemplate);
		} else {
			wxTemplate.setLastUpdateDate(new Date());
			wxTemplateMapper.updateWxTemplate(wxTemplate);
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
	public void setWxTemplateMapper(WxTemplateMapper wxTemplateMapper) {
		this.wxTemplateMapper = wxTemplateMapper;
	}

}
