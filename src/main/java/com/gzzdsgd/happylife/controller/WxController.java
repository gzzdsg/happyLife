package com.gzzdsgd.happylife.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.gzzdsgd.happylife.constant.Constants;
import com.gzzdsgd.happylife.util.EncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 微信官方对接接口
 *
 * @author damei
 */
@Slf4j
@RestController
public class WxController {

    /**
     * 修改微信公众号配置后的验证开发者服务器
     *
     * @param signature 参数签名
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @param echostr   返回值
     * @return 返回值
     */
    @GetMapping("/wx")
    public String wx(@RequestParam(value = "signature") String signature,
                     @RequestParam(value = "timestamp") String timestamp,
                     @RequestParam(value = "nonce") String nonce,
                     @RequestParam(value = "echostr") String echostr) {

        // 将参与验签的参数按照自然序排序
        List<String> params = Lists.newArrayList(Constants.APP_TOKEN, timestamp, nonce);
        List<String> sortedParams = params.stream().sorted().toList();
        // 三个参数拼接后进行sha1加密
        String encodingStr = EncodeUtils.encodeSha1(Joiner.on("").join(sortedParams));
        // 成功匹配到则将echostr返回
        if (Objects.equals(encodingStr, signature)) {
            return echostr;
        }
        log.error("WxController wx -> params : {}, encodingStr : {},  signature : {}, echostr : {}",
                JSON.toJSONString(params), encodingStr, signature, echostr);
        return "error";
    }
}
