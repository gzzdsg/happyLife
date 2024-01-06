package com.gzzdsgd.happylife.service;

/**
 * 消息逻辑处理
 *
 * @author: damei
 */
public interface MsgService {

    /**
     * 接收消息
     *
     * @param message 消息文本
     * @return 返回内容
     */
    String receiveMsg(String message);

}
