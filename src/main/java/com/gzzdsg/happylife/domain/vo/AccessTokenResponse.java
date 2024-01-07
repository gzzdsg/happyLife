package com.gzzdsg.happylife.domain.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 访问凭证返回值
 *
 * @author: damei
 */
@Data
public class AccessTokenResponse {

    /**
     * 访问凭证
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * 失效剩余时间
     */
    @JSONField(name = "expires_in")
    private Long expiresIn;

}
