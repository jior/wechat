package com.glaf.wechat.web.springmvc;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.glaf.base.modules.sys.model.SysApplication;
import com.glaf.base.modules.sys.service.SysApplicationService;
import com.glaf.core.base.TreeModel;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.security.LoginContext;
import com.glaf.core.tree.helper.TreeHelper;
import com.glaf.core.util.RequestUtils;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.service.WxUserService;

@Controller("/wechat/main")
public class WxMainController {

	protected static final Log logger = LogFactory
			.getLog(WxMainController.class);

	protected SysApplicationService sysApplicationService;

	protected WxUserService wxUserService;

	@javax.annotation.Resource
	public void setWxUserService(WxUserService wxUserService) {
		this.wxUserService = wxUserService;
	}

	@RequestMapping("/wechat/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		RequestUtils.setRequestParameterToAttribute(request);
		RequestUtils.setTheme(request, response, "gray");

		long accountId = RequestUtils.getLong(request, "accountId");

		String appCode = request.getParameter("appCode");
		if (StringUtils.isEmpty(appCode)) {
			appCode = "WeChat";
		}

		WxUser wxUser = null;
		if (accountId > 0) {
			wxUser = wxUserService.getWxUser(accountId);
		}

		request.setAttribute("wxUser", wxUser);

		SysApplication app = sysApplicationService.findByCode(appCode);

		TreeModel root = sysApplicationService.getTreeModelByAppId(app.getId());

		List<TreeModel> treeNodes = sysApplicationService.getTreeModels(
				root.getId(), loginContext.getActorId());

		if (treeNodes != null && !treeNodes.isEmpty()) {
			for (TreeModel treeModel : treeNodes) {
				if (StringUtils.isNotEmpty(treeModel.getUrl())) {
					if (StringUtils.contains(treeModel.getUrl(), "?")) {
						treeModel.setUrl(treeModel.getUrl() + "&accountId="
								+ accountId);
					} else {
						treeModel.setUrl(treeModel.getUrl() + "?accountId="
								+ accountId);
					}
				}
			}
			Collections.sort(treeNodes);

			TreeHelper treeHelper = new TreeHelper();
			JSONObject treeJson = treeHelper.getTreeJson(root, treeNodes);
			modelMap.put("treeJson", treeJson);
			logger.debug(treeJson.toJSONString());
		}

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wx.index");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/index", modelMap);
	}

	@RequestMapping("/wechat/main")
	public ModelAndView main(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wx.main");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/main", modelMap);
	}

	@javax.annotation.Resource
	public void setSysApplicationService(
			SysApplicationService sysApplicationService) {
		this.sysApplicationService = sysApplicationService;
	}

}
