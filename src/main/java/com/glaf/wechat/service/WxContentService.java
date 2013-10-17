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
	 * 根据主键获取一条记录
	 * 
	 * @return
	 */
	WxContent getWxContent(Long id);

	/**
	 * 根据uuid获取一条记录
	 * 
	 * @return
	 */
	WxContent getWxContentByUUID(String uuid);

	/**
	 * 获取内容及关联信息
	 * 
	 * @param id
	 * @return
	 */
	WxContent getWxContentWithRefs(Long id);
	
	WxContent getWxContentByUUIDWithRefs(String uuid);

	
	/**
	 * 获取某个用户某个栏目指定类型的内容
	 * @param createBy
	 * @param categoryId
	 * @param type
	 * @return
	 */
	List<WxContent> getWxContents(String createBy, Long categoryId, String type);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getWxContentCountByQueryCriteria(WxContentQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<WxContent> getWxContentsByQueryCriteria(int start, int pageSize,
			WxContentQuery query);

	/**
	 * 根据查询参数获取记录列表
	 * 
	 * @return
	 */
	List<WxContent> list(WxContentQuery query);

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	@Transactional
	void save(WxContent wxContent);

}
