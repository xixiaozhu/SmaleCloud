package com.smale.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.smale.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 在@HystrixCommand(fallbackMethod)配置过的走自定义的Fallback
 * 未配置的走全局默认Fallback, @DefaultProperties(defaultFallback)
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;


    /**
     * 测试正常访问连通性
     * 当cloud-provider-hystrix-payment8010服务中断则会调用自己的服务降级方法
     * postman  http://localhost/consumer/payment/hystrix/ok/1001
     *
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    /**
     * 超时访问
     * postman  http://localhost/consumer/payment/hystrix/timeout/1001
     * 
     * @HystrixCommand: 断路器-->服务降级
     *      fallbackMethod: 当前方法若出现内部异常,调用异常,调用超时等等问题,则启用指定超时方法返回给友好提醒至用户
     *      commandProperties的@HystrixProperty: 设置当前线程超时时间(ms)
     *
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        // 该方法指定3秒超时则做降级服务,返回友好提醒给用户
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    /**
     * 超时降级方法FallbackMethod,返回给用户友好界面提醒
     * @param id
     * @return
     */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "--------------启用服务降级,显示友好提醒--------------\n" +
                "消费者80,对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    /**
     * 全局fallback
     *
     * @return
     */
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后重试.o(╥﹏╥)o";
    }

    /**
     * 测试全局Fallback
     *      localhost/consumer/payment/hystrix/timeout_global/1001
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeout_global/{id}")
    @HystrixCommand
    public String paymentInfo_TimeOutGlobal(@PathVariable("id") Integer id) {
        int i = 10/0;
        
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
}
