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

package com.glaf.wechat.message.response;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.glaf.wechat.message.BaseMessage;

/**
 * 文本消息
 * 
 * @author jior
 */
public class NewsMessage extends BaseMessage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 图文消息个数，限制为10条以内
	private int articleCount;

	// 多条图文消息信息，默认第一个item为大图
	private List<Article> articles;

	public NewsMessage() {

	}

	public int getArticleCount() {
		return articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Document toDocument() {
		Document doc = super.toDocument();
		Element root = doc.getRootElement();
		root.addElement("ArticleCount").setText(String.valueOf(articleCount));
		if (articles != null && !articles.isEmpty()) {
			Element elem = root.addElement("Articles");
			for (Article art : articles) {
				Element e = elem.addElement("item");
				e.addElement("Title").setText(art.getTitle());
				e.addElement("Description").setText(art.getDescription());
				e.addElement("PicUrl").setText(art.getPicUrl());
				e.addElement("Url").setText(art.getUrl());
			}
		}
		return doc;
	}
}