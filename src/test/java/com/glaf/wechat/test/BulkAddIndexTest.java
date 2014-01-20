package com.glaf.wechat.test;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
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
 * ��ES�����������
 * 
 */
public class BulkAddIndexTest extends AbstractTest {

	protected WxContentService wxContentService;

	public BulkAddIndexTest() {
		wxContentService = getBean("wxContentService");
	}

	@Test
	public void addIndex() {
		Settings settings = ImmutableSettings.settingsBuilder()
		// 5������Ƭ
				.put("number_of_shards", 5)
				// ���Ի��������ٸ�������ٶ�
				.put("number_of_replicas", 0)
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
		WxContentQuery query = new WxContentQuery();
		List<WxContent> list = wxContentService.list(query);
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (WxContent content : list) {
			// �����ﴴ������Ҫ�����Ķ���
			//����wechatΪ����������һ��es��Ⱥ�п����ж�������⡣contentΪ�������ͣ�����������ͬ�������²�ͬ���͵����ݵģ�һ���������¿����ж���������͡�
			IndexRequestBuilder indexRequest = client
					.prepareIndex("wechat", "content")
					// ����Ϊ���󵥶�ָ��ID
					.setId(String.valueOf(content.getId()))
					.setSource(content.toJsonObject().toJSONString());
			bulkRequest.add(indexRequest);
		}

		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			// process failures by iterating through each bulk response item
			System.out.println(bulkResponse.buildFailureMessage());
		}

		client.close();
	}
}