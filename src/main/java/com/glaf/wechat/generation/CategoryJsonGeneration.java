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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.glaf.core.base.BaseTree;
import com.glaf.core.base.TreeModel;
import com.glaf.core.context.ContextFactory;
import com.glaf.core.identity.User;
import com.glaf.core.security.IdentityFactory;
import com.glaf.core.tree.helper.TreeHelper;
import com.glaf.core.util.FileUtils;
import com.glaf.core.util.StringTools;
import com.glaf.wechat.domain.WxCategory;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.query.WxCategoryQuery;
import com.glaf.wechat.service.WxCategoryService;
import com.glaf.wechat.service.WxUserService;
import com.glaf.wechat.util.WechatUtils;

public class CategoryJsonGeneration {

	protected final static Log logger = LogFactory
			.getLog(CategoryJsonGeneration.class);

	protected static String configurationResource = "spring/spring-config.xml";

	protected static org.springframework.context.ApplicationContext ctx;

	public static void main(String[] args) {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(configurationResource);
		}
		ContextFactory.setContext(ctx);
		CategoryJsonGeneration gen = new CategoryJsonGeneration();
		gen.generateJson("data", 1L, "category");
	}

	public void generateJson(String rootDir, Long accountId, String type) {
		WxCategoryService wxCategoryService = ContextFactory
				.getBean("wxCategoryService");
		WxUserService wxUserService = ContextFactory.getBean("wxUserService");
		WxCategoryQuery query = new WxCategoryQuery();
		query.accountId(accountId);
		query.type(type);

		WxUser wxUser = wxUserService.getWxUser(accountId);
		User user = IdentityFactory.getUser(wxUser.getActorId());

		if (StringUtils.isEmpty(type)) {
			type = "category";
		}
		List<WxCategory> categories = wxCategoryService.getCategoryList(
				accountId, type);
		if (categories != null && !categories.isEmpty()) {
			List<TreeModel> treeModels = new java.util.concurrent.CopyOnWriteArrayList<TreeModel>();
			for (WxCategory category : categories) {
				TreeModel tree = new BaseTree();
				tree.setId(category.getId());
				tree.setParentId(category.getParentId());
				tree.setCode(category.getCode());
				tree.setName(category.getName());
				tree.setSortNo(category.getSort());
				tree.setDescription(category.getDesc());
				tree.setCreateBy(category.getCreateBy());
				tree.setIconCls("tree_folder");
				tree.setTreeId(category.getTreeId());
				tree.setUrl(category.getUrl());
				treeModels.add(tree);
			}
			rootDir = StringTools.replace(rootDir, "\\", "/");
			if (!rootDir.endsWith("/")) {
				rootDir = rootDir + "/";
			}
			TreeHelper treeHelper = new TreeHelper();
			JSONArray result = treeHelper.getTreeJSONArray(treeModels);
			String rand = WechatUtils.getImagePath(user.getId(), accountId);
			String filename = rootDir + rand + "/" + type + ".json";
			String path = rootDir + rand;
			boolean success = false;
			int retry = 0;
			byte[] bytes = null;
			while (retry < 2 && !success) {
				try {
					retry++;
					FileUtils.mkdirs(path);
					bytes = result.toJSONString().getBytes("UTF-8");
					FileUtils.save(filename, bytes);
					success = true;
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error(ex);
				}
			}
		}

	}

}
