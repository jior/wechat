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
 * ��ES�����������
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
		for (WxContent content : list) {
			// �����ﴴ������Ҫ�����Ķ���
			IndexResponse response = client
					.prepareIndex("wechat", "content")
					// ����Ϊ���󵥶�ָ��ID
					.setId(String.valueOf(content.getId()))
					.setSource(content.toJsonObject().toJSONString()).execute()
					.actionGet();
			// ���index����汾�Ż��
			System.out.println("version:" + response.getVersion());
			System.out.println("headers:" + response.getHeaders());
		}
		client.close();
	}
}