package com.glaf.wechat.sdk.message;

/**
 * response text message 
 * 
 */
public class ResponseTextMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected String content;
	protected int funcFlag;

	public String getContent() {
		return content;
	}

	public int getFuncFlag() {
		return funcFlag;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}

}
