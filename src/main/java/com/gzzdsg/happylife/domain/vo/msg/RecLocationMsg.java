package com.gzzdsg.happylife.domain.vo.msg;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 微信位置信息消息
 *
 * @author: damei
 */
@Data
public class RecLocationMsg {

    /**
     * 地理位置纬度
     */
    @JSONField(name = "Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @JSONField(name = "Location_Y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @JSONField(name = "Scale")
    private Integer scale;

    /**
     * 地理位置信息
     */
    @JSONField(name = "Label")
    private String label;

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

    /**
     * 消息id，64位整型
     */
    @JSONField(name = "MsgId")
    private Long msgId;

    /**
     * 消息的数据ID（消息如果来自文章时才有）
     */
    @JSONField(name = "MsgDataId")
    private Long msgDataId;

    /**
     * 多图文时第几篇文章，从1开始（消息如果来自文章时才有）
     */
    @JSONField(name = "Idx")
    private Integer idx;

}
