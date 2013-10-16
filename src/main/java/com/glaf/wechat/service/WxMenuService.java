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
	 * ��������ɾ����¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteById(Long id);

	/**
	 * ��������ɾ��������¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteByIds(List<Long> ids);

	/**
	 * ��ȡĳ���û���ĳ�������µ�ȫ���Ӳ˵�
	 * 
	 * @param createBy
	 * @param parentId
	 * @return
	 */
	List<WxMenu> getMenuList(String createBy, Long parentId);

	/**
	 * ��ȡĳ���û���ĳ�������ȫ���Ӳ˵�
	 * 
	 * @param createBy
	 * @param group
	 * @return
	 */
	List<WxMenu> getMenuList(String createBy, String group);

	/**
	 * ��ȡĳ���û���ĳ�������ȫ���Ӳ˵�
	 * 
	 * @param createBy
	 * @param group
	 * @param parentId
	 * @return
	 */
	List<WxMenu> getMenuList(String createBy, String group, Long parentId);

	/**
	 * ����������ȡһ����¼
	 * 
	 * @return
	 */
	WxMenu getWxMenu(Long id);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWxMenuCountByQueryCriteria(WxMenuQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<WxMenu> getWxMenusByQueryCriteria(int start, int pageSize,
			WxMenuQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<WxMenu> list(WxMenuQuery query);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(WxMenu wxMenu);

}
