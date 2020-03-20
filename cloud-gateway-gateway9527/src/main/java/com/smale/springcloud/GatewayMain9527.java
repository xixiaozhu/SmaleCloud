package com.smale.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Gateway核心要素-->路由,断言,过滤
 *      路由: 类似nginx的请求分发,根据断言规则匹配路由接口, 在yml文件内配置, 也可以编码配置(参考config包下的GatewayConfig.java)
 *      断言: 根据某一规则来进行接口请求匹配, 在yml文件内配置, 规则较多参砍官网或其他资料
 *      过滤: 在请求被路由之前或之后,对请求进行修改,类似AOP拦截功能模式,具体参考filter包下的MyLogGatewayFilter.java配置
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class, args);
    }
}
