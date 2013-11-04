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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.cache.CacheFactory;
import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.core.util.UUID32;
import com.glaf.wechat.domain.WxConfig;
import com.glaf.wechat.mapper.WxConfigMapper;
import com.glaf.wechat.query.WxConfigQuery;
import com.glaf.wechat.service.*;
import com.glaf.wechat.util.WxConfigJsonFactory;

@Service("wxConfigService")
@Transactional(readOnly = true)
public class WxConfigServiceImpl implements WxConfigService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxConfigMapper wxConfigMapper;

	public WxConfigServiceImpl() {

	}

	public int count(WxConfigQuery query) {
		query.ensureInitialized();
		return wxConfigMapper.getWxConfigCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxConfigMapper.deleteWxConfigById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxConfigMapper.deleteWxConfigById(id);
			}
		}
	}

	public WxConfig getWxConfig(Long id) {
		if (id == null) {
			return null;
		}
		WxConfig wxConfig = wxConfigMapper.getWxConfigById(id);
		return wxConfig;
	}

	public WxConfig getWxConfigByUser(String createBy) {
		String cacheKey = "wx_cfg_" + createBy;
		if (CacheFactory.getString(cacheKey) != null) {
			String text = CacheFactory.getString(cacheKey);
			JSONObject json = JSON.parseObject(text);
			WxConfig cfg = WxConfigJsonFactory.jsonToObject(json);
			return cfg;
		}
		WxConfigQuery query = new WxConfigQuery();
		query.createBy(createBy);
		List<WxConfig> list = wxConfigMapper.getWxConfigs(query);
		if (list != null && !list.isEmpty()) {
			WxConfig cfg = list.get(0);
			if (cfg != null) {
				JSONObject json = WxConfigJsonFactory.toJsonObject(cfg);
				CacheFactory.put(cacheKey, json.toJSONString());
				return cfg;
			}
		}
		return null;
	}

	public int getWxConfigCountByQueryCriteria(WxConfigQuery query) {
		return wxConfigMapper.getWxConfigCount(query);
	}

	public List<WxConfig> getWxConfigsByQueryCriteria(int start, int pageSize,
			WxConfigQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxConfig> rows = sqlSessionTemplate.selectList("getWxConfigs",
				query, rowBounds);
		return rows;
	}

	public List<WxConfig> list(WxConfigQuery query) {
		List<WxConfig> list = wxConfigMapper.getWxConfigs(query);
		return list;
	}

	@Transactional
	public void save(WxConfig wxConfig) {
		if (wxConfig.getId() == 0) {
			wxConfig.setId(idGenerator.nextId());
			wxConfig.setCreateDate(new Date());
			wxConfig.setUuid(UUID32.getUUID());
			wxConfigMapper.insertWxConfig(wxConfig);
		} else {
			wxConfig.setLastUpdateDate(new Date());
			wxConfigMapper.updateWxConfig(wxConfig);
			String cacheKey = "wx_cfg_" + wxConfig.getCreateBy();
			CacheFactory.remove(cacheKey);
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
	public void setWxConfigMapper(WxConfigMapper wxConfigMapper) {
		this.wxConfigMapper = wxConfigMapper;
	}

}
