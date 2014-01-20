package com.glaf.wechat.test;

import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;

import com.glaf.test.AbstractTest;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.WxContentService;

/**
 * 从ES获取索引对象
 * 
 */
public class GetIndexTest extends AbstractTest {

	protected WxContentService wxContentService;

	public GetIndexTest() {
		wxContentService = getBean("wxContentService");
	}

	@Test
	public void deleteIndex() {
		Settings settings = ImmutableSettings.settingsBuilder()
		// 指定集群名称
				.put("cluster.name", "elasticsearch")
				// 探测集群中机器状态
				.put("client.transport.sniff", true).build();
		/*
		 * 创建客户端，所有的操作都由客户端开始，这个就好像是JDBC的Connection对象 用完记得要关闭
		 */
		TransportClient client = new TransportClient(settings);
		client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
				9300));
		client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
				9301));
		WxContentQuery query = new WxContentQuery();
		List<WxContent> list = wxContentService.list(query);
		for (WxContent content : list) {
			//其中wechat为索引库名，一个es集群中可以有多个索引库。content为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
			GetResponse response = client
					.prepareGet("wechat", "content",
							String.valueOf(content.getId())).execute()
					.actionGet();
			System.out.println("response.getId():" + response.getId());
			System.out.println("response.getSourceAsString():"
					+ response.getSourceAsString());
		}

		client.close();
	}
}