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

import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.query.WxContentQuery;

@Transactional(readOnly = true)
public interface WxContentService {

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
	WxContent getWxContent(Long id);

	/**
	 * ����uuid��ȡһ����¼
	 * 
	 * @return
	 */
	WxContent getWxContentByUUID(String uuid);

	/**
	 * ��ȡ���ݼ�������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	WxContent getWxContentWithRefs(Long id);
	
	WxContent getWxContentByUUIDWithRefs(String uuid);

	
	/**
	 * ��ȡĳ���û�ĳ����Ŀָ�����͵�����
	 * @param createBy
	 * @param categoryId
	 * @param type
	 * @return
	 */
	List<WxContent> getWxContents(String createBy, Long categoryId, String type);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWxContentCountByQueryCriteria(WxContentQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<WxContent> getWxContentsByQueryCriteria(int start, int pageSize,
			WxContentQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<WxContent> list(WxContentQuery query);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(WxContent wxContent);

}
