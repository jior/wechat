package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.LocationMessage;
import com.glaf.wechat.sdk.message.filter.MessageFilterChain;
import com.glaf.wechat.sdk.message.filter.DefaultResponseMessageFilter;

/**
 * handle location message
 * 
 */
public class LocationMessageHandler extends AbstractMessageHandler {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageFilterChain filterChain = new MessageFilterChain();
		//加入默认的响应处理类
		filterChain.addFilter(new DefaultResponseMessageFilter());
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
