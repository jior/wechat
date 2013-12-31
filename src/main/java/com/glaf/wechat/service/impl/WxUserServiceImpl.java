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

package com.glaf.wechat.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.base.modules.sys.model.SysDepartment;
import com.glaf.base.modules.sys.model.SysDeptRole;
import com.glaf.base.modules.sys.model.SysRole;
import com.glaf.base.modules.sys.model.SysUser;
import com.glaf.base.modules.sys.model.SysUserRole;
import com.glaf.base.modules.sys.service.SysDepartmentService;
import com.glaf.base.modules.sys.service.SysDeptRoleService;
import com.glaf.base.modules.sys.service.SysRoleService;
import com.glaf.base.modules.sys.service.SysTreeService;
import com.glaf.base.modules.sys.service.SysUserRoleService;
import com.glaf.base.modules.sys.service.SysUserService;
import com.glaf.core.config.Configuration;
import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.core.util.DateUtils;
import com.glaf.core.util.Tools;
import com.glaf.core.util.UUID32;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.mapper.WxUserMapper;
import com.glaf.wechat.query.WxUserQuery;
import com.glaf.wechat.service.WxUserService;

@Service("wxUserService")
@Transactional(readOnly = true)
public class WxUserServiceImpl implements WxUserService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static Configuration conf = WechatConfiguration.create();

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxUserMapper wxUserMapper;

	protected SysUserService sysUserService;

	protected SysUserRoleService sysUserRoleService;

	protected SysDepartmentService sysDepartmentService;

	protected SysDeptRoleService sysDeptRoleService;

	protected SysRoleService sysRoleService;

	protected SysTreeService sysTreeService;

	public WxUserServiceImpl() {

	}

	/**
	 * 创建用户账号
	 * 
	 * @param user
	 */
	@Transactional
	public boolean createAccount(SysUser bean) {
		boolean ret = false;
		long deptId = bean.getDeptId();
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
		if (sysUserService.findByMail(bean.getEmail()) != null) {
			throw new RuntimeException(bean.getEmail() + " is exist.");
		}
		if (sysUserService.create(bean)) {
			SysRole role = sysRoleService.findByCode("WX_ROLE");
			if (role != null) {
				if (conf.getBoolean("isIsdpIdentity", false)) {
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("authorizeFrom", 0);
					dataMap.put("userId", bean.getAccount());
					dataMap.put("roleId", String.valueOf(role.getId()));
					SysUserRole userRole = new SysUserRole();
					Tools.populate(userRole, dataMap);
					userRole.setAuthorized(0);
					userRole.setCreateBy("website");
					userRole.setCreateDate(new Date());
					userRole.setAvailDateStart(new Date());
					userRole.setAvailDateEnd(DateUtils.toDate("2020-01-01"));
					userRole.setUser(bean);
					sysUserRoleService.create(userRole);
				} else {
					SysDeptRole deptRole = sysDeptRoleService.find(
							bean.getDeptId(), role.getId());
					if (deptRole != null) {
						Map<String, Object> dataMap = new HashMap<String, Object>();
						dataMap.put("authorizeFrom", "0");
						dataMap.put("userId", bean.getId());
						dataMap.put("deptRoleId", deptRole.getId());
						SysUserRole userRole = new SysUserRole();
						Tools.populate(userRole, dataMap);
						userRole.setAuthorized(0);
						userRole.setCreateBy("website");
						userRole.setDeptRole(deptRole);
						userRole.setUser(bean);
						userRole.setCreateDate(new Date());
						userRole.setAvailDateStart(new Date());
						userRole.setAvailDateEnd(DateUtils.toDate("2020-01-01"));
						sysUserRoleService.create(userRole);
					}
				}
			}
			ret = true;
		}
		return ret;
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxUserMapper.deleteWxUserById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxUserMapper.deleteWxUserById(id);
			}
		}
	}

	public int count(WxUserQuery query) {
		return wxUserMapper.getWxUserCount(query);
	}

	public List<WxUser> list(WxUserQuery query) {
		List<WxUser> list = wxUserMapper.getWxUsers(query);
		return list;
	}

	public int getWxUserCountByQueryCriteria(WxUserQuery query) {
		return wxUserMapper.getWxUserCount(query);
	}

	public List<WxUser> getWxUsersByQueryCriteria(int start, int pageSize,
			WxUserQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxUser> rows = sqlSessionTemplate.selectList("getWxUsers", query,
				rowBounds);
		return rows;
	}

	public WxUser getWxUser(Long id) {
		if (id == null) {
			return null;
		}
		WxUser wxUser = wxUserMapper.getWxUserById(id);
		return wxUser;
	}

	public WxUser getWxUserByActorId(String actorId) {
		WxUserQuery query = new WxUserQuery();
		query.actorId(actorId);
		List<WxUser> rows = this.list(query);
		if (rows != null && !rows.isEmpty()) {
			return rows.get(0);
		}
		return null;
	}

	@Transactional
	public void save(WxUser wxUser) {
		WxUser model = null;
		if (wxUser.getId() > 0) {
			model = this.getWxUser(wxUser.getId());
		}
		if (wxUser.getId() == 0) {
			wxUser.setId(idGenerator.nextId());
		}
		if (model == null) {
			if (StringUtils.isEmpty(wxUser.getToken())) {
				wxUser.setToken(UUID32.getUUID());
			}
			wxUserMapper.insertWxUser(wxUser);
		} else {
			wxUserMapper.updateWxUser(wxUser);
		}
	}

	@javax.annotation.Resource
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@javax.annotation.Resource
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@javax.annotation.Resource
	public void setWxUserMapper(WxUserMapper wxUserMapper) {
		this.wxUserMapper = wxUserMapper;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
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
