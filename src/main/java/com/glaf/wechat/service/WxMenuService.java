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

package com.glaf.wechat.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.WxMenu;
import com.glaf.wechat.query.WxMenuQuery;

@Transactional(readOnly = true)
public interface WxMenuService {

	/**
	 * 根据主键删除记录
	 * 
	 * @return
	 */
	@Transactional
	void deleteById(Long id);

	/**
	 * 根据主键删除多条记录
	 * 
	 * @return
	 */
	@Transactional
	void deleteByIds(List<Long> ids);

	/**
	 * 获取某个用户的某个分类下的全部子菜单
	 * 
	 * @param accountId
	 * @param parentId
	 * @return
	 */
	List<WxMenu> getMenuList(Long accountId, Long parentId);

	/**
	 * 获取某个用户的某个分组的全部子菜单
	 * 
	 * @param accountId
	 * @param group
	 * @return
	 */
	List<WxMenu> getMenuList(Long accountId, String group);

	/**
	 * 获取某个用户的某个分组的全部子菜单
	 * 
	 * @param accountId
	 * @param group
	 * @param parentId
	 * @return
	 */
	List<WxMenu> getMenuList(Long accountId, String group, Long parentId);

	/**
	 * 根据主键获取一条记录
	 * 
	 * @return
	 */
	WxMenu getWxMenu(Long id);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getWxMenuCountByQueryCriteria(WxMenuQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<WxMenu> getWxMenusByQueryCriteria(int start, int pageSize,
			WxMenuQuery query);

	/**
	 * 根据查询参数获取记录列表
	 * 
	 * @return
	 */
	List<WxMenu> list(WxMenuQuery query);

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	@Transactional
	void save(WxMenu wxMenu);

	@Transactional
	void saveAll(List<WxMenu> rows);

}
