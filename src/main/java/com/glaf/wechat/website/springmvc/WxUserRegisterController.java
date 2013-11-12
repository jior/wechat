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
import com.glaf.base.modules.sys.model.SysDeptRole;
import com.glaf.base.modules.sys.model.SysRole;
import com.glaf.base.modules.sys.model.SysTree;
import com.glaf.base.modules.sys.model.SysUser;
import com.glaf.base.modules.sys.model.SysUserRole;
import com.glaf.base.modules.sys.service.SysDepartmentService;
import com.glaf.base.modules.sys.service.SysDeptRoleService;
import com.glaf.base.modules.sys.service.SysRoleService;
import com.glaf.base.modules.sys.service.SysTreeService;
import com.glaf.base.modules.sys.service.SysUserRoleService;
import com.glaf.base.modules.sys.service.SysUserService;
import com.glaf.base.utils.ParamUtil;
import com.glaf.core.config.Configuration;
import com.glaf.core.config.ViewProperties;
import com.glaf.core.res.MessageUtils;
import com.glaf.core.res.ViewMessage;
import com.glaf.core.res.ViewMessages;
import com.glaf.core.security.DigestUtil;
import com.glaf.core.util.RequestUtils;
import com.glaf.wechat.config.WechatConfiguration;

@Controller("/wx/register")
@RequestMapping("/wx/register")
public class WxUserRegisterController {

	protected static final Log logger = LogFactory
			.getLog(WxUserRegisterController.class);

	protected static Configuration conf = WechatConfiguration.create();

	protected SysUserService sysUserService;

	protected SysUserRoleService sysUserRoleService;

	protected SysDepartmentService sysDepartmentService;

	protected SysDeptRoleService sysDeptRoleService;

	protected SysRoleService sysRoleService;

	protected SysTreeService sysTreeService;

	public WxUserRegisterController() {

	}

	@RequestMapping("/create")
	public ModelAndView create(HttpServletRequest request, ModelMap modelMap) {
		SysUser bean = new SysUser();
		long deptId = RequestUtils.getLong(request, "deptId");
		if (deptId > 0) {
			SysDepartment department = sysDepartmentService.findById(deptId);
			bean.setDepartment(department);
			bean.setDeptId(department.getId());
		} else {
			SysDepartment department = sysDepartmentService
					.findByCode("website");
			if (department != null) {
				bean.setDepartment(department);
				bean.setDeptId(department.getId());
			}
		}

		bean.setAccount(ParamUtil.getParameter(request, "actorId"));
		bean.setCode(bean.getAccount());
		bean.setName(ParamUtil.getParameter(request, "name"));
		String password = ParamUtil.getParameter(request, "password");
		try {
			String pwd = DigestUtil.digestString(password, "MD5");
			bean.setPassword(pwd);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		bean.setMobile(ParamUtil.getParameter(request, "mobile"));
		bean.setEmail(ParamUtil.getParameter(request, "email"));
		bean.setBlocked(0);
		bean.setUserType(0);
		bean.setAccountType(2);
		bean.setEvection(0);
		bean.setCreateTime(new Date());
		bean.setLastLoginTime(new Date());
		bean.setCreateBy("website");
		bean.setUpdateBy("website");
		bean.setLastChangePasswordDate(new Date());
		bean.setIsChangePassword(0);

		int ret = 0;
		if (sysUserService.findByAccount(bean.getAccount()) == null) {
			if (sysUserService.create(bean)) {
				ret = 2;
				try {
					SysRole role = sysRoleService.findByCode("WX_ROLE");
					if (role != null) {
						SysDeptRole deptRole = sysDeptRoleService.find(
								bean.getDeptId(), role.getId());
						if (deptRole != null) {
							SysUserRole userRole = new SysUserRole();
							userRole.setAuthorized(0);
							userRole.setAuthorizeFrom(0);
							userRole.setCreateBy("website");
							userRole.setDeptRole(deptRole);
							userRole.setDeptRoleId(deptRole.getId());
							userRole.setUser(bean);
							userRole.setUserId(bean.getId());
							sysUserRoleService.create(userRole);
						}
					}
				} catch (Exception ex) {
					logger.error(ex);
				}
			}
		} else {// 帐号存在
			ret = 1;
		}

		ViewMessages messages = new ViewMessages();
		if (ret == 2) {// 保存成功
			modelMap.put("registerStatus", 2);
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"user.reg_success"));
		} else if (ret == 1) {// 保存失败
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"user.existed"));
		} else {
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"user.reg_failure"));
		}
		MessageUtils.addMessages(request, messages);

		// 显示注册成功页面
		return new ModelAndView("/wx/user/registerEnd", modelMap);
	}

	/**
	 * 显示修改页面
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);

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

		String x_view = ViewProperties.getString("wx.user.register");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/user/register", modelMap);
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

}
