package com.gzzdsg.happylife.service.impl;

import com.gzzdsg.happylife.service.KeyService;
import org.springframework.stereotype.Service;

/**
 * 各种redis key
 *
 * @author: damei
 */
@Service
public class KeyServiceImpl implements KeyService {

    @Override
    public String allFoodNameKey(String openId) {
        return String.format("%s:all:food:name:%s", "set", openId);
    }

    @Override
    public String accessTokenKey() {
        return String.format("%s:access:token", "value");
    }
}
