package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.TextMessage;
import com.glaf.wechat.sdk.message.filter.FilterChain;
import com.glaf.wechat.sdk.message.filter.FilterDefaultResponse;
import com.glaf.wechat.sdk.message.filter.FilterHelp;

/**
 * handle text message <br>
 * every handler can create its own filter chain to handler message or not
 * create it by handling directly
 * 
 */
public class TextMessageHandler extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new FilterHelp());
		filterChain.addFilter(new FilterDefaultResponse());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		TextMessage msg = (TextMessage) message;
		msg.setContent(root.elementText(TAG_CONTENT));
	}

}
