package com.smale.springcloud.controller;

import com.smale.springcloud.entities.CommonResult;
import com.smale.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 服务消费者
 */
@RestController
@Slf4j
public class OrderController {
    
    public static final String PAYMENT_URL = "http://localhost:8001";
    
    @Resource
    private RestTemplate restTemplate;

    /**
     * 远程调用服务提供者8001新增数据
     * 
     * postman localhost/consumer/payment/create
     *          使用body下的表单提交或者json格式提交均可
     * 
     * @param payment Payment
     * @return CommonResult<Payment>
     */
    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        // 使用restTemplate.postForObject远程http方式调用
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }

    /**
     * 远程调用服务提供者8001查询数据
     * 
     * postman localhost/consumer/payment/get/1001
     * 
     * @param id id
     * @return CommonResult<Payment>
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
    
}
