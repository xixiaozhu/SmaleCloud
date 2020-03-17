package com.smale.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡路由规则类
 * 该类不能在@ComponentScan能扫描到的包内(@SpringBootApplication集成了@ComponentScan注解)
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 随机
        return new RoundRobinRule();
        
//        return new WeightedResponseTimeRule();
    }
}
