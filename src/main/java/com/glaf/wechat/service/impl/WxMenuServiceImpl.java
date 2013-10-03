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
import com.glaf.wechat.domain.WxMenu;
import com.glaf.wechat.mapper.WxMenuMapper;
import com.glaf.wechat.query.WxMenuQuery;
import com.glaf.wechat.service.*;

@Service("wxMenuService")
@Transactional(readOnly = true)
public class WxMenuServiceImpl implements WxMenuService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxMenuMapper wxMenuMapper;

	public WxMenuServiceImpl() {

	}

	public int count(WxMenuQuery query) {
		query.ensureInitialized();
		return wxMenuMapper.getWxMenuCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxMenuMapper.deleteWxMenuById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxMenuMapper.deleteWxMenuById(id);
			}
		}
	}

	public WxMenu getWxMenu(Long id) {
		if (id == null) {
			return null;
		}
		WxMenu wxMenu = wxMenuMapper.getWxMenuById(id);
		return wxMenu;
	}

	public int getWxMenuCountByQueryCriteria(WxMenuQuery query) {
		return wxMenuMapper.getWxMenuCount(query);
	}

	public List<WxMenu> getWxMenusByQueryCriteria(int start, int pageSize,
			WxMenuQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxMenu> rows = sqlSessionTemplate.selectList("getWxMenus", query,
				rowBounds);
		return rows;
	}

	public List<WxMenu> list(WxMenuQuery query) {
		query.ensureInitialized();
		List<WxMenu> list = wxMenuMapper.getWxMenus(query);
		return list;
	}

	@Transactional
	public void save(WxMenu wxMenu) {
		if (wxMenu.getId() == 0) {
			wxMenu.setId(idGenerator.nextId());
			wxMenu.setCreateDate(new Date());
			wxMenu.setUuid(UUID32.getUUID());
			wxMenuMapper.insertWxMenu(wxMenu);
		} else {
			wxMenu.setLastUpdateDate(new Date());
			wxMenuMapper.updateWxMenu(wxMenu);
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
	public void setWxMenuMapper(WxMenuMapper wxMenuMapper) {
		this.wxMenuMapper = wxMenuMapper;
	}

}
