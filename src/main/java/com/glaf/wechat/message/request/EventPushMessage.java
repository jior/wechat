package com.glaf.wechat.message.request;

import org.dom4j.Document;
import org.dom4j.Element;

import com.glaf.wechat.message.BaseMessage;

public class EventPushMessage extends BaseMessage implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String event;

	protected String eventKey;

	public EventPushMessage() {

	}

	public String getEvent() {
		return event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public Document toDocument() {
		Document doc = super.toDocument();
		Element root = doc.getRootElement();
		root.addElement("Event").setText(event);
		root.addElement("EventKey").setText(eventKey);
		return doc;
	}

}
