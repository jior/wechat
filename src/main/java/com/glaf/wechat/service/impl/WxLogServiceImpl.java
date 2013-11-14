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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.config.Configuration;
import com.glaf.core.id.*;
import com.glaf.core.util.DateUtils;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.mapper.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.*;

@Service("wxLogService")
@Transactional(readOnly = true)
public class WxLogServiceImpl implements WxLogService {
	protected final static Log logger = LogFactory
			.getLog(WxLogServiceImpl.class);

	protected static Configuration conf = WechatConfiguration.create();

	protected static Stack<WxLog> wxLogs = new Stack<WxLog>();

	protected static long lastUpdate = System.currentTimeMillis();

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxLogMapper sysLogMapper;

	public WxLogServiceImpl() {

	}

	public int count(WxLogQuery query) {
		return sysLogMapper.getWxLogCount(query);
	}

	@Transactional
	public boolean create(WxLog bean) {
		this.save(bean);
		return true;
	}

	public int getWxLogCountByQueryCriteria(WxLogQuery query) {
		return sysLogMapper.getWxLogCount(query);
	}

	public List<WxLog> getWxLogsByQueryCriteria(int start, int pageSize,
			WxLogQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxLog> rows = sqlSessionTemplate.selectList("getWxLogs", query,
				rowBounds);
		return rows;
	}

	public List<WxLog> list(WxLogQuery query) {
		List<WxLog> list = sysLogMapper.getWxLogs(query);
		return list;
	}

	@Transactional
	public void save(WxLog sysLog) {
		sysLog.setId(idGenerator.nextId());
		sysLog.setCreateTime(new Date());
		sysLog.setSuffix("_" + DateUtils.getNowYearMonthDay());
		wxLogs.push(sysLog);
		/**
		 * 当记录数达到写数据库的条数或时间超过1分钟，写日志到数据库
		 */
		if (wxLogs.size() >= conf.getInt("wx_log_step", 100)
				|| ((System.currentTimeMillis() - lastUpdate) / 60000 > 0)) {
			while (!wxLogs.isEmpty()) {
				WxLog bean = wxLogs.pop();
				sysLogMapper.insertWxLog(bean);
			}
			lastUpdate = System.currentTimeMillis();
		}
	}

	@Resource
	@Qualifier("myBatisDbIdGenerator")
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Resource
	public void setWxLogMapper(WxLogMapper sysLogMapper) {
		this.sysLogMapper = sysLogMapper;
	}

}
