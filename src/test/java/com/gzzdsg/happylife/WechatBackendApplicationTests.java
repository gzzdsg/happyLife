package com.gzzdsg.happylife;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.gzzdsg.happylife.constant.EventTypeEnum;
import com.gzzdsg.happylife.domain.po.Food;
import com.gzzdsg.happylife.domain.vo.msg.RecEventMsg;
import com.gzzdsg.happylife.domain.vo.msg.RecTextMsg;
import com.gzzdsg.happylife.service.FoodService;
import com.gzzdsg.happylife.util.XmlUtils;
import jakarta.annotation.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class WechatBackendApplicationTests {

	@Resource
	private FoodService foodService;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void contextLoads() {
		System.out.println(new Date(1704555627000L));
	}

	@Test
	void testRedis() {
		String redisKey = "testkey";

		redisTemplate.opsForValue().set(redisKey, "1", 10, TimeUnit.SECONDS);

		System.out.println(redisTemplate.opsForValue().get(redisKey));
	}

	@Test
	void testMybatis() {
		List<Food> foods = foodService.findAllFood("123123123");
		System.out.println(JSON.toJSONString(foods));
	}

	@Test
	public void testEventMsg() throws DocumentException {
		String message = "<xml><ToUserName><![CDATA[gh_d67f72981591]]></ToUserName>\n" +
				"<FromUserName><![CDATA[oCwpY6k67fDs4a_jLVYQ55N68aw4]]></FromUserName>\n" +
				"<CreateTime>1704570547</CreateTime>\n" +
				"<MsgType><![CDATA[event]]></MsgType>\n" +
				"<Event><![CDATA[subscribe]]></Event>\n" +
				"<EventKey><![CDATA[]]></EventKey>\n" +
				"</xml>";

		JSONObject jsonObject = XmlUtils.parseXml(message);

		RecEventMsg recEventMsg = jsonObject.toJavaObject(RecEventMsg.class);
		System.out.println(JSON.toJSONString(recEventMsg));
		EventTypeEnum eventTypeEnum = EventTypeEnum.getByValue(recEventMsg.getEvent());
	}

	@Test
	void testParseXml() {

		String message = "<xml><ToUserName><![CDATA[gh_d67f72981591]]></ToUserName>\n" +
				"<FromUserName><![CDATA[oCwpY6k67fDs4a_jLVYQ55N68aw4]]></FromUserName>\n" +
				"<CreateTime>1704570595</CreateTime>\n" +
				"<MsgType><![CDATA[text]]></MsgType>\n" +
				"<Content><![CDATA[hello]]></Content>\n" +
				"<MsgId>24403581312208762</MsgId>\n" +
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
			String xml = XmlUtils.convertXml(recTextMsg);
			System.out.println(xml);

		} catch (DocumentException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
