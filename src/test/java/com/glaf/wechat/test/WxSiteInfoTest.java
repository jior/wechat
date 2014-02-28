package com.glaf.wechat.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.glaf.test.AbstractTest;
import com.glaf.wechat.domain.WxSiteInfo;
import com.glaf.wechat.query.WxSiteInfoQuery;
import com.glaf.wechat.service.WxSiteInfoService;

public class WxSiteInfoTest extends AbstractTest {

	protected WxSiteInfoService wxSiteInfoService;

	public WxSiteInfoTest() {
		wxSiteInfoService = getBean("wxSiteInfoService");
	}

	@Test
	public void insertWxSiteInfo() {
		for (int i = 0; i < 10; i++) {
			WxSiteInfo wxSiteInfo = new WxSiteInfo();
			wxSiteInfo.setLinkman("Linkman");
			wxSiteInfo.setTelephone("Telephone");
			wxSiteInfo.setMobile("Mobile");
			wxSiteInfo.setMail("Mail");
			wxSiteInfo.setQq("Qq");
			wxSiteInfo.setAddress("Address");
			wxSiteInfo.setSiteUrl("SiteUrl");
			wxSiteInfo.setRemark("Remark");
			wxSiteInfo.setCreateBy("CreateBy");
			wxSiteInfo.setCreateDate(new Date());

			wxSiteInfoService.save(wxSiteInfo);
		}
	}

	@Test
	public void list() {
		WxSiteInfoQuery wxSiteInfoQuery = new WxSiteInfoQuery();

		logger.debug("---------------------total----------------------");
		int total = wxSiteInfoService
				.getWxSiteInfoCountByQueryCriteria(wxSiteInfoQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxSiteInfo> list = wxSiteInfoService
					.getWxSiteInfosByQueryCriteria(0, 10, wxSiteInfoQuery);
			logger.debug(list);
		}
	}

}
