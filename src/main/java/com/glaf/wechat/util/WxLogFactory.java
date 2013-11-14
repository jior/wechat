package com.glaf.wechat.util;

import com.glaf.core.config.Configuration;
import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.config.WechatConfiguration;
import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.mongodb.config.MongodbContextFactory;
import com.glaf.wechat.service.WxLogService;

public class WxLogFactory {

	protected static Configuration conf = WechatConfiguration.create();

	protected static boolean isMongoDB = false;

	static {
		isMongoDB = conf.getBoolean("mongodb.logEnable", false);
	}

	public static void create(WxLog log) {
		if (isMongoDB) {
			WxLogService wxLogService = (WxLogService) MongodbContextFactory
					.getBean("wxLogService");
			wxLogService.create(log);
		} else {
			WxLogService wxLogService = ContextFactory.getBean("wxLogService");
			wxLogService.create(log);
		}
	}

}
