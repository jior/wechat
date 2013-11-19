package com.glaf.wechat.web.springmvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.glaf.base.modules.Constants;
import com.glaf.base.modules.sys.model.SysDepartment;
import com.glaf.base.modules.sys.model.SysTree;
import com.glaf.base.modules.sys.model.SysUser;
import com.glaf.base.modules.sys.service.SysDepartmentService;
import com.glaf.base.modules.sys.service.SysDeptRoleService;
import com.glaf.base.modules.sys.service.SysRoleService;
import com.glaf.base.modules.sys.service.SysTreeService;
import com.glaf.base.modules.sys.service.SysUserRoleService;
import com.glaf.base.modules.sys.service.SysUserService;
import com.glaf.base.utils.ParamUtil;
import com.glaf.base.utils.RequestUtil;
import com.glaf.core.cache.CacheUtils;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.res.MessageUtils;
import com.glaf.core.res.ViewMessage;
import com.glaf.core.res.ViewMessages;
import com.glaf.core.security.DigestUtil;
import com.glaf.core.util.RequestUtils;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.query.WxUserQuery;
import com.glaf.wechat.service.WxUserService;

@Controller("/wx/wxUser")
@RequestMapping("/wx/wxUser")
public class WxUserController {

	protected static final Log logger = LogFactory
			.getLog(WxUserController.class);

	protected WxUserService wxUserService;

	protected SysUserService sysUserService;

	protected SysUserRoleService sysUserRoleService;

	protected SysDepartmentService sysDepartmentService;

	protected SysDeptRoleService sysDeptRoleService;

	protected SysRoleService sysRoleService;

	protected SysTreeService sysTreeService;

