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
package com.glaf.wechat.sdk.message;

import java.util.ArrayList;
import java.util.List;

/**
 * response news message 
 * 
 */
public class ResponseNewsMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected int funcFlag;
	protected int count;
	protected List<ItemArticle> articleItems;
	
	public void addItemArticle(ItemArticle item){
		if(articleItems == null){
			articleItems = new ArrayList<ItemArticle>();
		}
		articleItems.add(item);
	}

	public List<ItemArticle> getArticleItems() {
		return articleItems;
	}

	public int getCount() {
		return count;
	}

	public int getFuncFlag() {
		return funcFlag;
	}

	public void setArticleItems(List<ItemArticle> articleItems) {
		this.articleItems = articleItems;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}

}
