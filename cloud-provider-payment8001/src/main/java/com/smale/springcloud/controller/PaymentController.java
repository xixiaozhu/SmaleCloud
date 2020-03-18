package com.smale.springcloud.controller;

import com.smale.springcloud.entities.CommonResult;
import com.smale.springcloud.entities.Payment;
import com.smale.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    
    @Resource
    private PaymentService paymentService;

    /**
     * 方便消费者调用服务方时显示调用的是哪一个服务者
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 可以通过服务发现来获得该服务的信息
     */
    @Resource
    private DiscoveryClient discoveryClient;


    /**
     * 新增
     * postman 通过@RequestBody方式请求则不能通过form-data 表单提交的方式进行传参
     *         需要改成json方式提交
     *
     * @param payment
     * @return
     */
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果*****: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功, serverPort = " + serverPort, result);
        }
        return new CommonResult(444, "插入数据库失败", null);
    }

    /**
     * 查询
     * postman http://localhost:8001/payment/get/1001
     *
     * @param id
     * @return
     */
    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果*****: " + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功, serverPort = " + serverPort, payment);
        }
        return new CommonResult(444, "没有对应记录,查询ID:" + id, null);
    }
    
    /**
     * DiscoveryClient: 获取注册在Eureka上的服务的信息
     */
    @GetMapping("/payment/discovery")
    public Object discovery(){
        
        // 获取注册在Eureka上的服务名称列表
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("element = {}", element);
        }

        // 获取注册在Eureka上的所有的服务具体信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        
        return this.discoveryClient;
    }
    
}
