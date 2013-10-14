package com.glaf.wechat.sdk.message;

/**
 * 消息基础类
 * 
 */
public class Message implements IMessage, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String msgType;// 消息类型（text/image/location/link/voice）
	protected String fromUserName;// 发送方帐号（一个OpenID）
	protected String toUserName;// 开发者微信号
	protected long msgId;// 消息id（64位长整型）
	protected long createTime;// 消息创建时间 （64位长整型）

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
