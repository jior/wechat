package com.glaf.wechat.web.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.glaf.core.config.ViewProperties;
import com.glaf.core.util.RequestUtils;
import com.glaf.wechat.domain.WxModule;
import com.glaf.wechat.query.WxModuleQuery;
import com.glaf.wechat.service.WxCategoryService;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxModuleService;

@Controller("/wx/wxChoose")
@RequestMapping("/wx/wxChoose")
public class WxChooseController {

	protected static final Log logger = LogFactory
			.getLog(WxChooseController.class);

	protected WxCategoryService wxCategoryService;

	protected WxContentService wxContentService;

	protected WxModuleService wxModuleService;

	public WxChooseController() {

	}

	@RequestMapping("/chooseOne")
	public ModelAndView chooseOne(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

		WxModuleQuery query = new WxModuleQuery();
		query.locked(0);

		List<WxModule> modules = wxModuleService.list(query);
		request.setAttribute("modules", modules);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxChoose.chooseOne");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/common/chooseOne", modelMap);
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
	public void setWxModuleService(WxModuleService wxModuleService) {
		this.wxModuleService = wxModuleService;
	}

}
