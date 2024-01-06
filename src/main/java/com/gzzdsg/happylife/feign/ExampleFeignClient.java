package com.gzzdsg.happylife.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 使用feign的示例
 *
 * @author: damei
 */
@FeignClient(name = "example-service", url = "http://example.com")
public interface ExampleFeignClient {

    @GetMapping("/example-endpoint")
    String getExampleData();

}
