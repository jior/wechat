package com.glaf.wechat.test;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

/**
 * 从ES检索对象
 * 
 */
public class SearchTest {

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

		BoolQueryBuilder query = QueryBuilders.boolQuery();
		//query.must(QueryBuilders.fieldQuery("content", "商业"));
		//query.must(QueryBuilders.fieldQuery("type", "P"));
		//query.must(QueryBuilders.fieldQuery("accountId", 1L));

		//其中wechat为索引库名，一个es集群中可以有多个索引库。content为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
		SearchResponse response = client.prepareSearch("wechat")
				// 这个在prepareSearch中指定还不行，必须使用setTypes
				.setTypes("content").setQuery(query).setFrom(0).setSize(100)
				.execute().actionGet();
		/**
		 * SearchHits是SearchHit的复数形式，表示这个是一个列表
		 */
		SearchHits shs = response.getHits();
		for (SearchHit hit : shs) {
			System.out.println("id:" + hit.getId() + ":"
					+ hit.getSourceAsString());
		}

		client.close();
	}
}