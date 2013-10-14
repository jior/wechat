package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.IMessage;
import com.glaf.wechat.sdk.message.Message;

/**
 * a helper class for handle message response
 * 
 */
public class ResponseMessageHandlerHelper implements IMessage,
		IResponseMessageHandler {

	public String response(Message message) {
		return null;
	}

	// wrap the content by tag & content & whether CDATA used
	public String wrapperContent(String tag, String content, boolean isCDATA) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<" + tag + ">");
		if (isCDATA) {
			buffer.append("<![CDATA[");
		}
		buffer.append(content);
		if (isCDATA) {
			buffer.append("]]>");
		}
		buffer.append("</" + tag + ">");
		return buffer.toString();
	}

	// wrap the root tag xml
	public String wrapperXmlContent(String content) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<" + TAG_XML + ">");
		buffer.append(content);
		buffer.append("</" + TAG_XML + ">");
		return buffer.toString();
	}

}
