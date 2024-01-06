package com.gzzdsg.happylife.service;

/**
 * 各种redis key
 * @author: damei
 */
public interface KeyService {

    /**
     * 缓存全部食物名称
     *
     * set:all:food:name
     */
    String cacheAllFoodNameKey();

}
