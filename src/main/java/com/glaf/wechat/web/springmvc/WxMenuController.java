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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.base.BaseTree;
import com.glaf.core.base.TreeModel;
import com.glaf.core.config.Configuration;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.identity.User;
import com.glaf.core.security.LoginContext;
import com.glaf.core.tree.helper.TreeHelper;
import com.glaf.core.util.JsonUtils;
import com.glaf.core.util.Paging;
import com.glaf.core.util.ParamUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.ResponseUtils;
import com.glaf.core.util.Tools;
import com.glaf.wechat.component.Button;
import com.glaf.wechat.component.Menu;
import com.glaf.wechat.config.WechatCodeProperties;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.WxMenu;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.model.AccessToken;
import com.glaf.wechat.query.WxMenuQuery;
import com.glaf.wechat.service.WxMenuService;
import com.glaf.wechat.service.WxUserService;
import com.glaf.wechat.util.WechatUtils;
import com.glaf.wechat.util.WxIdentityFactory;

@Controller("/wx/wxMenu")
@RequestMapping("/wx/wxMenu")
public class WxMenuController {
	protected static Configuration conf = WechatConfiguration.create();

	protected static final Log logger = LogFactory
			.getLog(WxMenuController.class);

	protected WxMenuService wxMenuService;

	protected WxUserService wxUserService;

