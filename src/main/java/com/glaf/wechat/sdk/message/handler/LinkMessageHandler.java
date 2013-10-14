package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.LinkMessage;
import com.glaf.wechat.sdk.message.filter.FilterChain;
import com.glaf.wechat.sdk.message.filter.FilterDefaultResponse;

/**
 * handle link message
 * 
 */
public class LinkMessageHandler extends MessageHandlerHelper {

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
		LinkMessage msg = (LinkMessage) message;
		msg.setUrl(root.elementText(TAG_URL));
		msg.setDescription(root.elementText(TAG_DESCRIPTION));
		msg.setTitle(root.elementText(TAG_TITLE));
	}

}
