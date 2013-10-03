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
import com.glaf.wechat.domain.WxMember;
import com.glaf.wechat.mapper.WxMemberMapper;
import com.glaf.wechat.query.WxMemberQuery;
import com.glaf.wechat.service.*;

@Service("wxMemberService")
@Transactional(readOnly = true)
public class WxMemberServiceImpl implements WxMemberService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxMemberMapper wxMemberMapper;

	public WxMemberServiceImpl() {

	}

	public int count(WxMemberQuery query) {
		query.ensureInitialized();
		return wxMemberMapper.getWxMemberCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxMemberMapper.deleteWxMemberById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxMemberMapper.deleteWxMemberById(id);
			}
		}
	}

	public WxMember getWxMember(Long id) {
		if (id == null) {
			return null;
		}
		WxMember wxMember = wxMemberMapper.getWxMemberById(id);
		return wxMember;
	}

	public WxMember getWxMemberByUUID(String uuid){
		return wxMemberMapper.getWxMemberByUUID(uuid);
	}
	
	
	public int getWxMemberCountByQueryCriteria(WxMemberQuery query) {
		return wxMemberMapper.getWxMemberCount(query);
	}

	public List<WxMember> getWxMembersByQueryCriteria(int start, int pageSize,
			WxMemberQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxMember> rows = sqlSessionTemplate.selectList("getWxMembers",
				query, rowBounds);
		return rows;
	}

	public List<WxMember> list(WxMemberQuery query) {
		query.ensureInitialized();
		List<WxMember> list = wxMemberMapper.getWxMembers(query);
		return list;
	}

	@Transactional
	public void save(WxMember wxMember) {
		if (wxMember.getId() == 0) {
			wxMember.setId(idGenerator.nextId());
			wxMember.setCreateDate(new Date());
			wxMember.setUuid(UUID32.getUUID());
			wxMemberMapper.insertWxMember(wxMember);
		} else {
			wxMember.setLastUpdateDate(new Date());
			wxMemberMapper.updateWxMember(wxMember);
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
	public void setWxMemberMapper(WxMemberMapper wxMemberMapper) {
		this.wxMemberMapper = wxMemberMapper;
	}

}
