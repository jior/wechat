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
import com.glaf.wechat.domain.WxSiteInfo;
import com.glaf.wechat.mapper.WxSiteInfoMapper;
import com.glaf.wechat.query.WxSiteInfoQuery;
import com.glaf.wechat.service.*;

@Service("wxSiteInfoService")
@Transactional(readOnly = true)
public class WxSiteInfoServiceImpl implements WxSiteInfoService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxSiteInfoMapper wxSiteInfoMapper;

	public WxSiteInfoServiceImpl() {

	}

	public int count(WxSiteInfoQuery query) {
		query.ensureInitialized();
		return wxSiteInfoMapper.getWxSiteInfoCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxSiteInfoMapper.deleteWxSiteInfoById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxSiteInfoMapper.deleteWxSiteInfoById(id);
			}
		}
	}

	public WxSiteInfo getWxSiteInfo(Long id) {
		if (id == null) {
			return null;
		}
		WxSiteInfo wxSiteInfo = wxSiteInfoMapper.getWxSiteInfoById(id);
		return wxSiteInfo;
	}
	
	
	
	/**
	 * 根据创建人获取一条记录
	 * 
	 * @return
	 */
	public WxSiteInfo getWxSiteInfoByUser(String createBy){
		WxSiteInfoQuery query = new WxSiteInfoQuery();
		query.createBy(createBy);
		List<WxSiteInfo> list = wxSiteInfoMapper.getWxSiteInfos(query);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public int getWxSiteInfoCountByQueryCriteria(WxSiteInfoQuery query) {
		return wxSiteInfoMapper.getWxSiteInfoCount(query);
	}

	public List<WxSiteInfo> getWxSiteInfosByQueryCriteria(int start,
			int pageSize, WxSiteInfoQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxSiteInfo> rows = sqlSessionTemplate.selectList("getWxSiteInfos",
				query, rowBounds);
		return rows;
	}

	public List<WxSiteInfo> list(WxSiteInfoQuery query) {
		List<WxSiteInfo> list = wxSiteInfoMapper.getWxSiteInfos(query);
		return list;
	}

	@Transactional
	public void save(WxSiteInfo wxSiteInfo) {
		if (wxSiteInfo.getId() == 0) {
			wxSiteInfo.setId(idGenerator.nextId());
			wxSiteInfo.setCreateDate(new Date());
			wxSiteInfoMapper.insertWxSiteInfo(wxSiteInfo);
		} else {
			wxSiteInfo.setLastUpdateDate(new Date());
			wxSiteInfoMapper.updateWxSiteInfo(wxSiteInfo);
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
	public void setWxSiteInfoMapper(WxSiteInfoMapper wxSiteInfoMapper) {
		this.wxSiteInfoMapper = wxSiteInfoMapper;
	}

}
