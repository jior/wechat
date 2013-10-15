package com.glaf.wechat.sdk.message.handler;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.EventMessage;
import com.glaf.wechat.sdk.message.filter.MessageFilterChain;
import com.glaf.wechat.sdk.message.filter.DefaultResponseMessageFilter;
import com.glaf.wechat.sdk.message.filter.GreetingMessageFilter;

/**
 * handle event message
 */
public class EventMessageHandler extends AbstractMessageHandler {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageFilterChain filterChain = new MessageFilterChain();
		EventMessage msg = (EventMessage) message;
		if (StringUtils.equalsIgnoreCase(msg.getEvent(), "subscribe")) {
			// �����¼�
		} else if (StringUtils.equalsIgnoreCase(msg.getEvent(), "CLICK")) {
			// �Զ���˵�����¼�
		}
		// I do not check if it is "subscribe"
		filterChain.addFilter(new GreetingMessageFilter());
		// ����Ĭ�ϵ���Ӧ������
		filterChain.addFilter(new DefaultResponseMessageFilter());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		EventMessage msg = (EventMessage) message;
		msg.setEvent(root.elementText(TAG_EVENT));
		msg.setEventKey(root.elementText(TAG_EVENTKEY));
	}

}
