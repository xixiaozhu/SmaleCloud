package com.smale.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos配置中心
 * @RefreshScope:   支持nacos的动态刷新
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    /**
     * 查看Nacos上的配置文件信息
     * postman  localhost:3377/config/info
     * @return
     */
    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
