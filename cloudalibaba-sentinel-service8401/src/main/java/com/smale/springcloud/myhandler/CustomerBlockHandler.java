package com.smale.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.smale.springcloud.entities.CommonResult;

/**
 * 自定义限流处理类
 * 
 */
public class CustomerBlockHandler {

    /**
     * 自定义返回测试类1
     */
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444,"返回测试类1 ,global handlerException----1");
    }

    /**
     * 自定义返回测试类2
     * @param exception
     * @return
     */
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444,"返回测试类2, global handlerException----2");
    }
}
