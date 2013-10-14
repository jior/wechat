package com.glaf.wechat.sdk.message;

import java.util.List;

/**
 * response message news
 * 
 */
public class ResponseNewsMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected int funcFlag;
	protected int count;
	protected List<ItemArticle> articleItems;

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
