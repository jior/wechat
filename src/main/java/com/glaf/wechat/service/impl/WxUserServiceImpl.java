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
import com.glaf.wechat.mapper.*;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.*;

@Service("wxUserService")
@Transactional(readOnly = true)
public class WxUserServiceImpl implements WxUserService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxUserMapper wxUserMapper;

	public WxUserServiceImpl() {

	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxUserMapper.deleteWxUserById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxUserMapper.deleteWxUserById(id);
			}
		}
	}

	public int count(WxUserQuery query) {
		return wxUserMapper.getWxUserCount(query);
	}

	public List<WxUser> list(WxUserQuery query) {
		List<WxUser> list = wxUserMapper.getWxUsers(query);
		return list;
	}

	public int getWxUserCountByQueryCriteria(WxUserQuery query) {
		return wxUserMapper.getWxUserCount(query);
	}

	public List<WxUser> getWxUsersByQueryCriteria(int start, int pageSize,
			WxUserQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxUser> rows = sqlSessionTemplate.selectList("getWxUsers", query,
				rowBounds);
		return rows;
	}

	public WxUser getWxUser(Long id) {
		if (id == null) {
			return null;
		}
		WxUser wxUser = wxUserMapper.getWxUserById(id);
		return wxUser;
	}
	
	public WxUser getWxUserByActorId(String actorId){
		WxUserQuery query = new WxUserQuery();
		query.actorId(actorId);
		List<WxUser> rows =this.list(query);
		if(rows != null && !rows.isEmpty()){
			return rows.get(0);
		}
		return null;
	}

	@Transactional
	public void save(WxUser wxUser) {
		WxUser model = null;
		if (wxUser.getId() > 0) {
			model = this.getWxUser(wxUser.getId());
		}
		if (wxUser.getId() == 0) {
			wxUser.setId(idGenerator.nextId());
		}
		if (model == null) {
			wxUserMapper.insertWxUser(wxUser);
		} else {
			wxUserMapper.updateWxUser(wxUser);
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
	public void setWxUserMapper(WxUserMapper wxUserMapper) {
		this.wxUserMapper = wxUserMapper;
	}

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
