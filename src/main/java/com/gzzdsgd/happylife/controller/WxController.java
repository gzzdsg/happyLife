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

@Slf4j
@RestController
public class WxController {

    @GetMapping("/wx")
    public String hello(@RequestParam(value = "signature") String signature,
                        @RequestParam(value = "timestamp") String timestamp,
                        @RequestParam(value = "nonce") String nonce,
                        @RequestParam(value = "echostr") String echostr) {

        List<String> params = Lists.newArrayList(Constants.APP_TOKEN, timestamp, nonce);
        List<String> sortedParams = params.stream().sorted().toList();
        String encodingStr = EncodeUtils.encodeSha1(Joiner.on("").join(sortedParams));
        System.out.println(JSON.toJSONString(params));
        System.out.println(signature);
        System.out.println(encodingStr);
        System.out.println(echostr);
        return echostr;
    }
}
