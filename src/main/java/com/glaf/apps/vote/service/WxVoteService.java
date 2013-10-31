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
	WxVote getWxVote(Long id);
	
	/**
	 * ��ȡ���µ�һ��ͶƱ���
	 * @param voteId ͶƱ��¼ 
	 * @param ip IP��ַ
	 * @return
	 */
	WxVoteResult getLatestVoteResult(Long voteId, String ip);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWxVoteCountByQueryCriteria(WxVoteQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<WxVote> getWxVotesByQueryCriteria(int start, int pageSize,
			WxVoteQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<WxVote> list(WxVoteQuery query);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(WxVote wxVote);

}
