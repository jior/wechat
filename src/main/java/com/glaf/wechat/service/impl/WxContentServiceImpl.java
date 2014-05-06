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

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.cache.CacheFactory;
import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.core.util.StringTools;
import com.glaf.core.util.UUID32;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.mapper.WxContentMapper;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxKeywordsService;

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
	
	/**
	 * 删除某个栏目的内容
	 * @param categoryId
	 */
	@Transactional
	public void deleteWxContentByCategoryId(Long categoryId){
		wxContentMapper.deleteWxContentByCategoryId(categoryId);
	}

	public WxContent getWxContent(Long id) {
		if (id == null) {
			return null;
		}
		WxContent wxContent = wxContentMapper.getWxContentById(id);
		if (wxContent != null
				&& StringUtils.isNotEmpty(wxContent.getRelationIds())) {
			List<Long> relationIds = StringTools.splitToLong(wxContent
					.getRelationIds());
			logger.debug("relationIds:" + relationIds);
			List<WxContent> relations = wxContentMapper
					.getWxContentsByIds(relationIds);
			wxContent.setRelations(relations);
		}
		if (wxContent != null
				&& StringUtils.isNotEmpty(wxContent.getRecommendationIds())) {
			List<Long> recommendationIds = StringTools.splitToLong(wxContent
					.getRecommendationIds());
			List<WxContent> recommendations = wxContentMapper
					.getWxContentsByIds(recommendationIds);
			wxContent.setRecommendations(recommendations);
		}
		return wxContent;
	}

	public WxContent getWxContentByUUID(String uuid) {
		return wxContentMapper.getWxContentByUUID(uuid);
	}

	public WxContent getWxContentByUUIDWithRefs(String uuid) {
		if (uuid == null) {
			return null;
		}
		WxContent wxContent = wxContentMapper.getWxContentByUUID(uuid);
		if (wxContent != null
				&& StringUtils.isNotEmpty(wxContent.getRelationIds())) {
			List<Long> relationIds = StringTools.splitToLong(wxContent
					.getRelationIds());
			logger.debug("relationIds:" + relationIds);
			List<WxContent> relations = wxContentMapper
					.getWxContentsByIds(relationIds);
			wxContent.setRelations(relations);
		}
		if (wxContent != null
				&& StringUtils.isNotEmpty(wxContent.getRecommendationIds())) {
			List<Long> recommendationIds = StringTools.splitToLong(wxContent
					.getRecommendationIds());
			List<WxContent> recommendations = wxContentMapper
					.getWxContentsByIds(recommendationIds);
			wxContent.setRecommendations(recommendations);
		}
		return wxContent;
	}

	public int getWxContentCountByQueryCriteria(WxContentQuery query) {
		return wxContentMapper.getWxContentCount(query);
	}

	/**
	 * 获取某个用户某个栏目指定类型的内容
	 * 
	 * @param accountId
	 * @param categoryId
	 * @param type
	 * @return
	 */
	public List<WxContent> getWxContents(Long accountId, Long categoryId,
			String type) {
		WxContentQuery query = new WxContentQuery();
		query.categoryId(categoryId);
		query.accountId(accountId);
		query.type(type);
		return this.list(query);
	}

	public List<WxContent> getWxContentsByQueryCriteria(int start,
			int pageSize, WxContentQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxContent> rows = sqlSessionTemplate.selectList("getWxContents",
				query, rowBounds);
		return rows;
	}

	public WxContent getWxContentWithRefs(Long id) {
		if (id == null) {
			return null;
		}
		WxContent wxContent = wxContentMapper.getWxContentById(id);
		if (wxContent != null
				&& StringUtils.isNotEmpty(wxContent.getRelationIds())) {
			List<Long> relationIds = StringTools.splitToLong(wxContent
					.getRelationIds());
			logger.debug("relationIds:" + relationIds);
			List<WxContent> relations = wxContentMapper
					.getWxContentsByIds(relationIds);
			wxContent.setRelations(relations);
		}
		if (wxContent != null
				&& StringUtils.isNotEmpty(wxContent.getRecommendationIds())) {
			List<Long> recommendationIds = StringTools.splitToLong(wxContent
					.getRecommendationIds());
			List<WxContent> recommendations = wxContentMapper
					.getWxContentsByIds(recommendationIds);
			wxContent.setRecommendations(recommendations);
		}
		return wxContent;
	}

	public List<WxContent> list(WxContentQuery query) {
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
			String cacheKey = "website_detail_" + wxContent.getId();
			CacheFactory.remove(cacheKey);
			cacheKey = "website_detail_" + wxContent.getUuid();
			CacheFactory.remove(cacheKey);
			cacheKey = "website_list_" + wxContent.getCategoryId();
			CacheFactory.remove(cacheKey);
		}
		wxKeywordsService.saveAll(wxContent.getCategoryId(), wxContent);
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
	public void setWxContentMapper(WxContentMapper wxContentMapper) {
		this.wxContentMapper = wxContentMapper;
	}

	@javax.annotation.Resource
	public void setWxKeywordsService(WxKeywordsService wxKeywordsService) {
		this.wxKeywordsService = wxKeywordsService;
	}

}
