package com.gzzdsgd.happylife.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信文本消息消息
 *
 * @author: damei
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RecTextMsg extends BaseMsg{

    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 消息的数据ID（消息如果来自文章时才有）
     */
    private Long msgDataId;

    /**
     * 多图文时第几篇文章，从1开始（消息如果来自文章时才有）
     */
    private Integer idx;

}
