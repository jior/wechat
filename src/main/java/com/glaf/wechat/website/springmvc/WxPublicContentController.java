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
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glaf.core.config.Configuration;
import com.glaf.core.freemarker.TemplateUtils;
import com.glaf.core.identity.User;
import com.glaf.core.security.IdentityFactory;
import com.glaf.core.util.IOUtils;
import com.glaf.core.util.Paging;
import com.glaf.core.util.ParamUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.WxCategory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.domain.WxTemplate;
import com.glaf.wechat.domain.WxUserTemplate;
import com.glaf.wechat.query.WxCategoryQuery;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.WxCategoryService;
import com.glaf.wechat.service.WxConfigService;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxCoverService;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.service.WxMenuService;
import com.glaf.wechat.service.WxSiteInfoService;
import com.glaf.wechat.service.WxTemplateService;
import com.glaf.wechat.service.WxUserTemplateService;
import com.glaf.wechat.util.WechatUtils;

@Controller("/wx/content")
@RequestMapping("/wx/content")
public class WxPublicContentController {

	protected static final Log logger = LogFactory
			.getLog(WxPublicContentController.class);

	protected static Configuration conf = WechatConfiguration.create();

	protected WxCategoryService wxCategoryService;

	protected WxTemplateService wxTemplateService;

	protected WxUserTemplateService wxUserTemplateService;

	protected WxContentService wxContentService;

	protected WxCoverService wxCoverService;

	protected WxFileService wxFileService;

	protected WxMenuService wxMenuService;

	protected WxConfigService wxConfigService;

	protected WxSiteInfoService wxSiteInfoService;

	public WxPublicContentController() {

	}

