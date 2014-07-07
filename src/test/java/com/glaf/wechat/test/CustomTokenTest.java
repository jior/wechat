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
package com.glaf.wechat.test;

public class CustomTokenTest {

	public static void main(String[] args) {
		String time = System.currentTimeMillis() + "";// 日期时间
		String key = "XYTEYUOPEECSDFG456G78DD0OSXSAMJ5";// 密锁（硬盘上的某个文件）
		// String key = com.glaf.core.util.FileUtils.readFile("/key");//根目录下的key文件
		String actorId = "glaf";// 用户名
		String str = actorId + time + key;
		String tk = org.apache.commons.codec.digest.DigestUtils.md5Hex(str);
		System.out.println(time);
		System.out.println(tk);
	}

}
