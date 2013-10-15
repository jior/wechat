package com.glaf.wechat.sdk.message;

import org.dom4j.Element;

/**
 * ��Ϣ������
 * 
 */
public class Message implements IMessage, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String msgType;// ��Ϣ���ͣ�text/image/location/link/voice��
	protected String fromUserName;// ���ͷ��ʺţ�һ��OpenID��
	protected String toUserName;// ������΢�ź�
	protected long msgId;// ��Ϣid��64λ�����ͣ�
	protected long createTime;// ��Ϣ����ʱ�� ��64λ�����ͣ�
	protected String customer;//�ͻ����
	protected Element root;

	public Message() {

	}

	public long getCreateTime() {
		return createTime;
	}

	public String getCustomer() {
		return customer;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public long getMsgId() {
		return msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public Element getRoot() {
		return root;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setRoot(Element root) {
		this.root = root;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

}
