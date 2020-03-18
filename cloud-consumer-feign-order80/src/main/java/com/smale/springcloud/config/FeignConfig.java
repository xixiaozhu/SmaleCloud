package com.smale.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign日志控制设置
 **/
@Configuration
public class FeignConfig {

    /**
     * feignClient配置日志级别
     * 
     * NONE:    默认不显示任何日志
     * BASIC:   仅记录请求方法,url,响应状态码和执行时间
     * HEADERS: 在BASIC上多了请求和响应的头信息
     * FULL:    在HEADERS上多了请求和响应的正文及元数据,最详细
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