	@ResponseBody
	@RequestMapping("/detail/{uuid}")
	public void detail(@PathVariable("uuid") String uuid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		WxContent wxContent = null;
		if (StringUtils.isNotEmpty(uuid)) {
			wxContent = wxContentService.getWxContentByUUIDWithRefs(uuid);
		}
		if (wxContent != null) {
			String actorId = wxContent.getCreateBy();
			Long categoryId = wxContent.getCategoryId();
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(actorId, "2", categoryId);
			if (wxUserTemplate == null) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						actorId, "2", 0L);
			}
			if (wxUserTemplate == null
					|| wxUserTemplate.getTemplateId() == null
					|| wxUserTemplate.getTemplateId() == 0) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						"system", "2", 0L);
			}

			// categoryId == 0 为回复内容或ppt
			WxCategory category = wxCategoryService.getWxCategory(categoryId);
			Long templateId = wxUserTemplate.getTemplateId();
			boolean cache = conf.getBoolean("wx_template_cache", true);
			WxTemplate template = wxTemplateService.getWxTemplate(templateId,
					cache);
			if (template != null && template.getContent() != null) {
				String serviceUrl = WechatUtils.getServiceUrl(request);
				Map<String, Object> context = RequestUtils
						.getParameterMap(request);
				User user = IdentityFactory.getUser(actorId);
				String hashedPath = WechatUtils.getHashedPath(actorId);
				context.put("hashedPath", hashedPath);
				context.put("userId", String.valueOf(user.getId()));
				context.put("actorId", actorId);
				context.put("actorIdMD5Hex", DigestUtils.md5Hex(actorId));
				context.put("content", wxContent);
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);

				WxCategoryQuery query3 = new WxCategoryQuery();
				query3.createBy(actorId);
				query3.parentId(0L);
				query3.type("category");
				query3.locked(0);
				List<WxCategory> list3 = wxCategoryService.list(query3);
				if (list3 != null && !list3.isEmpty()) {
					for (WxCategory cat : list3) {
						if (StringUtils.isNotEmpty(cat.getUrl())) {
							if (StringUtils.startsWith(cat.getUrl(), "/mx/wx/")) {
								cat.setUrl(serviceUrl + cat.getUrl());
							}
							if (StringUtils.startsWith(cat.getUrl(),
									"/website/wx/")) {
								cat.setUrl(serviceUrl + cat.getUrl());
							}
						}
					}
				}
				context.put("categories", list3);

				context.put("category", category);

				String content = TemplateUtils.process(context,
						template.getContent());

				PrintWriter out = response.getWriter();
				out.write(content);
				out.flush();
				IOUtils.closeStream(out);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/index/{userId}")
	public void index(@PathVariable("userId") Long userId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Long categoryId = RequestUtils.getLong(request, "categoryId", 0);
		User user = IdentityFactory.getUserByUserId(userId);
		String actorId = user.getActorId();
		WxUserTemplate wxUserTemplate = wxUserTemplateService
				.getWxUserTemplate(actorId, "0", categoryId);
		if (wxUserTemplate == null || wxUserTemplate.getTemplateId() == null
				|| wxUserTemplate.getTemplateId() == 0) {
			wxUserTemplate = wxUserTemplateService.getWxUserTemplate("system",
					"0", 0L);
		}
		if (wxUserTemplate != null) {
			Long templateId = wxUserTemplate.getTemplateId();
			boolean cache = conf.getBoolean("wx_template_cache", true);
			logger.debug("templateId:"+templateId);
			WxTemplate template = wxTemplateService.getWxTemplate(templateId,
					cache);
			if (template != null && template.getContent() != null) {
				String serviceUrl = WechatUtils.getServiceUrl(request);
				Map<String, Object> context = RequestUtils
						.getParameterMap(request);
				String hashedPath = WechatUtils.getHashedPath(actorId);
				context.put("hashedPath", hashedPath);
				context.put("userId", String.valueOf(user.getId()));
				context.put("actorId", actorId);
				context.put("actorIdMD5Hex", DigestUtils.md5Hex(actorId));
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);

				WxContentQuery query = new WxContentQuery();
				query.createBy(actorId);
				query.categoryId(0L);
				query.type("PPT");
				List<WxContent> list = wxContentService.list(query);
				context.put("pptList", list);

				WxCategoryQuery query3 = new WxCategoryQuery();
				query3.createBy(actorId);
				query3.parentId(0L);
				query3.type("category");
				query3.locked(0);
				List<WxCategory> list3 = wxCategoryService.list(query3);
				context.put("categories", list3);
				if (list3 != null && !list3.isEmpty()) {
					for (WxCategory cat : list3) {
						if (StringUtils.isNotEmpty(cat.getUrl())) {
							if (StringUtils.startsWith(cat.getUrl(), "/mx/wx/")) {
								cat.setUrl(serviceUrl + cat.getUrl());
							}
							if (StringUtils.startsWith(cat.getUrl(),
									"/website/wx/")) {
								cat.setUrl(serviceUrl + cat.getUrl());
							}
						}
					}
				}

				String content = TemplateUtils.process(context,
						template.getContent());
				PrintWriter out = response.getWriter();
				out.write(content);
				out.flush();
				IOUtils.closeStream(out);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/list/{categoryId}")
	public void list(@PathVariable("categoryId") Long categoryId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		WxCategory category = wxCategoryService.getWxCategory(categoryId);
		if (category != null) {
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(categoryId, "1");
			if (wxUserTemplate == null
					|| wxUserTemplate.getTemplateId() == null
					|| wxUserTemplate.getTemplateId() == 0) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						"system", "1", 0L);
			}
			if (wxUserTemplate != null) {
				Long templateId = wxUserTemplate.getTemplateId();
				boolean cache = conf.getBoolean("wx_template_cache", true);
				WxTemplate template = wxTemplateService.getWxTemplate(
						templateId, cache);
				if (template != null && template.getContent() != null) {
					String serviceUrl = WechatUtils.getServiceUrl(request);
					Map<String, Object> context = RequestUtils
							.getParameterMap(request);
					String actorId = category.getCreateBy();
					User user = IdentityFactory.getUser(actorId);
					String hashedPath = WechatUtils.getHashedPath(actorId);
					context.put("hashedPath", hashedPath);
					context.put("userId", String.valueOf(user.getId()));
					context.put("actorId", category.getCreateBy());
					context.put("actorIdMD5Hex",
							DigestUtils.md5Hex(category.getCreateBy()));
					context.put("template", template);
					context.put("contextPath", request.getContextPath());
					context.put("serviceUrl", serviceUrl);
					context.put("serverUrl", serviceUrl);
					context.put("pageSize", 1);
					context.put("pageNo", 1);

					Map<String, Object> params = RequestUtils
							.getParameterMap(request);
					int start = 0;
					int limit = 10;
					int pageNo = ParamUtils.getInt(params, "pageNo");
					start = (pageNo - 1) * limit;
					if (start < 0) {
						start = 0;
					}
					if (limit <= 0) {
						limit = Paging.DEFAULT_PAGE_SIZE;
					}
					if (pageNo < 1) {
						pageNo = 1;
					}
					context.put("pageNo", pageNo);

					WxContentQuery query = new WxContentQuery();
					query.categoryId(categoryId);
					query.status(1);

					int total = wxContentService
							.getWxContentCountByQueryCriteria(query);
					if (total > 0) {
						List<WxContent> list = wxContentService
								.getWxContentsByQueryCriteria(start, limit,
										query);
						context.put("contents", list);
						context.put("pageSize", (total / limit + 1));
					}

					WxContentQuery query2 = new WxContentQuery();
					query2.createBy(category.getCreateBy());
					query2.categoryId(categoryId);
					query2.type("PPT");
					List<WxContent> list = wxContentService.list(query2);
					context.put("pptList", list);
					context.put("category", category);

					WxCategoryQuery query3 = new WxCategoryQuery();
					query3.createBy(category.getCreateBy());
					query3.parentId(0L);
					query3.type("category");
					query3.locked(0);
					List<WxCategory> list3 = wxCategoryService.list(query3);
					context.put("categories", list3);
					if (list3 != null && !list3.isEmpty()) {
						for (WxCategory cat : list3) {
							if (StringUtils.isNotEmpty(cat.getUrl())) {
								if (StringUtils.startsWith(cat.getUrl(),
										"/mx/wx/")) {
									cat.setUrl(serviceUrl + cat.getUrl());
								}
								if (StringUtils.startsWith(cat.getUrl(),
										"/website/wx/")) {
									cat.setUrl(serviceUrl + cat.getUrl());
								}
							}
						}
					}

					String content = TemplateUtils.process(context,
							template.getContent());
					PrintWriter out = response.getWriter();
					out.write(content);
					out.flush();
					IOUtils.closeStream(out);
				}
			}
		}
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

	@ResponseBody
	@RequestMapping("/view/{id}")
	public void view(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		WxContent wxContent = null;
		if (id != null && id > 0) {
			wxContent = wxContentService.getWxContentWithRefs(id);
		}
		if (wxContent != null) {
			String actorId = wxContent.getCreateBy();
			Long categoryId = wxContent.getCategoryId();
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(actorId, "2", categoryId);
			if (wxUserTemplate == null
					|| wxUserTemplate.getTemplateId() == null
					|| wxUserTemplate.getTemplateId() == 0) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						actorId, "2", 0L);
			}
			if (wxUserTemplate == null) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						"system", "2", 0L);
			}

			WxCategory category = wxCategoryService.getWxCategory(categoryId);

			Long templateId = wxUserTemplate.getTemplateId();
			boolean cache = conf.getBoolean("wx_template_cache", true);
			WxTemplate template = wxTemplateService.getWxTemplate(templateId,
					cache);
			if (template != null && template.getContent() != null) {
				String serviceUrl = WechatUtils.getServiceUrl(request);
				Map<String, Object> context = RequestUtils
						.getParameterMap(request);
				User user = IdentityFactory.getUser(actorId);
				String hashedPath = WechatUtils.getHashedPath(actorId);
				context.put("hashedPath", hashedPath);
				context.put("userId", String.valueOf(user.getId()));
				context.put("actorId", actorId);
				context.put("actorIdMD5Hex", DigestUtils.md5Hex(actorId));
				context.put("content", wxContent);
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);

				WxCategoryQuery query3 = new WxCategoryQuery();
				query3.createBy(actorId);
				query3.parentId(0L);
				query3.type("category");
				query3.locked(0);
				List<WxCategory> list3 = wxCategoryService.list(query3);

				if (list3 != null && !list3.isEmpty()) {
					for (WxCategory cat : list3) {
						if (StringUtils.isNotEmpty(cat.getUrl())) {
							if (StringUtils.startsWith(cat.getUrl(), "/mx/wx/")) {
								cat.setUrl(serviceUrl + cat.getUrl());
							}
							if (StringUtils.startsWith(cat.getUrl(),
									"/website/wx/")) {
								cat.setUrl(serviceUrl + cat.getUrl());
							}
						}
					}
				}
				context.put("categories", list3);
				context.put("category", category);

				String content = TemplateUtils.process(context,
						template.getContent());

				PrintWriter out = response.getWriter();
				out.write(content);
				out.flush();
				IOUtils.closeStream(out);
			}
		}
	}

}