	public WxMenuController() {

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
					WxMenu wxMenu = wxMenuService.getWxMenu(Long.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */

					if (wxMenu != null
							&& (StringUtils.equals(wxMenu.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxMenuService.deleteById(wxMenu.getId());
					}
				}
			}
		} else if (id != null) {
			WxMenu wxMenu = wxMenuService.getWxMenu(Long.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */

			if (wxMenu != null
					&& (StringUtils.equals(wxMenu.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxMenuService.deleteById(wxMenu.getId());
			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");

		WxMenu wxMenu = wxMenuService.getWxMenu(RequestUtils.getLong(request,
				"id"));
		if (wxMenu != null
				&& (StringUtils.equals(wxMenu.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			request.setAttribute("wxMenu", wxMenu);
		}

		Long accountId = RequestUtils.getLong(request, "accountId");
		List<WxMenu> topMenus = wxMenuService
				.getMenuList(accountId, "menu", 0L);
		request.setAttribute("topMenus", topMenus);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxMenu.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/menu/edit", modelMap);
	}

	@ResponseBody
	@RequestMapping("/fetchMenuFromServer/{accountId}")
	public byte[] fetchMenuFromServer(
			@PathVariable("accountId") Long accountId,
			HttpServletRequest request) {
		String type = request.getParameter("type");
		String access_token_url = null;
		String menu_get_url = null;
		if (StringUtils.equals("weixin", type)) {
			access_token_url = conf.get("weixin_access_token_url");
			menu_get_url = conf.get("weixin_menu_get_url");
		} else if (StringUtils.equals("yixin", type)) {
			access_token_url = conf.get("yixin_access_token_url");
			menu_get_url = conf.get("yixin_menu_get_url");
		}
		WxUser wxUser = wxUserService.getWxUser(accountId);
		if (wxUser != null) {
			String appId = null;
			String appSecret = null;
			if (StringUtils.equals("weixin", type)) {
				appId = wxUser.getWxAppId();
				appSecret = wxUser.getWxAppSecret();
			} else if (StringUtils.equals("yixin", type)) {
				appId = wxUser.getYxAppId();
				appSecret = wxUser.getYxAppSecret();
			}
			logger.debug("appId:" + appId);
			if (StringUtils.isNotEmpty(appId)
					&& StringUtils.isNotEmpty(appSecret)) {
				AccessToken accessToken = WechatUtils.getAccessToken(
						access_token_url, appId, appSecret);
				if (accessToken != null && accessToken.getToken() != null) {
					JSONObject jsonObject = WechatUtils.getMenu(menu_get_url,
							accessToken.getToken());
					if (jsonObject != null) {
						logger.debug(jsonObject.toJSONString());
						if (jsonObject.containsKey("menu")) {
							JSONObject menuJson = jsonObject
									.getJSONObject("menu");
							if (menuJson.containsKey("button")) {
								List<WxMenu> menus = new java.util.concurrent.CopyOnWriteArrayList<WxMenu>();
								JSONArray buttonArray = menuJson
										.getJSONArray("button");
								for (int i = 0; i < buttonArray.size(); i++) {
									JSONObject buttonJson = buttonArray
											.getJSONObject(i);
									WxMenu menu = this.jsonToMenu(
											wxUser.getActorId(), accountId,
											buttonJson);
									menu.setSort(100 - i);
									menu.setAccountId(accountId);
									menu.setCreateBy(wxUser.getActorId());
									menu.setGroup("menu");
									menus.add(menu);
								}
								wxMenuService.saveAll(menus);
								return ResponseUtils.responseJsonResult(true);
							}
						}
					}
				}
			}
		}
		return ResponseUtils.responseJsonResult(false);
	}

	public WxUserService getWxUserService() {
		return wxUserService;
	}

	@RequestMapping("/json/{accountId}")
	@ResponseBody
	public byte[] json(@PathVariable("accountId") Long accountId,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxMenuQuery query = new WxMenuQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);
		String actorId = loginContext.getActorId();
		query.createBy(actorId);
		query.setAccountId(accountId);

		Long parentId = RequestUtils.getLong(request, "parentId", 0);
		query.parentId(parentId);

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
		int total = wxMenuService.getWxMenuCountByQueryCriteria(query);
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

			List<WxMenu> list = wxMenuService.getWxMenusByQueryCriteria(start,
					limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxMenu wxMenu : list) {
					JSONObject rowJSON = wxMenu.toJsonObject();
					rowJSON.put("id", wxMenu.getId());
					rowJSON.put("pId", wxMenu.getParentId());
					rowJSON.put("menuId", wxMenu.getId());
					rowJSON.put("wxMenuId", wxMenu.getId());
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

	protected WxMenu jsonToMenu(String createBy, Long accountId,
			JSONObject buttonJson) {
		WxMenu menu = new WxMenu();
		menu.setName(buttonJson.getString("name"));
		menu.setType(buttonJson.getString("type"));
		if (buttonJson.containsKey("key")) {
			menu.setKey(buttonJson.getString("key"));
		}
		if (buttonJson.containsKey("url")) {
			menu.setUrl(buttonJson.getString("url"));
		}
		if (buttonJson.containsKey("sub_button")) {
			JSONArray subButtonArray = buttonJson.getJSONArray("sub_button");
			for (int i = 0; i < subButtonArray.size(); i++) {
				JSONObject subButtonJson = subButtonArray.getJSONObject(i);
				WxMenu m = new WxMenu();
				m.setName(subButtonJson.getString("name"));
				m.setType(subButtonJson.getString("type"));
				if (subButtonJson.containsKey("key")) {
					m.setKey(subButtonJson.getString("key"));
				}
				if (subButtonJson.containsKey("url")) {
					m.setUrl(subButtonJson.getString("url"));
				}
				m.setSort(100 - i);
				m.setAccountId(accountId);
				m.setCreateBy(createBy);
				m.setGroup("menu");
				menu.addChild(m);
			}
		}
		return menu;
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

		String requestURI = request.getRequestURI();
		if (request.getQueryString() != null) {
			request.setAttribute(
					"fromUrl",
					RequestUtils.encodeURL(requestURI + "?"
							+ request.getQueryString()));
		} else {
			request.setAttribute("fromUrl", RequestUtils.encodeURL(requestURI));
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		return new ModelAndView("/wx/menu/list", modelMap);
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
		String x_view = ViewProperties.getString("wxMenu.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/menu/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMenu wxMenu = new WxMenu();
		Tools.populate(wxMenu, params);
		Long accountId = RequestUtils.getLong(request, "accountId");
		wxMenu.setParentId(RequestUtils.getLong(request, "parentId"));
		wxMenu.setSort(RequestUtils.getInt(request, "sort"));
		wxMenu.setIcon(request.getParameter("icon"));
		wxMenu.setIconCls(request.getParameter("iconCls"));
		wxMenu.setLocked(RequestUtils.getInt(request, "locked"));
		wxMenu.setName(request.getParameter("name"));
		wxMenu.setType(request.getParameter("type"));
		wxMenu.setGroup(request.getParameter("group"));
		wxMenu.setUrl(request.getParameter("url"));
		wxMenu.setPicUrl(request.getParameter("picUrl"));
		wxMenu.setDesc(request.getParameter("desc"));
		wxMenu.setCreateBy(actorId);
		wxMenu.setLastUpdateBy(actorId);
		wxMenu.setAccountId(accountId);

		wxMenuService.save(wxMenu);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxMenu")
	public byte[] saveWxMenu(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxMenu wxMenu = new WxMenu();
		try {
			Tools.populate(wxMenu, params);
			wxMenu.setParentId(RequestUtils.getLong(request, "parentId"));
			wxMenu.setSort(RequestUtils.getInt(request, "sort"));
			wxMenu.setIcon(request.getParameter("icon"));
			wxMenu.setIconCls(request.getParameter("iconCls"));
			wxMenu.setLocked(RequestUtils.getInt(request, "locked"));
			wxMenu.setName(request.getParameter("name"));
			wxMenu.setType(request.getParameter("type"));
			wxMenu.setGroup(request.getParameter("group"));
			wxMenu.setUrl(request.getParameter("url"));
			wxMenu.setPicUrl(request.getParameter("picUrl"));
			wxMenu.setDesc(request.getParameter("desc"));
			wxMenu.setCreateBy(actorId);
			wxMenu.setLastUpdateBy(actorId);
			wxMenu.setAccountId(accountId);
			this.wxMenuService.save(wxMenu);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxMenuService(WxMenuService wxMenuService) {
		this.wxMenuService = wxMenuService;
	}

	@javax.annotation.Resource
	public void setWxUserService(WxUserService wxUserService) {
		this.wxUserService = wxUserService;
	}

	@ResponseBody
	@RequestMapping("/syncServer/{accountId}")
	public byte[] syncServer(@PathVariable("accountId") Long accountId,
			HttpServletRequest request) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			String type = request.getParameter("type");
			String access_token_url = null;
			String menu_create_url = null;
			if (StringUtils.equals("weixin", type)) {
				access_token_url = conf.get("weixin_access_token_url");
				menu_create_url = conf.get("weixin_menu_create_url");
			} else if (StringUtils.equals("yixin", type)) {
				access_token_url = conf.get("yixin_access_token_url");
				menu_create_url = conf.get("yixin_menu_create_url");
			}

			List<WxMenu> menus = wxMenuService.getMenuList(accountId, "menu",
					0L);

			if (menus != null && !menus.isEmpty()) {
				WxUser wxUser = wxUserService.getWxUser(accountId);
				if (wxUser != null) {
					String appId = null;
					String appSecret = null;
					if (StringUtils.equals("weixin", type)) {
						appId = wxUser.getWxAppId();
						appSecret = wxUser.getWxAppSecret();
					} else if (StringUtils.equals("yixin", type)) {
						appId = wxUser.getYxAppId();
						appSecret = wxUser.getYxAppSecret();
					}
					logger.debug("appId:" + appId);
					if (StringUtils.isNotEmpty(appId)
							&& StringUtils.isNotEmpty(appSecret)) {
						AccessToken accessToken = WechatUtils.getAccessToken(
								access_token_url, appId, appSecret);
						if (accessToken != null
								&& accessToken.getToken() != null) {
							Menu menu = new Menu();
							for (int i = 0; i < menus.size(); i++) {
								WxMenu wxm = menus.get(i);
								if (wxm.getLocked() != 0) {
									continue;
								}

								Button button = new Button();
								button.setKey(wxm.getKey());
								button.setName(wxm.getName());
								button.setType(wxm.getType());
								if (StringUtils.equals(wxm.getType(), "view")) {
									button.setUrl(wxm.getUrl());
									if (StringUtils.startsWith(button.getUrl(),
											"/website/wx/")) {
										String url = WechatUtils
												.getServiceUrl(request)
												+ button.getUrl();
										button.setUrl(url);
									}
								}

								List<WxMenu> childrenMenus = wxMenuService
										.getMenuList(accountId, wxm.getId());
								for (int j = 0; j < childrenMenus.size(); j++) {
									WxMenu m = childrenMenus.get(j);
									if (m.getLocked() != 0) {
										continue;
									}

									Button b = new Button();
									b.setKey(m.getKey());
									b.setName(m.getName());
									b.setType(m.getType());
									if (StringUtils.equals(m.getType(), "view")) {
										b.setUrl(m.getUrl());
										if (StringUtils.startsWith(b.getUrl(),
												"/website/wx/")) {
											String url = WechatUtils
													.getServiceUrl(request)
													+ b.getUrl();
											b.setUrl(url);
										}
									}
									button.addChild(b);

									if (button.getChildren().size() >= 5) {
										break;
									}
								}

								menu.addButton(button);

								if (menu.getButtons().size() >= 3) {
									break;
								}
							}

							logger.debug("prepare create menu...");
							String jsonMenu = menu.toJSONObject()
									.toJSONString();
							logger.debug(jsonMenu);

							int result = WechatUtils.createMenu(
									menu_create_url, menu,
									accessToken.getToken());
							logger.debug("result=" + result);
							if (result == 0) {
								logger.debug("成功同步菜单到微信服务器。");
								return ResponseUtils.responseJsonResult(true);
							} else {
								String message = WechatCodeProperties
										.getString("" + result);
								if (message != null) {
									return ResponseUtils.responseJsonResult(
											false, message);
								}
							}
						}
					}
				}
			}
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@ResponseBody
	@RequestMapping("/treeJson/{accountId}")
	public byte[] treeJson(@PathVariable("accountId") Long accountId,
			HttpServletRequest request) throws IOException {
		JSONArray array = new JSONArray();
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		logger.debug("accountId:"+accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			String group = request.getParameter("group");
			Long parentId = RequestUtils.getLong(request, "parentId", 0);
			List<WxMenu> menus = null;
			if (parentId != null && parentId > 0) {
				menus = wxMenuService.getMenuList(accountId, parentId);
			} else if (StringUtils.isNotEmpty(group)) {
				menus = wxMenuService.getMenuList(accountId, group);
			}

			if (menus != null && !menus.isEmpty()) {
				Map<Long, TreeModel> treeMap = new java.util.concurrent.ConcurrentHashMap<Long, TreeModel>();
				List<TreeModel> treeModels = new java.util.concurrent.CopyOnWriteArrayList<TreeModel>();
				List<Long> menuIds = new java.util.concurrent.CopyOnWriteArrayList<Long>();
				for (WxMenu menu : menus) {
					TreeModel tree = new BaseTree();
					tree.setId(menu.getId());
					tree.setParentId(menu.getParentId());
					tree.setCode(menu.getKey());
					tree.setName(menu.getName());
					tree.setSortNo(menu.getSort());
					tree.setDescription(menu.getDesc());
					tree.setCreateBy(menu.getCreateBy());
					tree.setIconCls("tree_folder");
					tree.setTreeId(menu.getTreeId());
					// tree.setUrl(menu.getUrl());
					treeModels.add(tree);
					menuIds.add(menu.getId());
					treeMap.put(menu.getId(), tree);
				}
				logger.debug("treeModels:" + treeModels.size());
				TreeHelper treeHelper = new TreeHelper();
				JSONArray jsonArray = treeHelper.getTreeJSONArray(treeModels);
				logger.debug(jsonArray.toJSONString());
				return jsonArray.toJSONString().getBytes("UTF-8");
			}
		}
		return array.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id,
			HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxMenu wxMenu = wxMenuService.getWxMenu(id);
		if (wxMenu != null
				&& (StringUtils.equals(wxMenu.getCreateBy(),
						loginContext.getActorId()) || loginContext
						.isSystemAdministrator())) {
			wxMenu.setParentId(RequestUtils.getLong(request, "parentId"));
			wxMenu.setSort(RequestUtils.getInt(request, "sort"));
			wxMenu.setIcon(request.getParameter("icon"));
			wxMenu.setIconCls(request.getParameter("iconCls"));
			wxMenu.setLocked(RequestUtils.getInt(request, "locked"));
			wxMenu.setName(request.getParameter("name"));
			wxMenu.setKey(request.getParameter("key"));
			wxMenu.setType(request.getParameter("type"));
			wxMenu.setGroup(request.getParameter("group"));
			wxMenu.setUrl(request.getParameter("url"));
			wxMenu.setPicUrl(request.getParameter("picUrl"));
			wxMenu.setDesc(request.getParameter("desc"));
			wxMenu.setLastUpdateBy(loginContext.getActorId());
			wxMenuService.save(wxMenu);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id,
			HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		WxMenu wxMenu = wxMenuService.getWxMenu(id);
		request.setAttribute("wxMenu", wxMenu);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxMenu.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/menu/view");
	}

}
