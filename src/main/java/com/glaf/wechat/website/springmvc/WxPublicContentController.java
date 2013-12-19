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
import java.util.Date;
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

import com.glaf.core.cache.CacheFactory;
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
import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.domain.WxTemplate;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.domain.WxUserTemplate;
import com.glaf.wechat.query.WxCategoryQuery;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.WxCategoryService;

import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxCoverService;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.service.WxLogService;
import com.glaf.wechat.service.WxMenuService;
import com.glaf.wechat.service.WxSiteInfoService;
import com.glaf.wechat.service.WxTemplateService;
import com.glaf.wechat.service.WxUserTemplateService;
import com.glaf.wechat.util.WechatUtils;
import com.glaf.wechat.util.WxIdentityFactory;
import com.glaf.wechat.util.WxLogFactory;

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

	protected WxSiteInfoService wxSiteInfoService;

	protected WxLogService wxLogService;

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
		String cacheKey = "website_detail_" + uuid;
		if (CacheFactory.getString(cacheKey) != null) {
			String content = CacheFactory.getString(cacheKey);
			PrintWriter out = response.getWriter();
			out.write(content);
			out.flush();
			IOUtils.closeStream(out);
			try {
				WxLog bean = new WxLog();
				bean.setCreateTime(new Date());
				bean.setFlag(1001);
				bean.setIp(RequestUtils.getIPAddress(request));
				bean.setOperate(uuid);
				WxLogFactory.create(bean);
			} catch (Exception ex) {
			}
			return;
		}
		WxContent wxContent = null;
		if (StringUtils.isNotEmpty(uuid)) {
			wxContent = wxContentService.getWxContentByUUIDWithRefs(uuid);
		}
		if (wxContent != null) {
			Long accountId = wxContent.getAccountId();
			Long categoryId = wxContent.getCategoryId();
			String actorId = wxContent.getCreateBy();
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(accountId, categoryId, "2");
			if (wxUserTemplate == null) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						accountId, 0L, "2");
			}
			if (wxUserTemplate == null
					|| wxUserTemplate.getTemplateId() == null
					|| wxUserTemplate.getTemplateId() == 0) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(0L,
						0L, "2");
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

				context.put("userId", String.valueOf(user.getId()));
				context.put("actorId", actorId);
				context.put("accountId", accountId);
				context.put("actorIdMD5Hex", DigestUtils.md5Hex(actorId));
				context.put("content", wxContent);
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);

				WxCategoryQuery query3 = new WxCategoryQuery();
				query3.createBy(actorId);
				query3.accountId(accountId);
				query3.parentId(0L);
				query3.type("category");
				query3.locked(0);
				query3.indexShow(1);
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
					context.put("categories", list3);
				}

				context.put("category", category);

				String content = TemplateUtils.process(context,
						template.getContent());
				if (cache) {
					CacheFactory.put(cacheKey, content);
				}

				PrintWriter out = response.getWriter();
				out.write(content);
				out.flush();
				IOUtils.closeStream(out);

				try {
					WxLog bean = new WxLog();
					bean.setActorId(actorId);
					bean.setAccountId(accountId);
					bean.setCreateTime(new Date());
					bean.setFlag(1002);
					bean.setIp(RequestUtils.getIPAddress(request));
					bean.setOperate(uuid);
					WxLogFactory.create(bean);
				} catch (Exception ex) {
				}
			}
		}
	}

	@ResponseBody
	@RequestMapping("/index/{accountId}")
	public void index(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String cacheKey = "website_index_" + accountId;
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (CacheFactory.getString(cacheKey) != null) {
			String content = CacheFactory.getString(cacheKey);
			PrintWriter out = response.getWriter();
			out.write(content);
			out.flush();
			IOUtils.closeStream(out);
			try {
				WxLog bean = new WxLog();
				bean.setActorId(user.getActorId());
				bean.setAccountId(accountId);
				bean.setCreateTime(new Date());
				bean.setFlag(10001);
				bean.setIp(RequestUtils.getIPAddress(request));
				WxLogFactory.create(bean);
			} catch (Exception ex) {
			}
			return;
		}

		Long categoryId = RequestUtils.getLong(request, "categoryId", 0);
		String actorId = user.getActorId();
		WxUserTemplate wxUserTemplate = wxUserTemplateService
				.getWxUserTemplate(accountId, categoryId, "0");
		if (wxUserTemplate == null || wxUserTemplate.getTemplateId() == null
				|| wxUserTemplate.getTemplateId() == 0) {
			wxUserTemplate = wxUserTemplateService.getWxUserTemplate(0L, 0L,
					"0");
		}
		if (wxUserTemplate != null) {
			Long templateId = wxUserTemplate.getTemplateId();
			boolean cache = conf.getBoolean("wx_template_cache", true);
			WxTemplate template = wxTemplateService.getWxTemplate(templateId,
					cache);
			logger.debug("cache:" + cache);
			logger.debug("templateId:" + templateId);
			if (template != null && template.getContent() != null) {
				String serviceUrl = WechatUtils.getServiceUrl(request);
				Map<String, Object> context = RequestUtils
						.getParameterMap(request);

				context.put("userId", String.valueOf(user.getId()));
				context.put("actorId", actorId);
				context.put("accountId", accountId);
				context.put("actorIdMD5Hex", DigestUtils.md5Hex(actorId));
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);

				WxContentQuery query = new WxContentQuery();
				query.accountId(accountId);
				query.categoryId(0L);
				query.type("PPT");
				query.status(1);
				List<WxContent> list = wxContentService.list(query);
				if (list != null && !list.isEmpty()) {
					logger.debug("index ppt size:" + list.size());
					for (WxContent c : list) {
						if (StringUtils.isNotEmpty(c.getUrl())) {
							if (StringUtils.startsWith(c.getUrl(), "/mx/wx/")) {
								c.setUrl(serviceUrl + c.getUrl());
							}
							if (StringUtils.startsWith(c.getUrl(),
									"/website/wx/")) {
								c.setUrl(serviceUrl + c.getUrl());
							}
						}
					}
					context.put("pptList", list);
				}

				WxCategoryQuery query3 = new WxCategoryQuery();
				query3.setAccountId(accountId);
				query3.createBy(actorId);
				query3.parentId(0L);
				query3.type("category");
				query3.locked(0);
				query3.indexShow(1);
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
					context.put("categories", list3);
				}

				String content = TemplateUtils.process(context,
						template.getContent());
				if (cache) {
					CacheFactory.put(cacheKey, content);
				}
				PrintWriter out = response.getWriter();
				out.write(content);
				out.flush();
				IOUtils.closeStream(out);
				try {
					WxLog bean = new WxLog();
					bean.setActorId(actorId);
					bean.setAccountId(accountId);
					bean.setCreateTime(new Date());
					bean.setFlag(10002);
					bean.setIp(RequestUtils.getIPAddress(request));
					WxLogFactory.create(bean);
				} catch (Exception ex) {
				}
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
		String cacheKey = "website_list_" + categoryId;
		WxCategory category = wxCategoryService.getWxCategory(categoryId);
		if (CacheFactory.getString(cacheKey) != null) {
			String content = CacheFactory.getString(cacheKey);
			PrintWriter out = response.getWriter();
			out.write(content);
			out.flush();
			IOUtils.closeStream(out);

			try {
				WxLog bean = new WxLog();
				if (category != null) {
					bean.setActorId(category.getCreateBy());
					bean.setAccountId(category.getAccountId());
				}
				bean.setCreateTime(new Date());
				bean.setFlag(5001);
				bean.setIp(RequestUtils.getIPAddress(request));
				bean.setOperate(String.valueOf(categoryId));
				WxLogFactory.create(bean);
			} catch (Exception ex) {
			}

			return;
		}

		if (category != null) {
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(categoryId, "1");
			if (wxUserTemplate == null
					|| wxUserTemplate.getTemplateId() == null
					|| wxUserTemplate.getTemplateId() == 0) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(0L,
						0L, "1");
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

					context.put("userId", String.valueOf(user.getId()));
					context.put("accountId", category.getAccountId());
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
					query.accountId(category.getAccountId());
					query.categoryId(categoryId);
					query.status(1);
					query.type("P");

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
					query2.accountId(category.getAccountId());
					query2.categoryId(categoryId);
					query2.type("PPT");
					query2.status(1);

					List<WxContent> list = wxContentService.list(query2);
					if (list != null && !list.isEmpty()) {
						logger.debug(" ppt size:" + list.size());
						for (WxContent c : list) {
							if (StringUtils.isNotEmpty(c.getUrl())) {
								if (StringUtils.startsWith(c.getUrl(),
										"/mx/wx/")) {
									c.setUrl(serviceUrl + c.getUrl());
								}
								if (StringUtils.startsWith(c.getUrl(),
										"/website/wx/")) {
									c.setUrl(serviceUrl + c.getUrl());
								}
							}
						}
						context.put("pptList", list);
					}

					context.put("category", category);

					WxCategoryQuery query3 = new WxCategoryQuery();
					query3.accountId(category.getAccountId());
					query3.parentId(0L);
					query3.type("category");
					query3.locked(0);
					query3.indexShow(1);
					List<WxCategory> list3 = wxCategoryService.list(query3);
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
						context.put("categories", list3);
					}

					WxCategoryQuery query4 = new WxCategoryQuery();
					query4.accountId(category.getAccountId());
					query4.parentId(category.getId());
					query4.type("category");
					query4.locked(0);
					query4.indexShow(1);
					List<WxCategory> list4 = wxCategoryService.list(query4);
					if (list4 != null && !list4.isEmpty()) {
						for (WxCategory cat : list4) {
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
						context.put("subCategories", list4);
					}

					String content = TemplateUtils.process(context,
							template.getContent());
					if (cache) {
						CacheFactory.put(cacheKey, content);
					}
					PrintWriter out = response.getWriter();
					out.write(content);
					out.flush();
					IOUtils.closeStream(out);

					try {
						WxLog bean = new WxLog();
						bean.setActorId(actorId);
						bean.setAccountId(category.getAccountId());
						bean.setCreateTime(new Date());
						bean.setFlag(5002);
						bean.setIp(RequestUtils.getIPAddress(request));
						bean.setOperate(String.valueOf(categoryId));
						WxLogFactory.create(bean);
					} catch (Exception ex) {
					}
				}
			}
		}
	}

	@javax.annotation.Resource
	public void setWxCategoryService(WxCategoryService wxCategoryService) {
		this.wxCategoryService = wxCategoryService;
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
	public void setWxLogService(WxLogService wxLogService) {
		this.wxLogService = wxLogService;
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
		String cacheKey = "website_detail_" + id;
		if (CacheFactory.getString(cacheKey) != null) {
			String content = CacheFactory.getString(cacheKey);
			PrintWriter out = response.getWriter();
			out.write(content);
			out.flush();
			IOUtils.closeStream(out);
			try {
				WxLog bean = new WxLog();
				bean.setCreateTime(new Date());
				bean.setFlag(2001);
				bean.setIp(RequestUtils.getIPAddress(request));
				bean.setOperate(String.valueOf(id));
				WxLogFactory.create(bean);
			} catch (Exception ex) {
			}
			return;
		}
		WxContent wxContent = null;
		if (id != null && id > 0) {
			wxContent = wxContentService.getWxContentWithRefs(id);
		}
		if (wxContent != null) {
			String actorId = wxContent.getCreateBy();
			Long accountId = wxContent.getAccountId();
			Long categoryId = wxContent.getCategoryId();
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(accountId, categoryId, "2");
			if (wxUserTemplate == null
					|| wxUserTemplate.getTemplateId() == null
					|| wxUserTemplate.getTemplateId() == 0) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						accountId, 0L, "2");
			}
			if (wxUserTemplate == null) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(0L,
						0L, "2");
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

				context.put("userId", String.valueOf(user.getId()));
				context.put("accountId", accountId);
				context.put("actorId", actorId);
				context.put("actorIdMD5Hex", DigestUtils.md5Hex(actorId));
				context.put("content", wxContent);
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);

				WxCategoryQuery query3 = new WxCategoryQuery();
				query3.accountId(accountId);
				query3.parentId(0L);
				query3.type("category");
				query3.locked(0);
				query3.indexShow(1);
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
				if (cache) {
					CacheFactory.put(cacheKey, content);
				}

				PrintWriter out = response.getWriter();
				out.write(content);
				out.flush();
				IOUtils.closeStream(out);

				try {
					WxLog bean = new WxLog();
					bean.setActorId(actorId);
					bean.setAccountId(accountId);
					bean.setCreateTime(new Date());
					bean.setFlag(2002);
					bean.setIp(RequestUtils.getIPAddress(request));
					bean.setOperate(String.valueOf(id));
					WxLogFactory.create(bean);
				} catch (Exception ex) {
				}
			}
		}
	}

}
