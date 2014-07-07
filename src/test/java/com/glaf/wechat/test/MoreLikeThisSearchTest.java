package com.glaf.wechat.test;

import org.elasticsearch.action.mlt.MoreLikeThisRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

/**
 * 从ES检索对象
 * 
 */
public class MoreLikeThisSearchTest {

	@Test
	public void search() {
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

		MoreLikeThisRequestBuilder mlt = new MoreLikeThisRequestBuilder(client,
				"wechat", "content", "2225");
		mlt.setField("title");// 匹配的字段
		SearchResponse response = client.moreLikeThis(mlt.request())
				.actionGet();
		SearchHits shs = response.getHits();
		for (SearchHit hit : shs) {
			System.out.println("id:" + hit.getId() + ":"
					+ hit.getSourceAsString());
		}

		client.close();
	}
}