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

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Transactional(readOnly = true)
public interface WxFollowerService {

	/**
	 * 根据主键删除记录
	 * 
	 * @return
	 */
	@Transactional
	void deleteById(Long accountId, String openId);

	/**
	 * 根据主键删除多条记录
	 * 
	 * @return
	 */
	@Transactional
	void deleteByIds(Long accountId, List<String> openIds);

	/**
	 * 根据查询参数获取记录列表
	 * 
	 * @return
	 */
	List<WxFollower> list(WxFollowerQuery query);

	List<WxFollower> getEmptyWxFollowers(WxFollowerQuery query);

	List<String> getExistsWxFollowers(Long accountId, Collection<String> openIds);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getWxFollowerCountByQueryCriteria(WxFollowerQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<WxFollower> getWxFollowersByQueryCriteria(int start, int pageSize,
			WxFollowerQuery query);

	/**
	 * 根据OpenId获取一条记录
	 * 
	 * @return
	 */
	WxFollower getWxFollowerByOpenId(Long accountId, String openId);

	/**
	 * 获取一条关注者信息
	 * 
	 * @param sourceId
	 *            原始微信ID
	 * @param openId
	 *            关注者OPENID
	 * @return
	 */
	WxFollower getWxFollower(Long accountId, String sourceId, String openId);

	@Transactional
	void insertAll(List<WxFollower> followers);

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	@Transactional
	void save(WxFollower wxFollower);

}
