package com.smale.springcloud.controller;

import com.smale.springcloud.entities.CommonResult;
import com.smale.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 服务消费者
 */
@RestController
@Slf4j
public class OrderController {

    /**
     * 单机固定找8001服务
     */
//    public static final String PAYMENT_URL = "http://localhost:8001";

    /**
     * 使用Eureka负载均衡
     */
    public static final String PAYMENT_SRV = "http://CLOUD-PAYMENT-SERVICE";
    
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
        // 使用restTemplate.postForObject远程http方式调用, 单机
//        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        
        // 使用Eureka负载均衡访问服务提供者集群
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_SRV + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }

    /**
     * 远程调用服务提供者8001查询数据
     * 
     * postman localhost/consumer/payment/get/1001
     * 
     * @param id id
     * @return CommonResult<Payment> Json字符串
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
//        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        return restTemplate.getForObject(PAYMENT_SRV + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * postman localhost/consumer/payment/getForEntity/1001
     * @param id
     * @return CommonResult<Payment> 详细信息
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_SRV + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败", null);
        }
    }

    // 使用restTemplate完成zipkin + sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject(PAYMENT_SRV + "/payment/zipkin", String.class);
        return result;
    }
    
}
