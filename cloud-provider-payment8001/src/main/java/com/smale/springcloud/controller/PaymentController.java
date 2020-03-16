package com.smale.springcloud.controller;

import com.smale.springcloud.entities.Payment;
import com.smale.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    /**
     * 新增
     * postman http://localhost:8001/payment/create?serial=smale002
     *
     * @param payment
     * @return
     */
    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果*****: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功", result);
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
            return new CommonResult(200, "查询成功,serverPort:", payment);
        }
        return new CommonResult(444, "没有对应记录,查询ID:" + id, null);
    }
    
}
