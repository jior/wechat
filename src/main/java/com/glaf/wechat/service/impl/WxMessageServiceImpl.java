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

import com.glaf.wechat.domain.WxMessage;
import com.glaf.wechat.mapper.WxMessageMapper;
import com.glaf.wechat.query.WxMessageQuery;
import com.glaf.wechat.service.*;

@Service("wxMessageService")
@Transactional(readOnly = true)
public class WxMessageServiceImpl implements WxMessageService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxMessageMapper wxMessageMapper;

	public WxMessageServiceImpl() {

	}

	public int count(WxMessageQuery query) {
		query.ensureInitialized();
		return wxMessageMapper.getWxMessageCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxMessageMapper.deleteWxMessageById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxMessageMapper.deleteWxMessageById(id);
			}
		}
	}

	public WxMessage getWxMessage(Long id) {
		if (id == null) {
			return null;
		}
		WxMessage wxMessage = wxMessageMapper.getWxMessageById(id);
		return wxMessage;
	}
	


	public int getWxMessageCountByQueryCriteria(WxMessageQuery query) {
		return wxMessageMapper.getWxMessageCount(query);
	}

	public List<WxMessage> getWxMessagesByQueryCriteria(int start,
			int pageSize, WxMessageQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxMessage> rows = sqlSessionTemplate.selectList("getWxMessages",
				query, rowBounds);
		return rows;
	}

	public List<WxMessage> list(WxMessageQuery query) {
		query.ensureInitialized();
		List<WxMessage> list = wxMessageMapper.getWxMessages(query);
		return list;
	}

	@Transactional
	public void save(WxMessage wxMessage) {
		if (wxMessage.getId() == 0) {
			wxMessage.setId(idGenerator.nextId());
			wxMessage.setCreateDate(new Date());
			wxMessageMapper.insertWxMessage(wxMessage);
		} else {
			wxMessage.setLastUpdateDate(new Date());
			wxMessageMapper.updateWxMessage(wxMessage);
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
	public void setWxMessageMapper(WxMessageMapper wxMessageMapper) {
		this.wxMessageMapper = wxMessageMapper;
	}

}
