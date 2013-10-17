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
package com.glaf.wechat.website.springmvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.base.BaseTree;
import com.glaf.core.base.TreeModel;
import com.glaf.core.tree.helper.TreeHelper;
import com.glaf.wechat.domain.WxCategory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.service.WxCategoryService;
import com.glaf.wechat.service.WxConfigService;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxCoverService;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.service.WxMenuService;
import com.glaf.wechat.service.WxSiteInfoService;
import com.glaf.wechat.service.WxTemplateService;
import com.glaf.wechat.service.WxUserTemplateService;
import com.glaf.wechat.util.WxContentJsonFactory;

@Controller("/wx/json")
@RequestMapping("/wx/json")
public class WxPublicJsonController {

	protected static final Log logger = LogFactory
			.getLog(WxPublicJsonController.class);

	protected WxCategoryService wxCategoryService;

	protected WxTemplateService wxTemplateService;

	protected WxUserTemplateService wxUserTemplateService;

	protected WxContentService wxContentService;

	protected WxCoverService wxCoverService;

	protected WxFileService wxFileService;

	protected WxMenuService wxMenuService;

	protected WxConfigService wxConfigService;

	protected WxSiteInfoService wxSiteInfoService;

	public WxPublicJsonController() {

	}

	@ResponseBody
	@RequestMapping("/category/{customer}")
	public byte[] category(@PathVariable("customer") String customer,
			HttpServletRequest request) throws IOException {
		JSONArray result = new JSONArray();
		String type = request.getParameter("type");
		if (StringUtils.isEmpty(type)) {
			type = "category";
		}
		List<WxCategory> categories = wxCategoryService.getCategoryList(
				customer, type);
		if (categories != null && !categories.isEmpty()) {
			List<TreeModel> treeModels = new ArrayList<TreeModel>();
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
			TreeHelper treeHelper = new TreeHelper();
			result = treeHelper.getTreeJSONArray(treeModels);
		}

		return result.toJSONString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping("/content/{uuid}")
	public byte[] content(@PathVariable("uuid") String uuid,
			HttpServletRequest request) throws IOException {
		WxContent wxContent = null;
		if (StringUtils.isNotEmpty(uuid)) {
			wxContent = wxContentService.getWxContentByUUIDWithRefs(uuid);
		}
		JSONObject result = new JSONObject();
		if (wxContent != null) {
			result = wxContent.toJsonObject();
			result.put("id", wxContent.getId());
			result.put("contentId", wxContent.getId());
		}
		return result.toJSONString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping("/list/{customer}/{categoryId}")
	public byte[] list(@PathVariable("customer") String customer,
			@PathVariable("categoryId") Long categoryId,
			HttpServletRequest request) throws IOException {
		JSONArray result = null;
		String type = request.getParameter("type");
		if (StringUtils.isEmpty(type)) {
			type = "P";
		}
		List<WxContent> list = wxContentService.getWxContents(customer,
				categoryId, type);
		result = WxContentJsonFactory.listToArray(list);
		return result.toJSONString().getBytes("UTF-8");
	}

	@javax.annotation.Resource
	public void setWxCategoryService(WxCategoryService wxCategoryService) {
		this.wxCategoryService = wxCategoryService;
	}

	@javax.annotation.Resource
	public void setWxConfigService(WxConfigService wxConfigService) {
		this.wxConfigService = wxConfigService;
	}

	@javax.annotation.Resource
	public void setWxContentService(WxContentService wxContentService) {
		this.wxContentService = wxContentService;
	}

	@javax.annotation.Resource
	public void setWxCoverService(WxCoverService wxCoverService) {
		this.wxCoverService = wxCoverService;
	}

	@javax.annotation.Resource
	public void setWxFileService(WxFileService wxFileService) {
		this.wxFileService = wxFileService;
	}

	@javax.annotation.Resource
	public void setWxMenuService(WxMenuService wxMenuService) {
		this.wxMenuService = wxMenuService;
	}

	@javax.annotation.Resource
	public void setWxSiteInfoService(WxSiteInfoService wxSiteInfoService) {
		this.wxSiteInfoService = wxSiteInfoService;
	}

	@javax.annotation.Resource
	public void setWxTemplateService(WxTemplateService wxTemplateService) {
		this.wxTemplateService = wxTemplateService;
	}

	@javax.annotation.Resource
	public void setWxUserTemplateService(
			WxUserTemplateService wxUserTemplateService) {
		this.wxUserTemplateService = wxUserTemplateService;
	}

}
