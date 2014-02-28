package com.glaf.wechat.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.glaf.apps.member.domain.WxMember;
import com.glaf.apps.member.query.WxMemberQuery;
import com.glaf.apps.member.service.WxMemberService;
import com.glaf.test.AbstractTest;

public class WxMemberTest extends AbstractTest {

	protected WxMemberService wxMemberService;

	public WxMemberTest() {
		wxMemberService = getBean("wxMemberService");
	}

	@Test
	public void insertWxMember() {
		for (int i = 0; i < 10; i++) {
			WxMember wxMember = new WxMember();
			wxMember.setCardNo("CardNo");
			wxMember.setName("Name");
			wxMember.setTelephone("Telephone");
			wxMember.setMobile("Mobile");
			wxMember.setMail("Mail");
			wxMember.setQq("Qq");
			wxMember.setAddress("Address");
			wxMember.setStatus(1);
			wxMember.setUuid("Uuid");
			wxMember.setCreateBy("CreateBy");
			wxMember.setCreateDate(new Date());

			wxMemberService.save(wxMember);
		}
	}

	@Test
	public void list() {
		WxMemberQuery wxMemberQuery = new WxMemberQuery();

		logger.debug("---------------------total----------------------");
		int total = wxMemberService
				.getWxMemberCountByQueryCriteria(wxMemberQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxMember> list = wxMemberService.getWxMembersByQueryCriteria(
					0, 10, wxMemberQuery);
			logger.debug(list);
		}
	}

}
