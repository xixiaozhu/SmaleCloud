package com.smale.springcloud.controller;

import com.smale.springcloud.entities.CommonResult;
import com.smale.springcloud.entities.Payment;
import com.smale.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Feign对外提供服务
 */
@RestController
@Slf4j
public class OrderFeignController {
    
    @Resource
    private PaymentFeignService paymentFeignService;

    /**
     * Feign调用自己service层,对外提供服务接口
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 模拟超时请求, feign服务默认1秒超时,调用服务端接口时若超过1秒则返回错误信息
     * 可在yml文件中设置超时时间
     * @return
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon, 客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
