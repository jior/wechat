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

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheFactory {

	private static class CacheFactoryHolder {
		public static CacheFactory instance = new CacheFactory();
	}

	public static CacheFactory getInstance() {
		return CacheFactoryHolder.instance;
	}

	protected Cache<Object, Object> cache;

	private CacheFactory() {
		cache = CacheBuilder.newBuilder().maximumSize(10000)
				.expireAfterAccess(30, TimeUnit.MINUTES).build();
	}

	public void clear() {
		getCache().invalidateAll();
		getCache().cleanUp();
	}

	public Cache<Object, Object> getCache() {
		if (cache == null) {
			cache = CacheBuilder.newBuilder().maximumSize(10000)
					.expireAfterAccess(30, TimeUnit.MINUTES).build();
		}
		return cache;
	}

	public Object getObject(String key) {
		Object value = getCache().getIfPresent(key);
		return value;
	}

	public void putObject(String key, Object value) {
		getCache().put(key, value);
	}

	public void removeObject(String key) {
		getCache().invalidate(key);
	}

	public void shutdown() {
		getCache().invalidateAll();
		getCache().cleanUp();
	}
}