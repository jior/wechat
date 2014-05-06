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

package com.glaf.apps.vote.service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.apps.vote.domain.*;
import com.glaf.apps.vote.query.*;

@Transactional(readOnly = true)
public interface WxVoteService {

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
	WxVote getWxVote(Long id);
	
	/**
	 * 获取最新的一条投票结果
	 * @param voteId 投票记录 
	 * @param ip IP地址
	 * @return
	 */
	WxVoteResult getLatestVoteResult(Long voteId, String ip);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getWxVoteCountByQueryCriteria(WxVoteQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<WxVote> getWxVotesByQueryCriteria(int start, int pageSize,
			WxVoteQuery query);

	/**
	 * 根据查询参数获取记录列表
	 * 
	 * @return
	 */
	List<WxVote> list(WxVoteQuery query);

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	@Transactional
	void save(WxVote wxVote);

}
