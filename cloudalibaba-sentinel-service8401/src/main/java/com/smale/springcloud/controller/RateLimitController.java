package com.smale.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.smale.springcloud.entities.CommonResult;
import com.smale.springcloud.entities.Payment;
import com.smale.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 非持久化,关闭该项目则Sentinel控制台内配置的一系列限流方案会消失
 * @SentinelResource注解内参数说明
 *      value="热点规则资源名"
 *      blockHandler="指定方法名"  --> 负责Sentinel控制台的配置违规,在请求量达到Sentinel的配置时候跳转至指定方法
 *      fallback="指定方法名"  --> 负责业务异常,当业务内抛异常跳转至指定方法
 *      blockHandlerClass=function.class --> 自定义返回类时指定该类.class
 *      
 * blockHandler检测配置异常,fallback检测java/业务异常,当两个同时都存在,则都会生效
 * 但是请求还未来到业务内就已经被Sentinel流控配置所监视,因此会先抛QPS的拦截信息
 */
@RestController
public class RateLimitController {

    /**
     * 按资源名称限流
     * @return
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    /**
     * 按url限流
     * @return
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200,"按url限流测试OK", new Payment(2020L,"serial002"));
    }

    /**
     * 自定义返回类,
     * @return
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,     //指定返回的类
            blockHandler = "handlerException2")                 //指定该类下返回的方法
    public CommonResult customerBlockHandler() {
        
        return new CommonResult(200,"自定义message", new Payment(2020L,"serial003"));
    }
}