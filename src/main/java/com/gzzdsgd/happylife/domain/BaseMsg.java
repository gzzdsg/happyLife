package com.gzzdsgd.happylife.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信消息基类
 *
 * @author: damei
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseMsg {

    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方账号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private Long createTime;

    /**
     * 消息类型，文本为text
     */
    private String msgType;

    /**
     * 消息id，64位整型
     */
    private Long msgId;

}
