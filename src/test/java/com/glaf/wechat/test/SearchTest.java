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
 * ��ES��������
 * 
 */
public class SearchTest {

	@Test
	public void search() {
		Settings settings = ImmutableSettings.settingsBuilder()
		// ָ����Ⱥ����
				.put("cluster.name", "elasticsearch")
				// ̽�⼯Ⱥ�л���״̬
				.put("client.transport.sniff", true).build();
		/*
		 * �����ͻ��ˣ����еĲ������ɿͻ��˿�ʼ������ͺ�����JDBC��Connection���� ����ǵ�Ҫ�ر�
		 */
		TransportClient client = new TransportClient(settings);
		client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
				9300));
		client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
				9301));

		BoolQueryBuilder query = QueryBuilders.boolQuery();
		//query.must(QueryBuilders.fieldQuery("content", "��ҵ"));
		//query.must(QueryBuilders.fieldQuery("type", "P"));
		//query.must(QueryBuilders.fieldQuery("accountId", 1L));

		//����wechatΪ����������һ��es��Ⱥ�п����ж�������⡣contentΪ�������ͣ�����������ͬ�������²�ͬ���͵����ݵģ�һ���������¿����ж���������͡�
		SearchResponse response = client.prepareSearch("wechat")
				// �����prepareSearch��ָ�������У�����ʹ��setTypes
				.setTypes("content").setQuery(query).setFrom(0).setSize(100)
				.execute().actionGet();
		/**
		 * SearchHits��SearchHit�ĸ�����ʽ����ʾ�����һ���б�
		 */
		SearchHits shs = response.getHits();
		for (SearchHit hit : shs) {
			System.out.println("id:" + hit.getId() + ":"
					+ hit.getSourceAsString());
		}

		client.close();
	}
}