package com.glaf.wechat.sdk;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * to deal with messages from Weixin server
 * 
 */
public class WeixinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private WeixinExecutor weixinExecutor;

	public void destroy() {
		super.destroy();
		weixinExecutor = null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		weixinExecutor.processMessage(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		weixinExecutor.processMessage(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		weixinExecutor = new WeixinExecutor();
	}

}
