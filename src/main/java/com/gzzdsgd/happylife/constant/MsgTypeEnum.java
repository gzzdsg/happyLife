package com.gzzdsgd.happylife.constant;

import lombok.Getter;

/**
 * 消息类型
 *
 * @author: damei
 */
public enum MsgTypeEnum {
    TEXT("text", "文本类型");

    @Getter
    private String value;

    private String desc;

    MsgTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
