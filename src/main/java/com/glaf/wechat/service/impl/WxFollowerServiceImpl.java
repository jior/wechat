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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.config.Configuration;
import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.core.util.DateUtils;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.WxFollower;
import com.glaf.wechat.mapper.WxFollowerMapper;
import com.glaf.wechat.query.WxFollowerQuery;
import com.glaf.wechat.service.WxFollowerService;
import com.glaf.wechat.util.WxFollowerDomainFactory;

@Service("wxFollowerService")
@Transactional(readOnly = true)
public class WxFollowerServiceImpl implements WxFollowerService {
	protected static Configuration conf = WechatConfiguration.create();

	protected static BlockingQueue<WxFollower> wxFollowers = new ArrayBlockingQueue<WxFollower>(
			1000);

	protected static long lastUpdate = System.currentTimeMillis();

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxFollowerMapper wxFollowerMapper;

	public WxFollowerServiceImpl() {

	}

	public int count(WxFollowerQuery query) {
		return wxFollowerMapper.getWxFollowerCount(query);
	}

	@Transactional
	public void deleteById(Long accountId, String id) {
		if (id != null) {
			WxFollowerQuery query = new WxFollowerQuery();
			query.setOpenId(id);
			query.setAccountId(accountId);
			query.setTableName(WxFollowerDomainFactory.TABLENAME_PREFIX
					+ accountId);
			wxFollowerMapper.deleteWxFollowerById(query);
		}
	}

	@Transactional
	public void deleteByIds(Long accountId, List<String> openIds) {
		if (openIds != null && !openIds.isEmpty()) {
			for (String id : openIds) {
				WxFollowerQuery query = new WxFollowerQuery();
				query.setOpenId(id);
				query.setAccountId(accountId);
				query.setTableName(WxFollowerDomainFactory.TABLENAME_PREFIX
						+ accountId);
				wxFollowerMapper.deleteWxFollowerById(query);
			}
		}
	}

	public long getCurrentUnixTimestamp() {
		Date date = new Date();
		return (date.getTime());
	}

	public WxFollower getWxFollower(Long accountId, Long id) {
		if (id == null) {
			return null;
		}
		WxFollowerQuery query = new WxFollowerQuery();
		query.setId(id);
		query.setAccountId(accountId);
		query.setTableName(WxFollowerDomainFactory.TABLENAME_PREFIX + accountId);
		WxFollower wxFollower = wxFollowerMapper.getWxFollowerById(query);
		return wxFollower;
	}

	public WxFollower getWxFollowerByOpenId(Long accountId, String openId) {
		WxFollowerQuery query = new WxFollowerQuery();
		query.openId(openId);
		query.setTableName(WxFollowerDomainFactory.TABLENAME_PREFIX + accountId);
		List<WxFollower> list = wxFollowerMapper.getWxFollowers(query);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
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

	public List<WxFollower> getEmptyWxFollowers(WxFollowerQuery query) {
		RowBounds rowBounds = new RowBounds(0, 10000);
		List<WxFollower> rows = sqlSessionTemplate.selectList(
				"getEmptyWxFollowers", query, rowBounds);
		return rows;
	}

	public List<String> getExistsWxFollowers(Long accountId,
			Collection<String> openIds) {
		List<String> list = new ArrayList<String>();
		if (openIds != null && !openIds.isEmpty()) {
			WxFollowerQuery query = new WxFollowerQuery();
			query.setTableName(WxFollowerDomainFactory.TABLENAME_PREFIX
					+ accountId);
			List<String> openIds2 = new ArrayList<String>();
			Iterator<String> iterator = openIds.iterator();
			while (iterator.hasNext()) {
				openIds2.add(iterator.next());
				if (openIds2.size() % 500 == 0) {
					query.setOpenIds(openIds2);
					List<String> exists = wxFollowerMapper
							.getExistsWxFollowerIds(query);
					if (exists != null && !exists.isEmpty()) {
						list.addAll(exists);
					}
					openIds2.clear();
				}
			}
			if (openIds2.size() > 0) {
				query.setOpenIds(openIds2);
				List<String> exists = wxFollowerMapper
						.getExistsWxFollowerIds(query);
				if (exists != null && !exists.isEmpty()) {
					list.addAll(exists);
				}
				openIds2.clear();
			}
		}
		return list;
	}

	@Transactional
	public void insertAll(List<WxFollower> followers) {
		if (followers != null && !followers.isEmpty()) {
			for (WxFollower follower : followers) {
				this.save(follower);
			}
		}
	}

	public List<WxFollower> list(WxFollowerQuery query) {
		List<WxFollower> list = wxFollowerMapper.getWxFollowers(query);
		return list;
	}

	@Transactional
	public void save(WxFollower wxFollower) {
		if (conf.getBoolean("wx_follower_async_write", true)) {
			try {
				wxFollowers.put(wxFollower);
			} catch (InterruptedException ex) {
			}
			logger.debug("->followers.size:" + wxFollowers.size());
			/**
			 * 当记录数达到写数据库的条数或时间超过1分钟，写数据到数据库
			 */
			if (wxFollowers.size() >= conf.getInt("wx_follower_step", 100)
					|| ((System.currentTimeMillis() - lastUpdate) / 60000 > 0)) {
				WxFollower follower = null;
				while (!wxFollowers.isEmpty()) {
					follower = wxFollowers.poll();
					this.saveInner(follower);
				}
				lastUpdate = System.currentTimeMillis();
				logger.debug("followers.size:" + wxFollowers.size());
			}
		} else {
			this.saveInner(wxFollower);
		}
	}

	@Transactional
	protected void saveInner(WxFollower follower) {
		WxFollower bean = this.getWxFollowerByOpenId(follower.getAccountId(),
				follower.getOpenId());
		if (bean != null) {
			follower.setTableName(WxFollowerDomainFactory.TABLENAME_PREFIX
					+ follower.getAccountId());
			follower.setLastModified(System.currentTimeMillis());

			if (follower.getSubscribeTime() != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(follower.getSubscribeTime() * 1000);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				follower.setSubscribeYear(year);
				follower.setSubscribeMonth(month);
				follower.setSubscribeDay(DateUtils.getYearMonthDay(calendar
						.getTime()));
			}

			wxFollowerMapper.updateWxFollower(follower);
		} else {
			if (follower.getSubscribeTime() == null
					|| follower.getSubscribeTime() == 0) {
				follower.setSubscribeTime(getCurrentUnixTimestamp());
			}
			follower.setTableName(WxFollowerDomainFactory.TABLENAME_PREFIX
					+ follower.getAccountId());
			follower.setLastModified(System.currentTimeMillis());

			if (follower.getSubscribeTime() != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(follower.getSubscribeTime() * 1000);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				follower.setSubscribeYear(year);
				follower.setSubscribeMonth(month);
				follower.setSubscribeDay(DateUtils.getYearMonthDay(calendar
						.getTime()));
			}

			wxFollowerMapper.insertWxFollower(follower);
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
	public void setWxFollowerMapper(WxFollowerMapper wxFollowerMapper) {
		this.wxFollowerMapper = wxFollowerMapper;
	}

}
