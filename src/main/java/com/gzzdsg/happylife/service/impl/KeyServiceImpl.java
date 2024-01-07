package com.gzzdsg.happylife.service.impl;

import com.gzzdsg.happylife.service.KeyService;
import org.springframework.stereotype.Service;

/**
 * 各种rediskey
 *
 * @author: damei
 */
@Service
public class KeyServiceImpl implements KeyService {

    @Override
    public String cacheAllFoodNameKey(String openId) {
        return String.format("%s:all:food:name:%s", "set", openId);
    }
}
