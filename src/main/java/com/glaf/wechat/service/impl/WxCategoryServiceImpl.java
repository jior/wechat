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
import java.util.Map;

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
import com.glaf.wechat.domain.WxCategory;
import com.glaf.wechat.mapper.WxCategoryMapper;
import com.glaf.wechat.query.WxCategoryQuery;
import com.glaf.wechat.service.*;

@Service("wxCategoryService")
@Transactional(readOnly = true)
public class WxCategoryServiceImpl implements WxCategoryService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxCategoryMapper wxCategoryMapper;

	public WxCategoryServiceImpl() {

	}

	public int count(WxCategoryQuery query) {
		return wxCategoryMapper.getWxCategoryCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxCategoryMapper.deleteWxCategoryById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxCategoryMapper.deleteWxCategoryById(id);
			}
		}
	}

	/**
	 * 获取某个分类的直接子节点列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<WxCategory> getCategoryList(long parentId) {
		WxCategoryQuery query = new WxCategoryQuery();
		query.parentId(parentId);
		List<WxCategory> list = wxCategoryMapper.getWxCategories(query);
		return list;
	}

	/**
	 * 获取某个用户创建的全部分类列表
	 * 
	 * @param createBy
	 * @return
	 */
	public List<WxCategory> getCategoryList(String createBy) {
		WxCategoryQuery query = new WxCategoryQuery();
		query.createBy(createBy);
		List<WxCategory> list = wxCategoryMapper.getWxCategories(query);
		return list;
	}

	/**
	 * 获取某个用户创建的某个分类的子分类列表
	 * 
	 * @param createBy
	 * @param parentId
	 * @return
	 */
	public List<WxCategory> getCategoryList(String createBy, long parentId) {
		WxCategoryQuery query = new WxCategoryQuery();
		query.createBy(createBy);
		query.parentId(parentId);
		List<WxCategory> list = wxCategoryMapper.getWxCategories(query);
		return list;
	}
	
	
	/**
	 * 获取某个用户创建的某类型的子分类列表
	 * 
	 * @param createBy
	 * @param type
	 * @return
	 */
	public List<WxCategory> getCategoryList(String createBy, String type){
		WxCategoryQuery query = new WxCategoryQuery();
		query.createBy(createBy);
		query.type(type);
		List<WxCategory> list = wxCategoryMapper.getWxCategories(query);
		return list;
	}

	protected String getTreeId(Map<Long, WxCategory> dataMap, WxCategory tree) {
		long parentId = tree.getParentId();
		long id = tree.getId();
		WxCategory parent = dataMap.get(parentId);
		if (parent != null && parent.getId() != 0) {
			if (StringUtils.isEmpty(parent.getTreeId())) {
				return getTreeId(dataMap, parent) + id + "|";
			}
			if (!parent.getTreeId().endsWith("|")) {
				parent.setTreeId(parent.getTreeId() + "|");
			}
			return parent.getTreeId() + id + "|";
		}
		return tree.getTreeId();
	}

	public WxCategory getWxCategory(Long id) {
		if (id == null) {
			return null;
		}
		WxCategory wxCategory = wxCategoryMapper.getWxCategoryById(id);
		return wxCategory;
	}

	public int getWxCategoryCountByQueryCriteria(WxCategoryQuery query) {
		return wxCategoryMapper.getWxCategoryCount(query);
	}

	public List<WxCategory> getWxCategorysByQueryCriteria(int start,
			int pageSize, WxCategoryQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxCategory> rows = sqlSessionTemplate.selectList("getWxCategories",
				query, rowBounds);
		return rows;
	}

	public List<WxCategory> list(WxCategoryQuery query) {
		List<WxCategory> list = wxCategoryMapper.getWxCategories(query);
		return list;
	}

	@Transactional
	public void save(WxCategory wxCategory) {
		if (wxCategory.getId() == 0) {
			wxCategory.setId(idGenerator.nextId());
			wxCategory.setCreateDate(new Date());
			wxCategory.setUuid(UUID32.getUUID());
			if (wxCategory.getParentId() > 0) {
				WxCategory parent = this
						.getWxCategory(wxCategory.getParentId());
				if (parent != null && parent.getTreeId() != null) {
					wxCategory.setTreeId(parent.getTreeId()
							+ wxCategory.getId() + "|");
				}
			} else {
				wxCategory.setTreeId(wxCategory.getId() + "|");
			}

			wxCategoryMapper.insertWxCategory(wxCategory);
		} else {
			wxCategory.setLastUpdateDate(new Date());
			wxCategoryMapper.updateWxCategory(wxCategory);
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
	public void setWxCategoryMapper(WxCategoryMapper wxCategoryMapper) {
		this.wxCategoryMapper = wxCategoryMapper;
	}

}
