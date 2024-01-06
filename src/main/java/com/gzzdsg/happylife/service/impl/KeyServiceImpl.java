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
    public String cacheAllFoodNameKey() {
        return String.format("%s:all:food:name", "set");
    }
}
