package com.gzzdsg.happylife.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * xml操作工具类
 *
 * @author: damei
 */
public class XmlUtils {

    /**
     * 将xml字符串解析成JSONObject
     *
     * @param message xml字符串
     * @return 解析完的JSONObject
     * @throws DocumentException 解析异常
     */
    public static JSONObject parseXml(String message) throws DocumentException {
        Document document = DocumentHelper.parseText(message);
        Element rootElement = document.getRootElement();
        List<Element> childElements = rootElement.elements();
        JSONObject msgData = new JSONObject();
        for (Element child : childElements) {
            msgData.put(child.getName(), child.getText());
        }
        return msgData;
    }

    /**
     * 将json对象转为XML
     *
     * @param msg 消息信息
     * @return xml字符串
     * @throws IOException
     */
    public static String convertXml(Object msg) throws IOException {
        JSONObject msgData = JSONObject.parseObject(JSON.toJSONString(msg), JSONObject.class);
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        for (Map.Entry<String, Object> entry : msgData.entrySet()) {
            rootElement.addElement(entry.getKey()).addText(entry.getValue().toString());
        }
        StringWriter sw = new StringWriter();
        XMLWriter writer = new XMLWriter(sw, OutputFormat.createPrettyPrint());
        writer.write(document);
        writer.flush();
        writer.close();
        return sw.toString();
    }

}
