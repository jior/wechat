package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.Message;

/**
 * ��Ϣ��Ӧ�ӿ�
 * 
 */
public interface IResponseMessageHandler {

	/**
	 * ������Ϣ�����װ��Ӧ��Ϣ
	 * 
	 * @param message
	 * @return
	 */
	String response(Message message);

}
