package com.gzzdsg.happylife.constant;

import com.gzzdsg.happylife.exception.CustomException;
import lombok.Getter;

import java.util.Objects;

/**
 * 事件类型
 *
 * @author: damei
 */
public enum EventTypeEnum {
    SUBSCRIBE("subscribe", "关注事件"),
    UNSUBSCRIBE("unsubscribe", "取消关注")
    ;

    @Getter
    private String value;

    private String desc;

    EventTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static EventTypeEnum getByValue(String value) {
        for (EventTypeEnum eventTypeEnum : EventTypeEnum.values()) {
            if (Objects.equals(eventTypeEnum.getValue(), value)) {
                return eventTypeEnum;
            }
        }
        throw new CustomException("非法的事件类型");
    }
}
