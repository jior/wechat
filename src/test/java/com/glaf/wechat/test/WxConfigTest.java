package com.glaf.wechat.test;

import java.util.*;

import org.junit.Test;

import com.glaf.wechat.domain.WxConfig;
import com.glaf.wechat.query.WxConfigQuery;
import com.glaf.wechat.service.WxConfigService;
import com.glaf.test.AbstractTest;

public class WxConfigTest extends AbstractTest {

	protected WxConfigService wxConfigService;

	public WxConfigTest() {
		wxConfigService = getBean("wxConfigService");
	}

	@Test
	public void insertWxConfig() {
		for (int i = 0; i < 10; i++) {
			WxConfig wxConfig = new WxConfig();
			wxConfig.setCallBackUrl("CallBackUrl");
			wxConfig.setToken("Token");
			wxConfig.setAppId("AppId");
			wxConfig.setAppSecret("AppSecret");
			wxConfig.setApiStatus("ApiStatus");
			wxConfig.setDefaultReply("DefaultReply");
			wxConfig.setUuid("Uuid");
			wxConfig.setCreateBy("CreateBy");
			wxConfig.setCreateDate(new Date());

			wxConfigService.save(wxConfig);
		}
	}

	@Test
	public void list() {
		WxConfigQuery wxConfigQuery = new WxConfigQuery();

		logger.debug("---------------------total----------------------");
		int total = wxConfigService
				.getWxConfigCountByQueryCriteria(wxConfigQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxConfig> list = wxConfigService.getWxConfigsByQueryCriteria(
					0, 10, wxConfigQuery);
			logger.debug(list);
		}
	}

}
