package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ImageMessage;
import com.glaf.wechat.sdk.message.filter.FilterChain;
import com.glaf.wechat.sdk.message.filter.FilterDefaultResponse;

/**
 * handle image message
 * 
 */
public class ImageMessageHandler extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		// add this,so the next line does not have to verify whether response is
		// null or not
		filterChain.addFilter(new FilterDefaultResponse());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		ImageMessage msg = (ImageMessage) message;
		msg.setPicUrl(root.elementText(TAG_PICURL));
	}

}
