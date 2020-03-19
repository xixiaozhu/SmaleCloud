package com.smale.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 实现需要降级服务的Service接口,做统一降级处理
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    /**
     * 统一正常处理
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back-paymentInfo_OK, O(∩_∩)O哈哈~";
    }

    /**
     * 统一异常处理
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----PaymentFallbackService fall back-paymentInfo_TimeOut, o(╥﹏╥)o";
    }
}
