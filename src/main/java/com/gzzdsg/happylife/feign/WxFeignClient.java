package com.gzzdsg.happylife.feign;

import com.gzzdsg.happylife.domain.vo.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 访问微信服务器
 *
 * @author: damei
 */
@FeignClient(name = "wxFeignClient", url = "https://api.weixin.qq.com")
public interface WxFeignClient {

    /**
     * 获取微信后台接口访问凭证
     *
     * @param grantType 授权类型
     * @param appId     开放平台三方id
     * @param appSecret 开放平台三方密钥
     * @return 公众平台接口访问凭证
     */
    @GetMapping("/cgi-bin/token")
    AccessTokenResponse getAccessToken(@RequestParam("grant_type") String grantType, @RequestParam(value = "appid") String appId, @RequestParam(value = "secret") String appSecret);

}
