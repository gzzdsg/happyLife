package com.gzzdsgd.happylife;
;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.gzzdsgd.happylife.domain.RecTextMsg;
import com.gzzdsgd.happylife.util.XmlUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest
class WechatBackendApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new Date(1704555627000L));
	}

	@Test
	void testParseXml() {

		String message = "<xml><ToUserName><![CDATA[gh_d67f72981591]]></ToUserName>\n" +
				"<FromUserName><![CDATA[oCwpY6k67fDs4a_jLVYQ55N68aw4]]></FromUserName>\n" +
				"<CreateTime>1704555627</CreateTime>\n" +
				"<MsgType><![CDATA[text]]></MsgType>\n" +
				"<Content><![CDATA[hello]]></Content>\n" +
				"<MsgId>24403372291987643</MsgId>\n" +
				"</xml>";


		try {
			// 解析
			Document document = DocumentHelper.parseText(message);
			Element rootElement = document.getRootElement();
			System.out.println("根元素：" + rootElement.getName());
			List<Element> childElements = rootElement.elements();
			JSONObject msgData = new JSONObject();
			for (Element child : childElements) {
				System.out.println("子元素：" + child.getName() + "，值：" + child.getText());
				msgData.put(child.getName(), child.getText());
			}
			System.out.println(JSON.toJSONString(msgData));
			RecTextMsg recTextMsg = msgData.toJavaObject(RecTextMsg.class);
			System.out.println(JSON.toJSONString(recTextMsg));

			// 转换
			String xml = XmlUtils.convertXml(JSONObject.parseObject(JSON.toJSONString(recTextMsg), JSONObject.class));
			System.out.println(xml);

		} catch (DocumentException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
