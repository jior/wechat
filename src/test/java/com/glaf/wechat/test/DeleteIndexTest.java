package com.glaf.wechat.test;

import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
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
 * ��ESɾ����������
 * 
 */
public class DeleteIndexTest extends AbstractTest {

	protected WxContentService wxContentService;

	public DeleteIndexTest() {
		wxContentService = getBean("wxContentService");
	}

	@Test
	public void deleteIndex() {
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
		WxContentQuery query = new WxContentQuery();
		List<WxContent> list = wxContentService.list(query);
		for (WxContent content : list) {
			//����wechatΪ����������һ��es��Ⱥ�п����ж�������⡣contentΪ�������ͣ�����������ͬ�������²�ͬ���͵����ݵģ�һ���������¿����ж���������͡�
			DeleteResponse response = client
					.prepareDelete("wechat", "content",
							String.valueOf(content.getId())).execute()
					.actionGet();
			System.out.println(response.getId());
			System.out.println(response.getHeaders());
		}

		client.close();
	}
}