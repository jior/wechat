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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter {

	protected static final ConcurrentMap<Long, AtomicInteger> counter = new ConcurrentHashMap<Long, AtomicInteger>();

	public static void add(Long accountId) {
		AtomicInteger count = counter.get(accountId);
		if (count == null) {
			count = new AtomicInteger(1);
		}
		count.incrementAndGet();
		counter.put(accountId, count);
	}

	public static int get(Long accountId) {
		AtomicInteger count = counter.get(accountId);
		if (count != null) {
			return count.get();
		}
		return 0;
	}

	public static void remove(Long accountId) {
		counter.remove(accountId);
	}

	private ThreadCounter() {

	}

}
