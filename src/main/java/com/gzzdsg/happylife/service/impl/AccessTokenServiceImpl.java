package com.gzzdsg.happylife.service.impl;

import com.gzzdsg.happylife.domain.vo.AccessTokenResponse;
import com.gzzdsg.happylife.exception.CustomException;
import com.gzzdsg.happylife.feign.WxFeignClient;
import com.gzzdsg.happylife.service.AccessTokenService;
import com.gzzdsg.happylife.service.KeyService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.gzzdsg.happylife.constant.Constants.*;

/**
 * 访问凭证管理逻辑
 *
 * @author: damei
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private KeyService keyService;

    @Resource
    private WxFeignClient wxFeignClient;


    @Override
    public String getAccessToken() {
        String key = keyService.accessTokenKey();
        // 尝试从缓存取accessToken
        String accessToken = redisTemplate.opsForValue().get(key);
        // 缓存中没有获取到
        if (StringUtils.isEmpty(accessToken)) {
            // 访问微信后台获取
            AccessTokenResponse accessTokenResponse = wxFeignClient.getAccessToken(APP_ACCESS_TOKEN_GRANT_TYPE, APP_ID, APP_SECRET);
            if (accessTokenResponse == null || StringUtils.isEmpty(accessTokenResponse.getAccessToken())) {
                throw new CustomException("获取accessToken失败");
            }
            accessToken = accessTokenResponse.getAccessToken();
            // 缓存凭证
            redisTemplate.opsForValue().set(key, accessToken, accessTokenResponse.getExpiresIn() - 100, TimeUnit.SECONDS);
        }
        return accessToken;
    }
}
