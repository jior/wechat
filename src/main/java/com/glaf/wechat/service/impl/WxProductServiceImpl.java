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

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.wechat.domain.WxProduct;
import com.glaf.wechat.mapper.WxProductMapper;
import com.glaf.wechat.query.WxProductQuery;
import com.glaf.wechat.service.WxProductService;

@Service("wxProductService")
@Transactional(readOnly = true)
public class WxProductServiceImpl implements WxProductService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxProductMapper wxProductMapper;

	public WxProductServiceImpl() {

	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxProductMapper.deleteWxProductById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxProductMapper.deleteWxProductById(id);
			}
		}
	}

	public int count(WxProductQuery query) {
		query.ensureInitialized();
		return wxProductMapper.getWxProductCount(query);
	}

	public List<WxProduct> list(WxProductQuery query) {
		query.ensureInitialized();
		List<WxProduct> list = wxProductMapper.getWxProducts(query);
		return list;
	}

	public int getWxProductCountByQueryCriteria(WxProductQuery query) {
		return wxProductMapper.getWxProductCount(query);
	}

	public List<WxProduct> getWxProductsByQueryCriteria(int start,
			int pageSize, WxProductQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxProduct> rows = sqlSessionTemplate.selectList("getWxProducts",
				query, rowBounds);
		return rows;
	}

	public WxProduct getWxProduct(Long id) {
		if (id == null) {
			return null;
		}
		WxProduct wxProduct = wxProductMapper.getWxProductById(id);
		return wxProduct;
	}

	@Transactional
	public void save(WxProduct wxProduct) {
		if (wxProduct.getId() == null) {
			wxProduct.setId(idGenerator.nextId("WX_WXPRODUCT"));
			wxProduct.setCreateDate(new Date());
			wxProductMapper.insertWxProduct(wxProduct);
		} else {
			wxProductMapper.updateWxProduct(wxProduct);
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
	public void setWxProductMapper(WxProductMapper wxProductMapper) {
		this.wxProductMapper = wxProductMapper;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
