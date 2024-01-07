package com.gzzdsg.happylife.domain.vo.msg;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 微信事件消息
 *
 * @author: damei
 */
@Data
public class RecEventMsg {

    /**
     * 事件类型
     */
    @JSONField(name = "Event")
    private String event;

    /**
     * 开发者微信号
     */
    @JSONField(name = "ToUserName")
    private String toUserName;

    /**
     * 发送方账号（一个OpenID）
     */
    @JSONField(name = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @JSONField(name = "CreateTime")
    private Long createTime;

    /**
     * 消息类型，文本为text
     */
    @JSONField(name = "MsgType")
    private String msgType;

}