	/**
	 * 显示修改页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/account")
	public ModelAndView account(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		SysUser user = RequestUtil.getLoginUser(request);

		WxUserQuery query = new WxUserQuery();
		query.createBy(user.getAccount());
		List<WxUser> list = wxUserService.list(query);
		if (list == null || list.isEmpty()) {
			WxUser wxUser = new WxUser();
			wxUser.setActorId(user.getActorId());
			wxUser.setAccountType(2);
			wxUser.setCreateDate(new Date());
			wxUser.setDeptId(user.getDeptId());
			wxUser.setLocked(0);
			wxUser.setUserType(1);
			wxUser.setId(user.getId());
			wxUserService.save(wxUser);
			list = wxUserService.list(query);
		}
		request.setAttribute("users", list);

		String x_view = ViewProperties.getString("wx.user.account");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/user/account", modelMap);
	}

	/**
	 * 显示修改页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/accountInfo")
	public ModelAndView accountInfo(HttpServletRequest request,
			ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		SysUser user = RequestUtil.getLoginUser(request);
		Long id = RequestUtils.getLong(request, "accountId");
		WxUser wxUser = null;
		if (id != null && id > 0) {
			wxUser = wxUserService.getWxUser(id);
		}

		if (wxUser == null) {
			wxUser = new WxUser();
			wxUser.setActorId(user.getActorId());
			wxUser.setAccountType(2);
			wxUser.setCreateDate(new Date());
			wxUser.setDeptId(user.getDeptId());
			wxUser.setLocked(0);
			wxUser.setUserType(1);
			wxUser.setId(user.getId());
			wxUserService.save(wxUser);
		}

		request.setAttribute("wxUser", wxUser);

		return new ModelAndView("/wx/user/accountInfo", modelMap);
	}

	/**
	 * 显示修改页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/editAccount")
	public ModelAndView editAccount(HttpServletRequest request,
			ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		SysUser user = RequestUtil.getLoginUser(request);
		Long id = RequestUtils.getLong(request, "id");
		WxUser wxUser = null;
		if (id != null && id > 0) {
			wxUser = wxUserService.getWxUser(id);
		}

		if (wxUser == null) {
			wxUser = new WxUser();
			wxUser.setActorId(user.getActorId());
			wxUser.setAccountType(2);
			wxUser.setCreateDate(new Date());
			wxUser.setDeptId(user.getDeptId());
			wxUser.setLocked(0);
			wxUser.setUserType(1);
			wxUser.setId(user.getId());
			wxUserService.save(wxUser);
		}

		request.setAttribute("wxUser", wxUser);

		SysDepartment department = sysDepartmentService.findByCode("website");
		if (department != null) {
			List<SysDepartment> depts = sysDepartmentService
					.getSysDepartmentList(department.getNodeId());
			modelMap.put("depts", depts);
			request.setAttribute("depts", depts);
		} else {
			department = new SysDepartment();
			department.setCode("website");
			department.setCode2("website");
			department.setNo("website");
			department.setDeptLevel(0);
			department.setDesc("网站用户");
			department.setName("网站用户");
			department.setLevel(0);
			department.setCreateBy("website");
			department.setCreateTime(new Date());

			long parentId = ParamUtil.getLongParameter(request, "parent", 0);
			if (parentId == 0) {
				SysTree root = sysTreeService
						.getSysTreeByCode(Constants.TREE_DEPT);
				parentId = root.getId();
			}
			SysTree node = new SysTree();
			node.setCreateBy(RequestUtils.getActorId(request));
			node.setName(department.getName());
			node.setDesc(department.getDesc());
			node.setCode(department.getCode());
			node.setParentId(parentId);
			department.setNode(node);
			department.setCreateBy(RequestUtils.getActorId(request));
			try {
				sysDepartmentService.create(department);
			} catch (Exception ex) {
				logger.error(ex);
			}

			List<SysDepartment> depts = new ArrayList<SysDepartment>();
			depts.add(department);
			modelMap.put("depts", depts);
			request.setAttribute("depts", depts);
		}

		String x_view = ViewProperties.getString("wx.user.editAccount");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/user/editAccount", modelMap);
	}

	/**
	 * 显示修改页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/prepareModifyInfo")
	public ModelAndView prepareModifyInfo(HttpServletRequest request,
			ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		SysUser user = RequestUtil.getLoginUser(request);
		SysUser bean = sysUserService.findByAccount(user.getAccount());
		request.setAttribute("bean", bean);

		String x_view = ViewProperties.getString("wx.user.prepareModifyInfo");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/user/user_change_info", modelMap);
	}

	/**
	 * 显示修改页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/prepareModifyPwd")
	public ModelAndView prepareModifyPwd(HttpServletRequest request,
			ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		SysUser bean = RequestUtil.getLoginUser(request);
		request.setAttribute("bean", bean);

		String x_view = ViewProperties.getString("wx.user.prepareModifyPwd");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/user/user_modify_pwd", modelMap);
	}

	/**
	 * 提交修改信息
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/saveModifyInfo")
	public ModelAndView saveModifyInfo(HttpServletRequest request,
			ModelMap modelMap) {
		SysUser bean = RequestUtil.getLoginUser(request);
		boolean ret = false;
		if (bean != null) {
			SysUser user = sysUserService.findByAccount(bean.getActorId());
			user.setName(ParamUtil.getParameter(request, "name"));
			user.setMobile(ParamUtil.getParameter(request, "mobile"));
			user.setEmail(ParamUtil.getParameter(request, "email"));
			user.setTelephone(ParamUtil.getParameter(request, "telephone"));
			user.setUpdateBy(RequestUtils.getActorId(request));
			ret = sysUserService.update(user);
			CacheUtils.clearUserCache(user.getAccount());
		}

		ViewMessages messages = new ViewMessages();
		if (ret) {// 保存成功
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"user.modify_success"));
		} else {// 保存失败
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"user.modify_failure"));
		}
		MessageUtils.addMessages(request, messages);
		return new ModelAndView("show_msg", modelMap);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/savePwd")
	public ModelAndView savePwd(HttpServletRequest request, ModelMap modelMap) {
		SysUser bean = RequestUtil.getLoginUser(request);
		boolean ret = false;
		String oldPwd = ParamUtil.getParameter(request, "oldPwd");
		String newPwd = ParamUtil.getParameter(request, "newPwd");
		if (bean != null && StringUtils.isNotEmpty(oldPwd)
				&& StringUtils.isNotEmpty(newPwd)) {
			SysUser user = sysUserService.findByAccount(bean.getActorId());
			try {
				String encPwd = DigestUtil.digestString(oldPwd, "MD5");
				if (StringUtils.equals(encPwd, user.getPassword())) {
					user.setPassword(DigestUtil.digestString(newPwd, "MD5"));
					user.setUpdateBy(RequestUtils.getActorId(request));
					// user.setLastChangePasswordDate(new Date());
					// user.setIsChangePassword(2);
					ret = sysUserService.update(user);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		ViewMessages messages = new ViewMessages();
		if (ret) {// 保存成功
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"user.modify_success"));
		} else {// 保存失败
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"user.modify_failure"));
		}
		MessageUtils.addMessages(request, messages);
		return new ModelAndView("show_msg", modelMap);
	}

	@javax.annotation.Resource
	public void setSysDepartmentService(
			SysDepartmentService sysDepartmentService) {
		this.sysDepartmentService = sysDepartmentService;
	}

	@javax.annotation.Resource
	public void setSysDeptRoleService(SysDeptRoleService sysDeptRoleService) {
		this.sysDeptRoleService = sysDeptRoleService;
	}

	@javax.annotation.Resource
	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@javax.annotation.Resource
	public void setSysTreeService(SysTreeService sysTreeService) {
		this.sysTreeService = sysTreeService;
	}

	@javax.annotation.Resource
	public void setSysUserRoleService(SysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	@javax.annotation.Resource
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
		logger.info("setSysUserService");
	}

	@javax.annotation.Resource
	public void setWxUserService(WxUserService wxUserService) {
		this.wxUserService = wxUserService;
	}

}
