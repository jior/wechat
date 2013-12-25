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
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.id.*;
import com.glaf.core.dao.*;
import com.glaf.core.service.ITablePageService;
import com.glaf.core.util.DBUtils;
import com.glaf.core.util.DateUtils;
import com.glaf.core.util.JsonUtils;
import com.glaf.wechat.mapper.*;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.WxModuleService;

@Service("wxModuleService")
@Transactional(readOnly = true)
public class WxModuleServiceImpl implements WxModuleService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxModuleMapper wxModuleMapper;

	protected ITablePageService tablePageService;

	public WxModuleServiceImpl() {

	}

	public int count(WxModuleQuery query) {
		return wxModuleMapper.getWxModuleCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxModuleMapper.deleteWxModuleById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxModuleMapper.deleteWxModuleById(id);
			}
		}
	}

	public WxModule getWxModule(long id) {
		WxModule wxModule = wxModuleMapper.getWxModuleById(id);
		return wxModule;
	}

	public int getWxModuleCountByQueryCriteria(WxModuleQuery query) {
		return wxModuleMapper.getWxModuleCount(query);
	}

	public List<WxModule> getWxModulesByQueryCriteria(int start, int pageSize,
			WxModuleQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxModule> rows = sqlSessionTemplate.selectList("getWxModules",
				query, rowBounds);
		return rows;
	}

	/**
	 * 根据主键获取一条数据
	 * 
	 * @param id
	 * @param accountId
	 * @param params
	 * @return
	 */
	public WxModule getWxModuleWithData(long id, long accountId,
			Map<String, Object> params) {
		WxModule wxModule = wxModuleMapper.getWxModuleById(id);
		if (wxModule != null && StringUtils.isNotEmpty(wxModule.getSql())) {
			if (DBUtils.isLegalQuerySql(wxModule.getSql())) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				if (StringUtils.isNotEmpty(wxModule.getJson())) {
					String json = wxModule.getJson();
					if (StringUtils.isNotEmpty(json)) {
						Map<String, Object> jsonMap = JsonUtils.decode(json);
						if (jsonMap != null && !jsonMap.isEmpty()) {
							Set<Entry<String, Object>> entrySet = jsonMap
									.entrySet();
							for (Entry<String, Object> entry : entrySet) {
								String key = entry.getKey();
								Object value = entry.getValue();
								paramMap.put(key, value);
							}
						}
					}
				}
				if (params != null && !params.isEmpty()) {
					paramMap.putAll(params);
				}
				Date now = new Date();
				paramMap.put("now", now);
				paramMap.put("curr_date", now);
				paramMap.put("curr_yyyymmdd", DateUtils.getYearMonthDay(now));
				paramMap.put("curr_yyyymm", DateUtils.getYearMonth(now));
				paramMap.put("accountId", accountId);
				List<Map<String, Object>> list = tablePageService.getListData(
						wxModule.getSql(), paramMap);
				if (list != null && !list.isEmpty()) {

				}
				wxModule.setDataList(list);
			}
		}
		return wxModule;
	}

	public List<WxModule> list(WxModuleQuery query) {
		List<WxModule> list = wxModuleMapper.getWxModules(query);
		return list;
	}

	@Transactional
	public void save(WxModule wxModule) {
		if (wxModule.getId() == 0) {
			wxModule.setId(idGenerator.nextId());
			wxModuleMapper.insertWxModule(wxModule);
		} else {
			wxModuleMapper.updateWxModule(wxModule);
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
	public void setTablePageService(ITablePageService tablePageService) {
		this.tablePageService = tablePageService;
	}

	@javax.annotation.Resource
	public void setWxModuleMapper(WxModuleMapper wxModuleMapper) {
		this.wxModuleMapper = wxModuleMapper;
	}

}
