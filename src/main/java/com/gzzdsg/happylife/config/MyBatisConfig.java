package com.gzzdsg.happylife.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置
 *
 * @author: damei
 */
@Configuration
@MapperScan("com.gzzdsg.happylife.mapper")
public class MyBatisConfig {

}