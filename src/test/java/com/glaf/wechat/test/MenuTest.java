/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glaf.wechat.test;

import com.glaf.wechat.component.Button;
import com.glaf.wechat.component.Menu;
import com.glaf.wechat.model.AccessToken;
import com.glaf.wechat.util.WechatUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �˵���������
 * 
 * @author jior
 */
public class MenuTest {
	private static Logger log = LoggerFactory.getLogger(MenuTest.class);

	/**
	 * ��װ�˵�����
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		Button btn11 = new Button();
		btn11.setName("����Ԥ��");
		btn11.setType("click");
		btn11.setKey("11");

		Button btn12 = new Button();
		btn12.setName("������ѯ");
		btn12.setType("click");
		btn12.setKey("12");

		Button btn13 = new Button();
		btn13.setName("�ܱ�����");
		btn13.setType("click");
		btn13.setKey("13");

		Button btn14 = new Button();
		btn14.setName("��ʷ�ϵĽ���");
		btn14.setType("click");
		btn14.setKey("14");

		Button btn21 = new Button();
		btn21.setName("�����㲥");
		btn21.setType("click");
		btn21.setKey("21");

		Button btn22 = new Button();
		btn22.setName("������Ϸ");
		btn22.setType("click");
		btn22.setKey("22");

		Button btn23 = new Button();
		btn23.setName("��Ů��̨");
		btn23.setType("click");
		btn23.setKey("23");

		Button btn24 = new Button();
		btn24.setName("����ʶ��");
		btn24.setType("click");
		btn24.setKey("24");

		Button btn25 = new Button();
		btn25.setName("�������");
		btn25.setType("click");
		btn25.setKey("25");

		Button btn31 = new Button();
		btn31.setName("Q��Ȧ");
		btn31.setType("click");
		btn31.setKey("31");

		Button btn32 = new Button();
		btn32.setName("��Ӱ���а�");
		btn32.setType("click");
		btn32.setKey("32");

		Button btn33 = new Button();
		btn33.setName("��ĬЦ��");
		btn33.setType("click");
		btn33.setKey("33");

		Button mainBtn1 = new Button();
		mainBtn1.setName("��������");
		mainBtn1.setSubButtons(new Button[] { btn11, btn12, btn13, btn14 });

		Button mainBtn2 = new Button();
		mainBtn2.setName("������վ");
		mainBtn2.setSubButtons(new Button[] { btn21, btn22, btn23, btn24, btn25 });

		Button mainBtn3 = new Button();
		mainBtn3.setName("��������");
		mainBtn3.setSubButtons(new Button[] { btn31, btn32, btn33 });

		/**
		 * ���ǹ��ں�xxxxĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
		 * 
		 * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
		 * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButtons(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		Menu menu = getMenu();
		log.info(menu.toJSONObject().toJSONString());
		// �������û�Ψһƾ֤
		String appId = "0000000000000000000000";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "00000000000000000000";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WechatUtils.getAccessToken(appId, appSecret);

		if (null != at) {
			// ���ýӿڴ����˵�
			int result = WechatUtils.createMenu(menu, at.getToken());

			// �жϲ˵��������
			if (0 == result) {
				log.info("�˵������ɹ���");
			} else {
				log.info("�˵�����ʧ�ܣ������룺" + result);
			}
		}
	}
}