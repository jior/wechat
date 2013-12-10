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
import com.glaf.wechat.service.WxFollowerService;

@Service("wxFollowerService")
@Transactional(readOnly = true)
public class WxFollowerServiceImpl implements WxFollowerService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxFollowerMapper wxFollowerMapper;

	public WxFollowerServiceImpl() {

	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxFollowerMapper.deleteWxFollowerById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxFollowerMapper.deleteWxFollowerById(id);
			}
		}
	}

	public int count(WxFollowerQuery query) {
		return wxFollowerMapper.getWxFollowerCount(query);
	}

	public List<WxFollower> list(WxFollowerQuery query) {
		List<WxFollower> list = wxFollowerMapper.getWxFollowers(query);
		return list;
	}

	public int getWxFollowerCountByQueryCriteria(WxFollowerQuery query) {
		return wxFollowerMapper.getWxFollowerCount(query);
	}

	public List<WxFollower> getWxFollowersByQueryCriteria(int start,
			int pageSize, WxFollowerQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxFollower> rows = sqlSessionTemplate.selectList("getWxFollowers",
				query, rowBounds);
		return rows;
	}

	public WxFollower getWxFollower(Long id) {
		if (id == null) {
			return null;
		}
		WxFollower wxFollower = wxFollowerMapper.getWxFollowerById(id);
		return wxFollower;
	}

	@Transactional
	public void save(WxFollower wxFollower) {
		WxFollower bean = wxFollowerMapper.getWxFollowerByOpenId(wxFollower
				.getOpenId());
		if (bean != null) {
			wxFollower.setMail(bean.getMail());
			wxFollower.setMobile(bean.getMobile());
			wxFollower.setRemark(bean.getRemark());
			wxFollower.setTelephone(bean.getTelephone());
			wxFollowerMapper.updateWxFollower(wxFollower);
		} else {
			if (wxFollower.getId() == null) {
				wxFollower.setId(idGenerator.nextId());
				wxFollower.setCreateDate(new Date());
			}
			wxFollowerMapper.insertWxFollower(wxFollower);
		}
	}

	@Transactional
	public void insertAll(List<WxFollower> followers) {
		if (followers != null && !followers.isEmpty()) {
			for (WxFollower follower : followers) {
				this.save(follower);
			}
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
	public void setWxFollowerMapper(WxFollowerMapper wxFollowerMapper) {
		this.wxFollowerMapper = wxFollowerMapper;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
