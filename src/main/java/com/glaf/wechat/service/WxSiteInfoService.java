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

import com.glaf.wechat.domain.WxSiteInfo;
import com.glaf.wechat.query.WxSiteInfoQuery;

@Transactional(readOnly = true)
public interface WxSiteInfoService {

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
	 * ����������ȡһ����¼
	 * 
	 * @return
	 */
	WxSiteInfo getWxSiteInfo(Long id);
	
	/**
	 * ���ݴ����˻�ȡһ����¼
	 * 
	 * @return
	 */
	WxSiteInfo getWxSiteInfoByUser(String createBy);


	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWxSiteInfoCountByQueryCriteria(WxSiteInfoQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<WxSiteInfo> getWxSiteInfosByQueryCriteria(int start, int pageSize,
			WxSiteInfoQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<WxSiteInfo> list(WxSiteInfoQuery query);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(WxSiteInfo wxSiteInfo);

}
