package com.smale.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 将RestTemplate注入进Spring容器中
 * RestTemplate: 访问远程http服务的方法
 *      是spring提供的用于访问Rest服务的客户端模板工具集
 *      与RPC远程服务调用各有优劣
 */
@Configuration
public class ApplicationContextConfig {
    
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
    
}
