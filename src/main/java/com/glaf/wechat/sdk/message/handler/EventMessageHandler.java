package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.EventMessage;
import com.glaf.wechat.sdk.message.filter.FilterChain;
import com.glaf.wechat.sdk.message.filter.FilterDefaultResponse;
import com.glaf.wechat.sdk.message.filter.FilterGreeting;

/**
 * handle event message
 */
public class EventMessageHandler extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		// I do not check if it is "subscribe"
		filterChain.addFilter(new FilterGreeting());
		filterChain.addFilter(new FilterDefaultResponse());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		EventMessage msg = (EventMessage) message;
		msg.setEvent(root.elementText(TAG_EVENT));
		msg.setEventKey(root.elementText(TAG_EVENTKEY));
	}

}
