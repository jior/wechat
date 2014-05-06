package com.glaf.wechat.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.glaf.core.util.DateUtils;
import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.mongodb.config.MongodbContextFactory;
import com.glaf.wechat.query.WxLogQuery;
import com.glaf.wechat.service.WxLogService;

public class MongodbLogTest {

	protected WxLogService wxLogService;

	@Test
	public void addLog() {
		for (int i = 0; i < 100; i++) {
			WxLog log = new WxLog();
			log.setActorId("root");
			log.setAccountId(1L);
			log.setFlag(1);
			log.setIp("127.0.0.1");
			log.setOperate("add");
			wxLogService.save(log);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("开始测试..................................");
		wxLogService = (WxLogService) MongodbContextFactory
				.getBean("wxLogService");
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testList() {
		WxLogQuery query = new WxLogQuery();
		query.setSuffix("_" + DateUtils.getNowYearMonthDay());
		query.setIp("127.0.0.1");
		
		List<WxLog> logs = wxLogService.list(query);
		for(WxLog log:logs){
			System.out.println(log.getId());
		}
	}
	
	@Test
	public void testPaging() {
		WxLogQuery query = new WxLogQuery();
		query.setSuffix("_" + DateUtils.getNowYearMonthDay());
		query.setIp("127.0.0.1");
		
		List<WxLog> logs = wxLogService.getWxLogsByQueryCriteria(0, 10, query);
		for(WxLog log:logs){
			System.out.println(log.getId());
		}
	}

	@Test
	public void testCount() {
		WxLogQuery query = new WxLogQuery();
		query.setSuffix("_" + DateUtils.getNowYearMonthDay());
		int total = wxLogService.getWxLogCountByQueryCriteria(query);
		System.out.println("total:" + total);
	}

}
