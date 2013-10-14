package com.glaf.wechat.sdk.message;

/**
 * event message
 * 
 */
public class EventMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected String event;
	protected String eventKey;

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

}
