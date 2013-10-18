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
package com.glaf.wechat.generation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.glaf.core.context.ContextFactory;
import com.glaf.core.util.FileUtils;
import com.glaf.core.util.StringTools;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.WxContentService;

public class ContentJsonGeneration {

	protected final static Log logger = LogFactory
			.getLog(ContentJsonGeneration.class);

	protected static String configurationResource = "spring/spring-config.xml";

	protected static org.springframework.context.ApplicationContext ctx;

	public static void main(String[] args) {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(configurationResource);
		}
		ContextFactory.setContext(ctx);
		ContentJsonGeneration gen = new ContentJsonGeneration();
		gen.generateJson("data", "root");
	}

	public void generateJson(String rootDir, Date beginDate, Date endDate) {
		WxContentService wxContentService = ContextFactory
				.getBean("wxContentService");
		WxContentQuery query = new WxContentQuery();
		query.createDateGreaterThanOrEqual(beginDate);
		query.createDateLessThanOrEqual(endDate);
		List<WxContent> rows = wxContentService.list(query);
		for (int i = 0; i < rows.size(); i++) {
			WxContent item = rows.get(0);
			if (StringUtils.isNotEmpty(item.getRelationIds())
					|| StringUtils.isNotEmpty(item.getRecommendationIds())) {
				item = wxContentService.getWxContentWithRefs(item.getId());
			}
			this.generateJson(rootDir, item);
		}
	}

	public void generateJson(String rootDir, List<WxContent> list) {
		rootDir = StringTools.replace(rootDir, "\\", "/");
		if (!rootDir.endsWith("/")) {
			rootDir = rootDir + "/";
		}
		Date date = null;
		boolean success = false;
		int retry = 0;
		byte[] bytes = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		for (WxContent item : list) {
			date = item.getCreateDate();
			if (date == null) {
				continue;
			}
			String dateStr = dateFormat.format(date);
			String filename = rootDir + dateStr + "/" + item.getId() + ".json";
			String filename1 = rootDir + dateStr + "/" + item.getUuid()
					+ ".json";
			success = false;
			retry = 0;
			bytes = null;
			while (retry < 2 && !success) {
				try {
					retry++;
					FileUtils.mkdirs(rootDir + dateStr);
					bytes = item.toJsonObject().toJSONString()
							.getBytes("UTF-8");
					FileUtils.save(filename, bytes);
					FileUtils.save(filename1, bytes);
					success = true;
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error(ex);
				}
			}
		}
	}

	public void generateJson(String rootDir, String createBy) {
		WxContentService wxContentService = ContextFactory
				.getBean("wxContentService");
		WxContentQuery query = new WxContentQuery();
		query.createBy(createBy);
		List<WxContent> rows = wxContentService.list(query);
		for (int i = 0; i < rows.size(); i++) {
			WxContent item = rows.get(0);
			if (StringUtils.isNotEmpty(item.getRelationIds())
					|| StringUtils.isNotEmpty(item.getRecommendationIds())) {
				item = wxContentService.getWxContentWithRefs(item.getId());
			}
			this.generateJson(rootDir, item);
		}
	}

	public void generateJson(String rootDir, String createBy, Date beginDate,
			Date endDate) {
		WxContentService wxContentService = ContextFactory
				.getBean("wxContentService");
		WxContentQuery query = new WxContentQuery();
		query.createBy(createBy);
		query.createDateGreaterThanOrEqual(beginDate);
		query.createDateLessThanOrEqual(endDate);
		List<WxContent> rows = wxContentService.list(query);
		for (int i = 0; i < rows.size(); i++) {
			WxContent item = rows.get(0);
			if (StringUtils.isNotEmpty(item.getRelationIds())
					|| StringUtils.isNotEmpty(item.getRecommendationIds())) {
				item = wxContentService.getWxContentWithRefs(item.getId());
			}
			this.generateJson(rootDir, item);
		}
	}

	public void generateJson(String rootDir, WxContent item) {
		rootDir = StringTools.replace(rootDir, "\\", "/");
		if (!rootDir.endsWith("/")) {
			rootDir = rootDir + "/";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = item.getCreateDate();
		String dateStr = dateFormat.format(date);
		String filename = rootDir + dateStr + "/" + item.getId() + ".json";
		String filename1 = rootDir + dateStr + "/" + item.getUuid() + ".json";
		boolean success = false;
		int retry = 0;
		byte[] bytes = null;
		while (retry < 2 && !success) {
			try {
				retry++;
				FileUtils.mkdirs(rootDir + dateStr);
				bytes = item.toJsonObject().toJSONString().getBytes("UTF-8");
				FileUtils.save(filename, bytes);
				FileUtils.save(filename1, bytes);
				success = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
			}
		}
	}

}
