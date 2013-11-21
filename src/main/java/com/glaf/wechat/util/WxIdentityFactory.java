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

package com.glaf.wechat.util;

import java.util.Date;

import com.glaf.core.context.ContextFactory;
import com.glaf.core.identity.User;
import com.glaf.core.util.UUID32;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.service.WxUserService;

public class WxIdentityFactory {

	protected static WxUserService wxUserService;

	private WxIdentityFactory() {

	}

	public static void createWxAccount(User user) {
		if (getUserByAccountId(user.getId()) == null) {
			WxUser wxUser = new WxUser();
			wxUser.setToken(UUID32.getUUID());
			wxUser.setActorId(user.getActorId());
			wxUser.setAccountType(2);
			wxUser.setCreateDate(new Date());
			wxUser.setDeptId(user.getDeptId());
			wxUser.setLocked(0);
			wxUser.setUserType(1);
			wxUser.setId(user.getId());
			getWxUserService().save(wxUser);
		}
	}

	public static WxUser getUserByAccountId(Long accountId) {
		return getWxUserService().getWxUser(accountId);
	}

	public static WxUserService getWxUserService() {
		if (wxUserService == null) {
			wxUserService = ContextFactory.getBean("wxUserService");
		}
		return wxUserService;
	}

}
