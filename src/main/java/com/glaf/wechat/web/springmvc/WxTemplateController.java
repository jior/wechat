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

package com.glaf.wechat.web.springmvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.*;
import com.glaf.core.config.SystemProperties;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.identity.*;
import com.glaf.core.security.*;
import com.glaf.core.util.*;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.*;
import com.glaf.wechat.util.WechatUtils;

@Controller("/wx/wxTemplate")
@RequestMapping("/wx/wxTemplate")
public class WxTemplateController {
	protected static final Log logger = LogFactory
			.getLog(WxTemplateController.class);

	protected WxTemplateService wxTemplateService;

	protected WxUserTemplateService wxUserTemplateService;

	public WxTemplateController() {

	}

	@ResponseBody
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long id = RequestUtils.getLong(request, "id");
		String ids = request.getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			StringTokenizer token = new StringTokenizer(ids, ",");
			while (token.hasMoreTokens()) {
				String x = token.nextToken();
				if (StringUtils.isNotEmpty(x)) {
					WxTemplate wxTemplate = wxTemplateService
							.getWxTemplate(Long.valueOf(x));
					if (wxTemplate != null
							&& (StringUtils.equals(wxTemplate.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxTemplateService.deleteById(wxTemplate.getId());
					}
				}
			}
		} else if (id != null) {
			WxTemplate wxTemplate = wxTemplateService.getWxTemplate(Long
					.valueOf(id));
			if (wxTemplate != null
					&& (StringUtils.equals(wxTemplate.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxTemplateService.deleteById(wxTemplate.getId());
			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");

		WxTemplate wxTemplate = wxTemplateService.getWxTemplate(RequestUtils
				.getLong(request, "id"));
		if (wxTemplate != null
				&& (StringUtils.equals(wxTemplate.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxTemplate", wxTemplate);
			JSONObject rowJSON = wxTemplate.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxTemplate.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/template/edit", modelMap);
	}

	@RequestMapping("/json/{accountId}")
	@ResponseBody
	public byte[] json(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxTemplateQuery query = new WxTemplateQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);

		String actorId = loginContext.getActorId();
		query.createBy(actorId);
		query.setAccountId(accountId);

		String gridType = ParamUtils.getString(params, "gridType");
		if (gridType == null) {
			gridType = "easyui";
		}
		int start = 0;
		int limit = 10;
		String orderName = null;
		String order = null;

		int pageNo = ParamUtils.getInt(params, "page");
		limit = ParamUtils.getInt(params, "rows");
		start = (pageNo - 1) * limit;
		orderName = ParamUtils.getString(params, "sortName");
		order = ParamUtils.getString(params, "sortOrder");

		if (start < 0) {
			start = 0;
		}

		if (limit <= 0) {
			limit = Paging.DEFAULT_PAGE_SIZE;
		}

		JSONObject result = new JSONObject();
		int total = wxTemplateService.getWxTemplateCountByQueryCriteria(query);
		if (total > 0) {
			result.put("total", total);
			result.put("totalCount", total);
			result.put("totalRecords", total);
			result.put("start", start);
			result.put("startIndex", start);
			result.put("limit", limit);
			result.put("pageSize", limit);

			if (StringUtils.isNotEmpty(orderName)) {
				query.setSortOrder(orderName);
				if (StringUtils.equals(order, "desc")) {
					query.setSortOrder(" desc ");
				}
			}

			List<WxTemplate> list = wxTemplateService
					.getWxTemplatesByQueryCriteria(start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxTemplate wxTemplate : list) {
					JSONObject rowJSON = wxTemplate.toJsonObject();
					rowJSON.put("id", wxTemplate.getId());
					rowJSON.put("wxTemplateId", wxTemplate.getId());
					rowJSON.put("startIndex", ++start);
					rowsJSON.add(rowJSON);
				}

			}
		} else {
			JSONArray rowsJSON = new JSONArray();
			result.put("rows", rowsJSON);
			result.put("total", total);
		}
		return result.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String x_query = request.getParameter("x_query");
		if (StringUtils.equals(x_query, "true")) {
			Map<String, Object> paramMap = RequestUtils
					.getParameterMap(request);
			String x_complex_query = JsonUtils.encode(paramMap);
			x_complex_query = RequestUtils.encodeString(x_complex_query);
			request.setAttribute("x_complex_query", x_complex_query);
		} else {
			request.setAttribute("x_complex_query", "");
		}
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		return new ModelAndView("/wx/template/list", modelMap);
	}

	@RequestMapping("/query/{accountId}")
	public ModelAndView query(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		request.setAttribute("accountId", accountId);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxTemplate.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/template/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxTemplate wxTemplate = new WxTemplate();
		Tools.populate(wxTemplate, params);
		Long accountId = RequestUtils.getLong(request, "accountId");
		wxTemplate.setTemplateType(request.getParameter("templateType"));
		wxTemplate.setSkinImage(request.getParameter("skinImage"));
		wxTemplate.setType(request.getParameter("type"));
		wxTemplate.setPath(request.getParameter("path"));
		wxTemplate.setDefaultFlag(RequestUtils.getInt(request, "defaultFlag"));
		wxTemplate.setCreateBy(actorId);
		wxTemplate.setAccountId(accountId);

		wxTemplateService.save(wxTemplate);

		return this.list(request, modelMap);
	}

	@RequestMapping("/saveUserTemplate")
	public ModelAndView saveUserTemplate(HttpServletRequest request,
			ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		String type = request.getParameter("type");
		if (StringUtils.isEmpty(type)) {
			type = "0";
		}
		Long accountId = RequestUtils.getLong(request, "accountId");
		Long categoryId = RequestUtils.getLong(request, "categoryId", 0);
		Long templateId = RequestUtils.getLong(request, "templateId");
		WxUserTemplate wxUserTemplate = new WxUserTemplate();
		wxUserTemplate.setCategoryId(categoryId);
		wxUserTemplate.setTemplateId(templateId);
		wxUserTemplate.setType(type);
		wxUserTemplate.setCreateBy(loginContext.getActorId());
		wxUserTemplate.setAccountId(accountId);
		wxUserTemplateService.save(wxUserTemplate);

		return this.settings(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxTemplate")
	public byte[] saveWxTemplate(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Long accountId = RequestUtils.getLong(request, "accountId");
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxTemplate wxTemplate = new WxTemplate();
		try {
			Tools.populate(wxTemplate, params);
			wxTemplate.setTemplateType(request.getParameter("templateType"));
			wxTemplate.setSkinImage(request.getParameter("skinImage"));
			wxTemplate.setType(request.getParameter("type"));
			wxTemplate.setPath(request.getParameter("path"));
			wxTemplate.setDefaultFlag(RequestUtils.getInt(request,
					"defaultFlag"));
			wxTemplate.setCreateBy(actorId);
			wxTemplate.setAccountId(accountId);
			this.wxTemplateService.save(wxTemplate);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@RequestMapping("/settings")
	public ModelAndView settings(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String type = request.getParameter("type");
		if (StringUtils.isEmpty(type)) {
			type = "0";
		}

		Long accountId = RequestUtils.getLong(request, "accountId");
		Long categoryId = RequestUtils.getLong(request, "categoryId", 0);

		List<WxTemplate> templates = wxTemplateService.getTemplates(accountId,
				categoryId, type);
		if (templates == null || templates.isEmpty()) {
			templates = wxTemplateService.getTemplates(0L, 0L, type);
		}

		request.setAttribute("templates", templates);

		WxUserTemplate wxUserTemplate = wxUserTemplateService
				.getWxUserTemplate(accountId, categoryId, type);
		request.setAttribute("wxUserTemplate", wxUserTemplate);

		String x_view = ViewProperties.getString("wxTemplate.settings");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		return new ModelAndView("/wx/template/settings", modelMap);
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

	@RequestMapping("/showUpload/{accountId}")
	public ModelAndView showUpload(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		request.setAttribute("accountId", accountId);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxTemplate.showUpload");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/template/showUpload", modelMap);
	}

	@RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id,
			HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxTemplate wxTemplate = wxTemplateService.getWxTemplate(id);
		if (wxTemplate != null
				&& (StringUtils.equals(wxTemplate.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxTemplate.setTemplateType(request.getParameter("templateType"));
			wxTemplate.setSkinImage(request.getParameter("skinImage"));
			wxTemplate.setType(request.getParameter("type"));
			wxTemplate.setPath(request.getParameter("path"));
			wxTemplate.setDefaultFlag(RequestUtils.getInt(request,
					"defaultFlag"));
			wxTemplate.setLastUpdateBy(loginContext.getActorId());
			wxTemplateService.save(wxTemplate);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/upload/{accountId}")
	public ModelAndView upload(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		String actorId = loginContext.getActorId();
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = req.getFileMap();
		Long categoryId = RequestUtils.getLong(req, "categoryId");
		if (categoryId == null) {
			categoryId = 0L;
		}

		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile mFile = entry.getValue();
			if (mFile.getOriginalFilename().endsWith(".zip")
					&& mFile.getSize() > 0) {
				String rand = WechatUtils.getHashedPath(loginContext
						.getActorId());
				String path = "/templates/users/" + rand + "/" + categoryId;
				try {
					FileUtils.mkdirs(SystemProperties.getAppPath() + path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				List<String> excludes = new ArrayList<String>();
				excludes.add("java");
				excludes.add("jsp");
				excludes.add("php");
				excludes.add("asp");
				excludes.add("aspx");
				excludes.add("exe");
				excludes.add("bat");
				excludes.add("cmd");
				excludes.add("dll");
				excludes.add("ocx");
				excludes.add("sh");
				excludes.add("shx");
				excludes.add("db");
				InputStream inputStream = null;
				ZipInputStream zipInputStream = null;
				List<String> includes = new ArrayList<String>();
				includes.add("html");
				try {
					ZipUtils.unzip(mFile.getInputStream(),
							SystemProperties.getAppPath() + path, excludes);
					inputStream = new ByteArrayInputStream(mFile.getBytes());
					zipInputStream = new ZipInputStream(inputStream);
					Map<String, byte[]> zipMap = ZipUtils
							.getZipBytesMap(zipInputStream);
					Set<Entry<String, byte[]>> entrySet2 = zipMap.entrySet();
					for (Entry<String, byte[]> entry2 : entrySet2) {
						String name = entry2.getKey();
						byte[] bytes = entry2.getValue();
						if (name != null && bytes != null) {
							WxTemplate wxTemplate = new WxTemplate();
							wxTemplate.setAccountId(accountId);
							wxTemplate.setCategoryId(categoryId);
							wxTemplate.setCreateBy(actorId);
							wxTemplate.setPath(path + "/" + categoryId + "/"
									+ name);

							if (StringUtils
									.equalsIgnoreCase("index.html", name)) {
								String content = new String(bytes, "UTF-8");
								wxTemplate.setContent(content);
								wxTemplate.setName("首页模版");
								wxTemplate.setType("0");
								wxTemplateService.save(wxTemplate);
							} else if (StringUtils.equalsIgnoreCase(
									"list.html", name)) {
								String content = new String(bytes, "UTF-8");
								wxTemplate.setContent(content);
								wxTemplate.setName("列表页模版");
								wxTemplate.setType("1");
								wxTemplateService.save(wxTemplate);
							} else if (StringUtils.equalsIgnoreCase(
									"detail.html", name)) {
								String content = new String(bytes, "UTF-8");
								wxTemplate.setContent(content);
								wxTemplate.setName("详细页模版");
								wxTemplate.setType("2");
								wxTemplateService.save(wxTemplate);
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					IOUtils.closeStream(zipInputStream);
					IOUtils.closeStream(zipInputStream);
				}
			}
		}
		return this.list(request, modelMap);
	}

	@RequestMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id,
			HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		WxTemplate wxTemplate = wxTemplateService.getWxTemplate(id);
		request.setAttribute("wxTemplate", wxTemplate);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxTemplate.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/template/view");
	}

}
