package com.glaf.wechat.test;

import java.util.List;

import org.junit.Test;

import com.glaf.core.query.TreeModelQuery;
import com.glaf.core.service.EntityService;
import com.glaf.test.AbstractTest;

public class IdentityTest extends AbstractTest {

	protected EntityService entityService;

	@Test
	public void testList() {
		entityService = getBean("entityService");
		TreeModelQuery query = new com.glaf.core.query.TreeModelQuery();
		List<Long> nodeIds = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		nodeIds.add(6L);
		nodeIds.add(2410L);
		List<Object> list = entityService.getList("getDepartments", query);
		logger.debug(list);
	}
}
