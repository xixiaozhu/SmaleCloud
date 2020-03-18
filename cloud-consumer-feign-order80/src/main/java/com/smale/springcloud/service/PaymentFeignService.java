package com.smale.springcloud.service;

import com.smale.springcloud.entities.CommonResult;
import com.smale.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign的service层
 * 做接口绑定,绑定其他服务提供者的service
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * 绑定服务8001提供者的接口作为自己的服务接口
     * @param id
     * @return
     */
    @GetMapping(value = "payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 模拟feign超时
     *
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
