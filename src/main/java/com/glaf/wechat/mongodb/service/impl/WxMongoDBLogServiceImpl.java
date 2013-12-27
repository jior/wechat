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
package com.glaf.wechat.mongodb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.glaf.core.config.Configuration;
import com.glaf.core.util.DateUtils;
import com.glaf.core.util.Paging;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.query.WxLogQuery;
import com.glaf.wechat.service.WxLogService;

public class WxMongoDBLogServiceImpl implements WxLogService {
	protected final static Log logger = LogFactory
			.getLog(WxMongoDBLogServiceImpl.class);

	protected static Configuration conf = WechatConfiguration.create();

	protected static Stack<WxLog> wxLogs = new Stack<WxLog>();

	protected static long lastUpdate = System.currentTimeMillis();

	protected MongoTemplate mongoTemplate;

	public boolean create(WxLog bean) {
		bean.setId(Long.MAX_VALUE - System.currentTimeMillis());
		bean.setCreateTime(new Date());
		bean.setSuffix("_" + DateUtils.getNowYearMonthDay());
		wxLogs.push(bean);
		/**
		 * 当记录数达到写数据库的条数或时间超过1分钟，写日志到数据库
		 */
		if (wxLogs.size() >= conf.getInt("wx_log_step", 100)
				|| ((System.currentTimeMillis() - lastUpdate) / 60000 > 0)) {
			DB db = mongoTemplate.getDb();
			db.requestStart();
			while (!wxLogs.isEmpty()) {
				WxLog model = wxLogs.pop();
				String tableName = "wx_log" + model.getSuffix();
				DBCollection coll = db.getCollection(tableName);
				if (coll != null) {
					BasicDBObject row = new BasicDBObject();
					row.put("id", model.getId());
					row.put("accountId", model.getAccountId());
					row.put("actorId", model.getActorId());
					row.put("openId", model.getOpenId());
					row.put("flag", Integer.valueOf(model.getFlag()));
					row.put("ip", model.getIp());
					row.put("operate", model.getOperate());
					row.put("content", model.getContent());
					row.put("createTime", model.getCreateTime().getTime());
					coll.insert(row);
					logger.debug("insert row:" + model.getId());
				}
			}
			db.requestDone();
			lastUpdate = System.currentTimeMillis();
			logger.debug("submit ok.");
			return true;
		}
		return false;
	}

	protected void fillQueryCondition(DBObject q, WxLogQuery query) {
		if (query.getCreateTimeLessThanOrEqual() != null) {
			q.put("createTime", new BasicDBObject("&lte", query
					.getCreateTimeLessThanOrEqual().getTime()));
		}

		if (query.getCreateTimeGreaterThanOrEqual() != null) {
			q.put("createTime", new BasicDBObject("&gte", query
					.getCreateTimeGreaterThanOrEqual().getTime()));
		}

		if (query.getAccountId() != null && query.getAccountId() != 0) {
			q.put("accountId", query.getAccountId());
		}

		if (query.getActorId() != null) {
			q.put("actorId", query.getActorId());
		}

		if (query.getOpenId() != null) {
			q.put("openId", query.getOpenId());
		}

		if (query.getIp() != null) {
			q.put("ip", query.getIp());
		}

		if (query.getOperate() != null) {
			q.put("operate", query.getOperate());
		}

		if (query.getFlag() != null && query.getFlag() != 0) {
			q.put("flag", query.getFlag());
		}

	}

	protected int getInt(DBObject object, String name) {
		int ret = 0;
		if (object.containsField(name)) {
			Object obj = object.get(name);
			if (obj instanceof Double) {
				Double d = (Double) obj;
				return d.intValue();
			} else if (obj instanceof Long) {
				Long d = (Long) obj;
				return d.intValue();
			} else if (obj instanceof Integer) {
				Integer d = (Integer) obj;
				return d.intValue();
			} else {
				String str = obj.toString();
				if (StringUtils.isNumeric(str)) {
					return Integer.valueOf(str);
				}
			}
		}
		return ret;
	}

	public int getWxLogCountByQueryCriteria(WxLogQuery query) {
		DB db = mongoTemplate.getDb();
		String tableName = "wx_log" + query.getSuffix();
		DBCollection coll = db.getCollection(tableName);
		if (coll != null) {
			BasicDBObject q = new BasicDBObject();
			this.fillQueryCondition(q, query);
			int count = (int) coll.count(q);
			logger.debug("count=" + count);
			return count;
		}
		return 0;
	}

	public List<WxLog> getWxLogsByQueryCriteria(int start, int pageSize,
			WxLogQuery query) {
		DB db = mongoTemplate.getDb();
		String tableName = "wx_log" + query.getSuffix();
		DBCollection coll = db.getCollection(tableName);
		BasicDBObject q = new BasicDBObject();
		this.fillQueryCondition(q, query);
		DBCursor cur = coll.find(q);

		List<WxLog> logs = new ArrayList<WxLog>();

		int limit = query.getPageSize();
		if (limit <= 0) {
			limit = Paging.DEFAULT_PAGE_SIZE;
		}
		if (start < cur.count()) {
			logger.debug("start=" + start);
			logger.debug("limit=" + limit);
			List<DBObject> list = coll.find(q).skip(start).limit(limit)
					.toArray();
			for (DBObject object : list) {
				WxLog log = new WxLog();
				log.setId((Long) object.get("id"));
				log.setIp((String) object.get("ip"));
				log.setActorId((String) object.get("actorId"));
				log.setContent((String) object.get("content"));
				log.setOperate((String) object.get("operate"));
				if (object.containsField("accountId")) {
					log.setAccountId((Long) object.get("accountId"));
				}
				if (object.containsField("openId")) {
					log.setOpenId((String) object.get("openId"));
				}
				if (object.containsField("flag")) {
					log.setFlag((Integer) object.get("flag"));
				}
				if (object.containsField("createTime")) {
					long ts = (Long) object.get("createTime");
					log.setCreateTime(new Date(ts));
				}

				logs.add(log);
			}
		}
		return logs;
	}

	public List<WxLog> list(WxLogQuery query) {
		DB db = mongoTemplate.getDb();
		String tableName = "wx_log" + query.getSuffix();
		DBCollection coll = db.getCollection(tableName);
		BasicDBObject q = new BasicDBObject();
		this.fillQueryCondition(q, query);
		List<DBObject> list = coll.find(q).toArray();
		List<WxLog> logs = new ArrayList<WxLog>();
		for (DBObject object : list) {
			WxLog log = new WxLog();
			log.setId((Long) object.get("id"));
			log.setIp((String) object.get("ip"));
			log.setActorId((String) object.get("actorId"));
			log.setOperate((String) object.get("operate"));
			log.setContent((String) object.get("content"));
			if (object.containsField("accountId")) {
				log.setAccountId((Long) object.get("accountId"));
			}
			if (object.containsField("openId")) {
				log.setOpenId((String) object.get("openId"));
			}
			if (object.containsField("flag")) {
				log.setFlag((Integer) object.get("flag"));
			}
			if (object.containsField("createTime")) {
				long ts = (Long) object.get("createTime");
				log.setCreateTime(new Date(ts));
			}

			logs.add(log);
		}
		return logs;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
