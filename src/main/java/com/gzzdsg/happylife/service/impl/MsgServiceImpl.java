package com.gzzdsg.happylife.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.Throwables;
import com.gzzdsg.happylife.constant.EventTypeEnum;
import com.gzzdsg.happylife.constant.MsgTypeEnum;
import com.gzzdsg.happylife.domain.RecEventMsg;
import com.gzzdsg.happylife.domain.RecTextMsg;
import com.gzzdsg.happylife.domain.po.Food;
import com.gzzdsg.happylife.mapper.FoodMapper;
import com.gzzdsg.happylife.service.MsgService;
import com.gzzdsg.happylife.util.RandomUtils;
import com.gzzdsg.happylife.util.XmlUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.gzzdsg.happylife.constant.Constants.REPLY_BY_SUBSCRIBE;

/**
 * 消息逻辑处理
 *
 * @author: damei
 */
@Slf4j
@Service
public class MsgServiceImpl implements MsgService {

    @Resource
    private FoodMapper foodMapper;


    @Override
    public String receiveMsg(String message) {
        try {
            JSONObject jsonObject = XmlUtils.parseXml(message);
            MsgTypeEnum msgType = MsgTypeEnum.getByValue(jsonObject.getString("MsgType"));
            switch (msgType) {
                case TEXT -> {
                    RecTextMsg recTextMsg = jsonObject.toJavaObject(RecTextMsg.class);
                    RecTextMsg replyTextMsg = new RecTextMsg();
                    BeanUtils.copyProperties(recTextMsg, replyTextMsg);
                    replyTextMsg.setFromUserName(recTextMsg.getToUserName());
                    replyTextMsg.setToUserName(recTextMsg.getFromUserName());
                    replyTextMsg.setCreateTime(System.currentTimeMillis() / 1000L);
                    // 根据文本内容做出不同的处理
                    String reply = this.textMsgHandler(recTextMsg.getContent());
                    replyTextMsg.setContent(reply);
                    return XmlUtils.convertXml(replyTextMsg);
                }
                case EVENT -> {
                    RecEventMsg recEventMsg = jsonObject.toJavaObject(RecEventMsg.class);
                    EventTypeEnum eventTypeEnum = EventTypeEnum.getByValue(recEventMsg.getEvent());
                    switch (eventTypeEnum) {
                        case SUBSCRIBE -> {
                            RecTextMsg msg = RecTextMsg.getInstance(recEventMsg.getFromUserName(), recEventMsg.getToUserName(), REPLY_BY_SUBSCRIBE);
                            return XmlUtils.convertXml(msg);
                        }
                        case UNSUBSCRIBE -> {
                            // todo 取消关注相关操作
                        }
                        // todo 其他事件类型的响应
                    }
                }
                // todo 其他消息类型的响应
            }

        } catch (Exception e) {
            log.error("MsgServiceImpl receiveMsg -> message : {}, e: {}", message, Throwables.getStackTraceAsString(e));
        }

        return "success";
    }

    /**
     * 文本消息处理方法
     *
     * @param msg 消息内容
     * @return 回复内容
     */
    private String textMsgHandler(String msg) {
        if (Objects.equals(msg, "中午吃啥")) {
            List<Food> allFoods = foodMapper.getAllFoods();
            if (CollectionUtils.isEmpty(allFoods)) {
                return "不知道哇。";
            }
            return allFoods.get(RandomUtils.randomInt(allFoods.size())).getName();
        }
        return msg;
    }


}
