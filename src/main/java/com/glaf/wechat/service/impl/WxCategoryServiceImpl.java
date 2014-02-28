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

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.base.ColumnModel;
import com.glaf.core.base.TableModel;
import com.glaf.core.cache.CacheFactory;
import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.core.service.ITableDataService;
import com.glaf.core.util.StringTools;
import com.glaf.core.util.UUID32;
import com.glaf.wechat.domain.WxCategory;
import com.glaf.wechat.mapper.WxCategoryMapper;
import com.glaf.wechat.mapper.WxContentMapper;
import com.glaf.wechat.query.WxCategoryQuery;
import com.glaf.wechat.service.WxCategoryService;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.util.WxCategoryJsonFactory;

@Service("wxCategoryService")
@Transactional(readOnly = true)
public class WxCategoryServiceImpl implements WxCategoryService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxCategoryMapper wxCategoryMapper;

	protected WxContentMapper wxContentMapper;

	protected ITableDataService tableDataService;

	protected WxFileService wxFileService;

	public WxCategoryServiceImpl() {

	}

	public int count(WxCategoryQuery query) {
		return wxCategoryMapper.getWxCategoryCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxFileService.deleteByCategoryId(id);
			wxContentMapper.deleteWxContentByCategoryId(id);
			wxCategoryMapper.deleteWxCategoryById(id);
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
	public List<WxCategory> getCategoryList(Long accountId) {
		WxCategoryQuery query = new WxCategoryQuery();
		query.accountId(accountId);
		query.setOrderBy(" E.TREEID_ asc");
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
	public List<WxCategory> getCategoryList(Long accountId, long parentId) {
		WxCategoryQuery query = new WxCategoryQuery();
		query.accountId(accountId);
		query.parentId(parentId);
		query.setOrderBy(" E.TREEID_ asc");
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
	public List<WxCategory> getCategoryList(Long accountId, String type) {
		WxCategoryQuery query = new WxCategoryQuery();
		query.accountId(accountId);
		query.type(type);
		query.setOrderBy(" E.TREEID_ asc");
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
		String cacheKey = "wx_cat_" + id;
		if (CacheFactory.getString(cacheKey) != null) {
			String text = CacheFactory.getString(cacheKey);
			JSONObject json = JSON.parseObject(text);
			WxCategory wxCategory = WxCategoryJsonFactory.jsonToObject(json);
			return wxCategory;
		}
		WxCategory wxCategory = wxCategoryMapper.getWxCategoryById(id);
		if (wxCategory != null) {
			JSONObject json = WxCategoryJsonFactory.toJsonObject(wxCategory);
			CacheFactory.put(cacheKey, json.toJSONString());
		}
		return wxCategory;
	}

	public WxCategory getWxCategoryByUUID(String uuid) {
		if (uuid == null) {
			return null;
		}
		String cacheKey = "wx_cat_" + uuid;
		if (CacheFactory.getString(cacheKey) != null) {
			String text = CacheFactory.getString(cacheKey);
			JSONObject json = JSON.parseObject(text);
			WxCategory wxCategory = WxCategoryJsonFactory.jsonToObject(json);
			return wxCategory;
		}
		WxCategory wxCategory = wxCategoryMapper.getWxCategoryByUUID(uuid);
		if (wxCategory != null) {
			JSONObject json = WxCategoryJsonFactory.toJsonObject(wxCategory);
			CacheFactory.put(cacheKey, json.toJSONString());
		}
		return wxCategory;
	}

	public int getWxCategoryCountByQueryCriteria(WxCategoryQuery query) {
		return wxCategoryMapper.getWxCategoryCount(query);
	}

	public List<WxCategory> getWxCategorysByQueryCriteria(int start,
			int pageSize, WxCategoryQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxCategory> rows = sqlSessionTemplate.selectList(
				"getWxCategories", query, rowBounds);
		return rows;
	}

	public List<WxCategory> list(WxCategoryQuery query) {
		List<WxCategory> list = wxCategoryMapper.getWxCategories(query);
		return list;
	}

	public void loadChildren(List<WxCategory> list, long parentId) {
		WxCategoryQuery query = new WxCategoryQuery();
		query.setParentId(Long.valueOf(parentId));
		List<WxCategory> nodes = this.list(query);
		if (nodes != null && !nodes.isEmpty()) {
			for (WxCategory node : nodes) {
				list.add(node);
				this.loadChildren(list, node.getId());
			}
		}
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
			this.update(wxCategory);
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
	public void setTableDataService(ITableDataService tableDataService) {
		this.tableDataService = tableDataService;
	}

	@javax.annotation.Resource
	public void setWxCategoryMapper(WxCategoryMapper wxCategoryMapper) {
		this.wxCategoryMapper = wxCategoryMapper;
	}

	@javax.annotation.Resource
	public void setWxContentMapper(WxContentMapper wxContentMapper) {
		this.wxContentMapper = wxContentMapper;
	}
	
	@javax.annotation.Resource
	public void setWxFileService(WxFileService wxFileService) {
		this.wxFileService = wxFileService;
	}

	@Transactional
	public boolean update(WxCategory bean) {
		WxCategory model = this.getWxCategory(bean.getId());
		/**
		 * 如果节点移动了位置，即移动到别的节点下面去了
		 */
		if (model.getParentId() != bean.getParentId()) {
			List<WxCategory> list = new java.util.concurrent.CopyOnWriteArrayList<WxCategory>();
			this.loadChildren(list, bean.getId());
			if (!list.isEmpty()) {
				for (WxCategory node : list) {
					/**
					 * 不能移动到ta自己的子节点下面去
					 */
					if (bean.getParentId() == node.getId()) {
						throw new RuntimeException(
								"Can't change node into children");
					}
				}
				/**
				 * 修正所有子节点的treeId
				 */
				WxCategory oldParent = this.getWxCategory(model.getParentId());
				WxCategory newParent = this.getWxCategory(bean.getParentId());
				if (oldParent != null && newParent != null
						&& StringUtils.isNotEmpty(oldParent.getTreeId())
						&& StringUtils.isNotEmpty(newParent.getTreeId())) {
					TableModel tableModel = new TableModel();
					tableModel.setTableName("WX_CATEGORY");
					ColumnModel idColumn = new ColumnModel();
					idColumn.setColumnName("ID_");
					idColumn.setJavaType("Long");
					tableModel.setIdColumn(idColumn);

					ColumnModel treeColumn = new ColumnModel();
					treeColumn.setColumnName("TREEID_");
					treeColumn.setJavaType("String");
					tableModel.addColumn(treeColumn);

					for (WxCategory node : list) {
						String treeId = node.getTreeId();
						if (StringUtils.isNotEmpty(treeId)) {
							treeId = StringTools.replace(treeId,
									oldParent.getTreeId(),
									newParent.getTreeId());
							idColumn.setValue(node.getId());
							treeColumn.setValue(treeId);
							tableDataService.updateTableData(tableModel);
						}
					}
				}
			}
		}

		if (bean.getParentId() != 0) {
			WxCategory parent = this.getWxCategory(bean.getParentId());
			if (parent != null) {
				if (StringUtils.isNotEmpty(parent.getTreeId())) {
					bean.setTreeId(parent.getTreeId() + bean.getId() + "|");
				}
			}
		}

		wxCategoryMapper.updateWxCategory(bean);

		String cacheKey = "wx_cat_" + bean.getId();
		CacheFactory.remove(cacheKey);
		cacheKey = "wx_cat_" + bean.getUuid();
		CacheFactory.remove(cacheKey);

		return true;
	}

}
