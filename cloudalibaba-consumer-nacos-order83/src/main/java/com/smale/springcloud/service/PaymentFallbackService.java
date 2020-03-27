package com.smale.springcloud.service;

import com.smale.springcloud.entities.CommonResult;
import com.smale.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * Sentinel结合OpenFeign服务降级测试
 */
@Component
public class PaymentFallbackService implements PaymentService {
    
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
