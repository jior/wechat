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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glaf.core.freemarker.TemplateUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.wechat.domain.WxCategory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.domain.WxTemplate;
import com.glaf.wechat.domain.WxUserTemplate;
import com.glaf.wechat.service.WxCategoryService;
import com.glaf.wechat.service.WxConfigService;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxCoverService;
import com.glaf.wechat.service.WxFileService;
import com.glaf.wechat.service.WxMenuService;
import com.glaf.wechat.service.WxSiteInfoService;
import com.glaf.wechat.service.WxTemplateService;
import com.glaf.wechat.service.WxUserTemplateService;

@Controller("/wx/content")
@RequestMapping("/wx/content")
public class WxPublicContentController {

	protected static final Log logger = LogFactory
			.getLog(WxPublicContentController.class);

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
	@RequestMapping("/detail/{customer}/{uuid}")
	public void detail(@PathVariable("customer") String customer,
			@PathVariable("uuid") String uuid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		WxContent wxContent = null;
		if (StringUtils.isNotEmpty(uuid)) {
			wxContent = wxContentService.getWxContentByUUIDWithRefs(uuid);
		}
		if (wxContent != null) {
			Long categoryId = wxContent.getCategoryId();
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(customer, "2", categoryId);
			if (wxUserTemplate == null) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						customer, "2", 0L);
			}
			if (wxUserTemplate == null) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						"system", "2", 0L);
			}
			Long templateId = wxUserTemplate.getTemplateId();
			WxTemplate template = wxTemplateService.getWxTemplate(templateId);
			if (template != null && template.getContent() != null) {
				String serviceUrl = "http://" + request.getServerName() + ":"
						+ request.getServerPort();
				Map<String, Object> context = RequestUtils
						.getParameterMap(request);
				context.put("customer", customer);
				context.put("content", wxContent);
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);
				String content = TemplateUtils.process(context,
						template.getContent());
				response.getWriter().write(content);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/index/{customer}")
	public void index(@PathVariable("customer") String customer,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Long categoryId = RequestUtils.getLong(request, "categoryId", 0);
		WxUserTemplate wxUserTemplate = wxUserTemplateService
				.getWxUserTemplate(customer, "0", categoryId);
		if (wxUserTemplate == null) {
			wxUserTemplate = wxUserTemplateService.getWxUserTemplate("system",
					"0", 0L);
		}
		if (wxUserTemplate != null) {
			Long templateId = wxUserTemplate.getTemplateId();
			WxTemplate template = wxTemplateService.getWxTemplate(templateId);
			if (template != null && template.getContent() != null) {
				String serviceUrl = "http://" + request.getServerName() + ":"
						+ request.getServerPort();
				Map<String, Object> context = RequestUtils
						.getParameterMap(request);
				context.put("customer", customer);
				context.put("template", template);
				context.put("contextPath", request.getContextPath());
				context.put("serviceUrl", serviceUrl);
				context.put("serverUrl", serviceUrl);
				String content = TemplateUtils.process(context,
						template.getContent());
				response.getWriter().write(content);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/list/{categoryId}")
	public void list(@PathVariable("categoryId") Long categoryId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		WxCategory category = wxCategoryService.getWxCategory(categoryId);
		if (category != null) {
			WxUserTemplate wxUserTemplate = wxUserTemplateService
					.getWxUserTemplate(categoryId, "1");
			if (wxUserTemplate == null) {
				wxUserTemplate = wxUserTemplateService.getWxUserTemplate(
						"system", "1", 0L);
			}
			if (wxUserTemplate != null) {
				Long templateId = wxUserTemplate.getTemplateId();
				WxTemplate template = wxTemplateService
						.getWxTemplate(templateId);
				if (template != null && template.getContent() != null) {
					String serviceUrl = "http://" + request.getServerName()
							+ ":" + request.getServerPort();
					Map<String, Object> context = RequestUtils
							.getParameterMap(request);
					context.put("customer", category.getCreateBy());
					context.put("template", template);
					context.put("contextPath", request.getContextPath());
					context.put("serviceUrl", serviceUrl);
					context.put("serverUrl", serviceUrl);
					String content = TemplateUtils.process(context,
							template.getContent());
					response.getWriter().write(content);
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

}
