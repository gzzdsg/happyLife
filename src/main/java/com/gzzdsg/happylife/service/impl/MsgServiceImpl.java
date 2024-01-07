package com.gzzdsg.happylife.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.Throwables;
import com.gzzdsg.happylife.constant.EventTypeEnum;
import com.gzzdsg.happylife.constant.MsgTypeEnum;
import com.gzzdsg.happylife.domain.po.Food;
import com.gzzdsg.happylife.domain.vo.RecEventMsg;
import com.gzzdsg.happylife.domain.vo.RecLocationMsg;
import com.gzzdsg.happylife.domain.vo.RecTextMsg;
import com.gzzdsg.happylife.service.FoodService;
import com.gzzdsg.happylife.service.KeyService;
import com.gzzdsg.happylife.service.MsgService;
import com.gzzdsg.happylife.util.XmlUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    private FoodService foodService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private KeyService keyService;


    @Override
    public String receiveMsg(String message) {
        try {
            // 解析xml消息
            JSONObject jsonObject = XmlUtils.parseXml(message);
            // 解析消息类型
            MsgTypeEnum msgType = MsgTypeEnum.getByValue(jsonObject.getString("MsgType"));
            switch (msgType) {
                // 如果是文本消息
                case TEXT -> {
                    RecTextMsg recTextMsg = jsonObject.toJavaObject(RecTextMsg.class);
                    RecTextMsg replyTextMsg = new RecTextMsg();
                    BeanUtils.copyProperties(recTextMsg, replyTextMsg);
                    replyTextMsg.setFromUserName(recTextMsg.getToUserName());
                    replyTextMsg.setToUserName(recTextMsg.getFromUserName());
                    replyTextMsg.setCreateTime(System.currentTimeMillis() / 1000L);
                    // 根据文本内容做出不同的处理
                    String reply = this.textMsgHandler(recTextMsg);
                    replyTextMsg.setContent(reply);
                    return XmlUtils.convertXml(replyTextMsg);
                }
                // 地理位置消息
                case LOCATION -> {
                    RecLocationMsg locationMsg = jsonObject.toJavaObject(RecLocationMsg.class);
                    log.info("receiveMsg -> locationMsg : {}", JSON.toJSONString(locationMsg));
                    return "success";
                }
                // 如果是事件消息
                case EVENT -> {
                    RecEventMsg recEventMsg = jsonObject.toJavaObject(RecEventMsg.class);
                    EventTypeEnum eventTypeEnum = EventTypeEnum.getByValue(recEventMsg.getEvent());
                    switch (eventTypeEnum) {
                        // 关注事件
                        case SUBSCRIBE -> {
                            // 这里做了自动打招呼处理
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
     * @param recTextMsg 收到的文本消息消息
     * @return 回复内容
     */
    private String textMsgHandler(RecTextMsg recTextMsg) {
        if (Objects.equals(recTextMsg.getContent(), "中午吃啥")) {
            String openId = recTextMsg.getFromUserName();
            String key = keyService.cacheAllFoodNameKey(openId);
            Boolean exists = redisTemplate.hasKey(key);
            if (exists == null || !exists) {
                List<Food> allFoods = foodService.findAllFood(openId);
                if (CollectionUtils.isEmpty(allFoods)) {
                    return "不知道哇。";
                }
                for (Food food : allFoods) {
                    redisTemplate.opsForSet().add(key, food.getName());
                }
                redisTemplate.expire(key, 10, TimeUnit.MINUTES);
            }
            return redisTemplate.opsForSet().randomMember(key) + "！";
        }
        return recTextMsg.getContent();
    }


}
