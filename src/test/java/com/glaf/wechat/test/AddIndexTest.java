package com.glaf.wechat.test;

import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
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
 * 向ES添加索引对象
 * 
 */
public class AddIndexTest extends AbstractTest {

	protected WxContentService wxContentService;

	public AddIndexTest() {
		wxContentService = getBean("wxContentService");
	}

	@Test
	public void addIndex() {
		Settings settings = ImmutableSettings.settingsBuilder()
		// 5个主分片
				.put("number_of_shards", 5)
				// 测试环境，减少副本提高速度
				.put("number_of_replicas", 0)
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
			// 在这里创建我们要索引的对象
			//其中wechat为索引库名，一个es集群中可以有多个索引库。content为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
			IndexResponse response = client
					.prepareIndex("wechat", "content")
					// 必须为对象单独指定ID
					.setId(String.valueOf(content.getId()))
					.setSource(content.toJsonObject().toJSONString()).execute()
					.actionGet();
			// 多次index这个版本号会变
			System.out.println("version:" + response.getVersion());
			System.out.println("headers:" + response.getHeaders());
		}
		client.close();
	}
}