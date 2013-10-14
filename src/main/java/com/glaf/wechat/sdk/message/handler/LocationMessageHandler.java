package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.LocationMessage;
import com.glaf.wechat.sdk.message.filter.FilterChain;
import com.glaf.wechat.sdk.message.filter.FilterDefaultResponse;

/**
 * handle location message
 * 
 */
public class LocationMessageHandler extends MessageHandlerHelper {

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
		LocationMessage msg = (LocationMessage) message;
		msg.setLocationX(root.elementText(TAG_LOCATIONX));
		msg.setLocationY(root.elementText(TAG_LOCATIONY));
		msg.setLabel(root.elementText(TAG_LABEL));
		msg.setScale(root.elementText(TAG_SCALE));
	}

}
