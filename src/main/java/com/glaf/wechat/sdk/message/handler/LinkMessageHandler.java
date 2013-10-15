package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.LinkMessage;
import com.glaf.wechat.sdk.message.filter.MessageFilterChain;
import com.glaf.wechat.sdk.message.filter.DefaultResponseMessageFilter;

/**
 * handle link message
 * 
 */
public class LinkMessageHandler extends AbstractMessageHandler {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageFilterChain filterChain = new MessageFilterChain();
		//加入默认的响应处理类
		filterChain.addFilter(new DefaultResponseMessageFilter());
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
