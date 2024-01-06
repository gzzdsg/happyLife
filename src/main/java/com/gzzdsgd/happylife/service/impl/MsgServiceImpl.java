package com.gzzdsgd.happylife.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.Throwables;
import com.gzzdsgd.happylife.constant.MsgTypeEnum;
import com.gzzdsgd.happylife.domain.RecTextMsg;
import com.gzzdsgd.happylife.service.MsgService;
import com.gzzdsgd.happylife.util.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 消息逻辑处理
 *
 * @author: damei
 */
@Slf4j
@Service
public class MsgServiceImpl implements MsgService {


    @Override
    public String receiveMsg(String message) {
        try {
            JSONObject jsonObject = XmlUtils.parseXml(message);
            if (Objects.equals(jsonObject.getString("MsgType"), MsgTypeEnum.TEXT.getValue())) {
                RecTextMsg recTextMsg = jsonObject.toJavaObject(RecTextMsg.class);
                RecTextMsg returnTextMsg = new RecTextMsg();
                BeanUtils.copyProperties(recTextMsg, returnTextMsg);
                returnTextMsg.setFromUserName(recTextMsg.getToUserName());
                returnTextMsg.setToUserName(recTextMsg.getFromUserName());
                returnTextMsg.setCreateTime(System.currentTimeMillis() / 1000L);
                if (Objects.equals(recTextMsg.getContent(), "中午吃啥")) {
                    returnTextMsg.setContent("尖椒肉丝盖饭!");
                }
                return XmlUtils.convertXml(JSONObject.parseObject(JSON.toJSONString(returnTextMsg), JSONObject.class));
            }
            // todo 其他消息类型的响应

        } catch (Exception e) {
            log.error("MsgServiceImpl receiveMsg -> message : {}, e: {}", message, Throwables.getStackTraceAsString(e));
        }

        return "success";
    }


}
