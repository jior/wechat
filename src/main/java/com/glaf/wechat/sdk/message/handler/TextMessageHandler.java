package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.TextMessage;
import com.glaf.wechat.sdk.message.filter.KeywordsMessageFilter;
import com.glaf.wechat.sdk.message.filter.MessageFilterChain;
import com.glaf.wechat.sdk.message.filter.DefaultResponseMessageFilter;
import com.glaf.wechat.sdk.message.filter.HelpMessageFilter;

/**
 * handle text message <br>
 * every handler can create its own filter chain to handler message or not
 * create it by handling directly
 * 
 */
public class TextMessageHandler extends AbstractMessageHandler {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageFilterChain filterChain = new MessageFilterChain();
		filterChain.addFilter(new KeywordsMessageFilter());
		filterChain.addFilter(new HelpMessageFilter());
		// 加入默认的响应处理类
		filterChain.addFilter(new DefaultResponseMessageFilter());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		TextMessage msg = (TextMessage) message;
		msg.setContent(root.elementText(TAG_CONTENT));
	}

}
