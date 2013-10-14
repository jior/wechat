package com.glaf.wechat.sdk.message;

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

	public Message() {

	}

	public long getCreateTime() {
		return createTime;
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

	public String getToUserName() {
		return toUserName;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
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

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

}
