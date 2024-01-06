package com.gzzdsg.happylife.constant;

import com.gzzdsg.happylife.exception.CustomException;
import lombok.Getter;

import java.util.Objects;

/**
 * 消息类型
 *
 * @author: damei
 */
public enum MsgTypeEnum {
    TEXT("text", "文本类型"),
    EVENT("event", "事件类型");

    @Getter
    private String value;

    private String desc;

    MsgTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static MsgTypeEnum getByValue(String value) {
        for (MsgTypeEnum msgTypeEnum : MsgTypeEnum.values()) {
            if (Objects.equals(msgTypeEnum.getValue(), value)) {
                return msgTypeEnum;
            }
        }
        throw new CustomException("非法的消息类型");
    }
}
