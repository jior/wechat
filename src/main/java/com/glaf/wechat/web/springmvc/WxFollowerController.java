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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.config.Configuration;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.security.LoginContext;
import com.glaf.core.util.JsonUtils;
import com.glaf.core.util.Paging;
import com.glaf.core.util.ParamUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.ResponseUtils;
import com.glaf.core.util.Tools;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.WxFollower;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.model.AccessToken;
import com.glaf.wechat.query.WxFollowerQuery;
import com.glaf.wechat.service.WxFollowerService;
import com.glaf.wechat.service.WxUserService;
import com.glaf.wechat.util.WechatUtils;
import com.glaf.wechat.util.WxFollowerThread;
import com.glaf.wechat.util.WxIdentityFactory;

@Controller("/wx/wxFollower")
@RequestMapping("/wx/wxFollower")
public class WxFollowerController {
	protected static final Log logger = LogFactory
			.getLog(WxFollowerController.class);

	protected static Configuration conf = WechatConfiguration.create();

	protected WxFollowerService wxFollowerService;

	protected WxUserService wxUserService;

	public WxFollowerController() {

	}

	protected void addFollower(Long accountId, String actorId,
			String subscribe_list_get_url, String subscribe_get_url,
			String token, JSONObject jsonObject) {
		JSONObject json = jsonObject.getJSONObject("data");
		if (json.containsKey("openid")) {
			JSONArray array = json.getJSONArray("openid");
			if (array != null && array.size() > 0) {
				for (int i = 0, len = array.size(); i < len; i++) {
					JSONObject o = array.getJSONObject(i);
					String openid = o.toString();
					logger.debug("openid:" + openid);
					WxFollowerThread thread = new WxFollowerThread(accountId,
							actorId, subscribe_get_url, token, openid);
					com.glaf.core.util.threads.ThreadFactory.run(thread);
				}
			}
		}
		if (jsonObject.containsKey("next_openid")) {
			String next_openid = jsonObject.getString("next_openid");
			JSONObject next_jsonObject = WechatUtils.getFollowers(
					subscribe_list_get_url, token, next_openid);
			if (next_jsonObject.containsKey("data")) {
				this.addFollower(accountId, actorId, subscribe_list_get_url,
						subscribe_get_url, token, next_jsonObject);
			}
		}
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
					WxFollower wxFollower = wxFollowerService
							.getWxFollower(Long.valueOf(x));
					if (wxFollower != null
							&& (StringUtils.equals(wxFollower.getActorId(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {
						wxFollowerService.deleteById(wxFollower.getId());
					}
				}
			}
		} else if (id != null) {
			WxFollower wxFollower = wxFollowerService.getWxFollower(Long
					.valueOf(id));
			if (wxFollower != null
					&& (StringUtils.equals(wxFollower.getActorId(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {
				wxFollowerService.deleteById(wxFollower.getId());
			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			WxFollower wxFollower = wxFollowerService
					.getWxFollower(RequestUtils.getLong(request, "id"));
			if (wxFollower != null) {
				request.setAttribute("wxFollower", wxFollower);
			}
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxFollower.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/follower/edit", modelMap);
	}

	@ResponseBody
	@RequestMapping("/fetchFollower")
	public byte[] fetchFollower(HttpServletRequest request) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		String actorId = loginContext.getActorId();
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			try {
				String type = request.getParameter("type");
				String access_token_url = null;
				String subscribe_list_get_url = null;
				String subscribe_get_url = null;
				if (StringUtils.equals("weixin", type)) {
					access_token_url = conf.get("weixin_access_token_url");
					subscribe_list_get_url = conf
							.get("weixin_subscribe_list_get_url");
					subscribe_get_url = conf.get("weixin_subscribe_get_url");
				} else if (StringUtils.equals("yixin", type)) {
					access_token_url = conf.get("yixin_access_token_url");
					subscribe_list_get_url = conf
							.get("yixin_subscribe_list_get_url");
					subscribe_get_url = conf.get("yixin_subscribe_get_url");
				}
				logger.debug(subscribe_list_get_url);
				logger.debug(subscribe_get_url);
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
							JSONObject jsonObject = WechatUtils.getFollowers(
									subscribe_list_get_url,
									accessToken.getToken(), "");
							if (jsonObject.containsKey("data")) {
								this.addFollower(accountId, actorId,
										subscribe_list_get_url,
										subscribe_get_url,
										accessToken.getToken(), jsonObject);
							}
						}
					}
				}

				return ResponseUtils.responseJsonResult(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
			}
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxFollowerQuery query = new WxFollowerQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);
		/**
		 * 此处业务逻辑需自行调整
		 */
		if (!loginContext.isSystemAdministrator()) {
			String actorId = loginContext.getActorId();
			query.createBy(actorId);
		}

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
		int total = wxFollowerService.getWxFollowerCountByQueryCriteria(query);
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

			List<WxFollower> list = wxFollowerService
					.getWxFollowersByQueryCriteria(start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxFollower wxFollower : list) {
					JSONObject rowJSON = wxFollower.toJsonObject();
					rowJSON.put("id", wxFollower.getId());
					rowJSON.put("followerId", wxFollower.getId());
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

		return new ModelAndView("/wx/follower/list", modelMap);
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxFollower.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/follower/query", modelMap);
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);

		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			WxFollower wxFollower = new WxFollower();
			Tools.populate(wxFollower, params);

			wxFollower.setAccountId(RequestUtils.getLong(request, "accountId"));

			wxFollower.setMobile(request.getParameter("mobile"));
			wxFollower.setMail(request.getParameter("mail"));
			wxFollower.setTelephone(request.getParameter("telephone"));

			wxFollower.setLocked(RequestUtils.getInt(request, "locked"));
			wxFollower.setRemark(request.getParameter("remark"));

			wxFollowerService.save(wxFollower);
		}

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxFollower")
	public byte[] saveWxFollower(HttpServletRequest request) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			WxFollower wxFollower = new WxFollower();
			try {
				Tools.populate(wxFollower, params);
				wxFollower.setAccountId(RequestUtils.getLong(request,
						"accountId"));

				wxFollower.setMobile(request.getParameter("mobile"));
				wxFollower.setMail(request.getParameter("mail"));
				wxFollower.setTelephone(request.getParameter("telephone"));

				wxFollower.setLocked(RequestUtils.getInt(request, "locked"));
				wxFollower.setRemark(request.getParameter("remark"));

				this.wxFollowerService.save(wxFollower);

				return ResponseUtils.responseJsonResult(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
			}
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@javax.annotation.Resource
	public void setWxFollowerService(WxFollowerService wxFollowerService) {
		this.wxFollowerService = wxFollowerService;
	}

	@javax.annotation.Resource
	public void setWxUserService(WxUserService wxUserService) {
		this.wxUserService = wxUserService;
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			WxFollower wxFollower = wxFollowerService
					.getWxFollower(RequestUtils.getLong(request, "id"));

			wxFollower.setMobile(request.getParameter("mobile"));
			wxFollower.setMail(request.getParameter("mail"));
			wxFollower.setTelephone(request.getParameter("telephone"));

			wxFollower.setLocked(RequestUtils.getInt(request, "locked"));
			wxFollower.setRemark(request.getParameter("remark"));

			wxFollowerService.save(wxFollower);
		}

		return this.list(request, modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long accountId = RequestUtils.getLong(request, "accountId");
		WxUser user = WxIdentityFactory.getUserByAccountId(accountId);
		if (StringUtils.equals(loginContext.getActorId(), user.getActorId())) {
			WxFollower wxFollower = wxFollowerService
					.getWxFollower(RequestUtils.getLong(request, "id"));
			if (wxFollower != null) {
				request.setAttribute("wxFollower", wxFollower);
			}
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxFollower.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/follower/view");
	}

}
