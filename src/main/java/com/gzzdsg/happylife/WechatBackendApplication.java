package com.gzzdsg.happylife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WechatBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatBackendApplication.class, args);
	}

}
