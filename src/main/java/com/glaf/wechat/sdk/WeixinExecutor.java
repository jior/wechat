package com.glaf.wechat.sdk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.glaf.wechat.sdk.message.IMessage;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.EventMessage;
import com.glaf.wechat.sdk.message.ImageMessage;
import com.glaf.wechat.sdk.message.LinkMessage;
import com.glaf.wechat.sdk.message.LocationMessage;
import com.glaf.wechat.sdk.message.TextMessage;
import com.glaf.wechat.sdk.message.handler.EventMessageHandler;
import com.glaf.wechat.sdk.message.handler.MessageHandlerHelper;
import com.glaf.wechat.sdk.message.handler.ImageMessageHandler;
import com.glaf.wechat.sdk.message.handler.LinkMessageHandler;
import com.glaf.wechat.sdk.message.handler.LocationMessageHandler;
import com.glaf.wechat.sdk.message.handler.TextMessageHandler;
import com.glaf.wechat.sdk.message.response.handler.ResponseMessageHandlerHelper;
import com.glaf.wechat.sdk.message.response.handler.ResponseMusicMessageHandler;
import com.glaf.wechat.sdk.message.response.handler.ResponseNewsMessageHandler;
import com.glaf.wechat.sdk.message.response.handler.ResponseTextMessageHandler;
import com.glaf.wechat.util.SignUtils;

/**
 * Weixin tool class
 * 
 */
public class WeixinExecutor implements IMessage {
	private HttpServletRequest request;// request
	private Message message;// message comes from
	private HttpServletResponse response;// response
	private Message messageResponse;// message will response
	private MessageHandlerHelper messageHadler;// handle message
	private ResponseMessageHandlerHelper responseMessageHandler;// handle
																// response
																// message

	public Message getMessage() {
		return message;
	}

	public MessageHandlerHelper getMessageHadler() {
		return messageHadler;
	}

	public Message getMessageResponse() {
		return messageResponse;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public ResponseMessageHandlerHelper getResponseMessageHandler() {
		return responseMessageHandler;
	}

	// parse the message content to Message
	private void parseInputStream() throws Exception {
		SAXReader xmlReader = new SAXReader();
		Document doc = null;
		try {
			doc = xmlReader.read(request.getInputStream());
		} catch (DocumentException ex) {
			throw new RuntimeException(ex);
		}

		Element root = doc.getRootElement();

		String type = root.elementText(IMessage.TAG_MSGTYPE);

		if (StringUtils.equalsIgnoreCase(type, MESSAGE_TEXT)) {
			message = new TextMessage();
			messageHadler = new TextMessageHandler();
		} else if (StringUtils.equalsIgnoreCase(type, MESSAGE_EVENT)) {
			// do subscribe event
			message = new EventMessage();
			messageHadler = new EventMessageHandler();
		} else if (StringUtils.equalsIgnoreCase(type, MESSAGE_IMAGE)) {
			message = new ImageMessage();
			messageHadler = new ImageMessageHandler();
		} else if (StringUtils.equalsIgnoreCase(type, MESSAGE_LINK)) {
			message = new LinkMessage();
			messageHadler = new LinkMessageHandler();
		} else if (StringUtils.equalsIgnoreCase(type, MESSAGE_LOCATION)) {
			message = new LocationMessage();
			messageHadler = new LocationMessageHandler();
		}
		// do the default/common parse!
		messageHadler.parseMessage(message, root);
	}

	// deal the message from user
	public void processMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		this.request = request;
		this.response = response;
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");
		if (signature != null
				&& SignUtils.checkSignature(signature, timestamp, nonce)) {
			if (echostr != null) {
				response.setContentType("text/plain");
				response.getWriter().write(echostr);
			} else {// do post
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/xml;charset=UTF-8");
				try {
					parseInputStream();// parse message
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				messageResponse = messageHadler.handleMessage(message);
				writeMessageToOuputStream();
			}
		} else {
			try {
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			} catch (ServletException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setMessageHadler(MessageHandlerHelper messageHadler) {
		this.messageHadler = messageHadler;
	}

	public void setMessageResponse(Message messageResponse) {
		this.messageResponse = messageResponse;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResponseMessageHandler(
			ResponseMessageHandlerHelper responseMessageHandler) {
		this.responseMessageHandler = responseMessageHandler;
	}

	// write the response message
	private void writeMessageToOuputStream() throws IOException {
		if (StringUtils.equalsIgnoreCase(messageResponse.getMsgType(),
				MESSAGE_RESPONSE_TEXT)) {
			responseMessageHandler = new ResponseTextMessageHandler();
		} else if (StringUtils.equalsIgnoreCase(messageResponse.getMsgType(),
				MESSAGE_RESPONSE_NEWS)) {
			responseMessageHandler = new ResponseNewsMessageHandler();
		} else if (StringUtils.equalsIgnoreCase(messageResponse.getMsgType(),
				MESSAGE_RESPONSE_MUSIC)) {
			responseMessageHandler = new ResponseMusicMessageHandler();
		}
		String responseContent = responseMessageHandler.response(messageResponse);
		response.getWriter().print(responseContent);
	}

}
