package com.gzzdsg.happylife.service;

/**
 * 各种redis key
 * @author: damei
 */
public interface KeyService {

    /**
     * 缓存全部食物名称
     *
     * set:all:food:name:openId
     */
    String allFoodNameKey(String openId);

    /**
     * 缓存公众平台访问凭证
     *
     * value:access:token
     */
    String accessTokenKey();

}
