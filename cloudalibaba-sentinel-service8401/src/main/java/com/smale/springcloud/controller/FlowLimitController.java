package com.smale.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 测试各种限流类型
 */
@RestController
@Slf4j
public class FlowLimitController {
    
    /****  测试流控效果  ****/
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "------testB";
    }


    @GetMapping("/testD")
    public String testD() {
        
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        log.info("testD 测试RT");

        log.info("testD 异常比例");
        int age = 10/0;
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        log.info("testE 测试异常数");
        int age = 10/0;
        return "------testE 测试异常数";
    }


    /**
     * 测试热点限流
     * @param p1 限流参数
     * @param p2 不限流参数
     * @return StringResult
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        
        // java运行时异常不在限流服务降级方法内,限流只针对规则不针对异常
//        int age = 10/0;
        String s = "------testHotKey" + "p1=" + p1 + "p2=" + p2;
        return s;
    }
    /**
     * 限流方法
     * @param p1
     * @param p2
     * @param exception
     * @return
     */
    public String deal_testHotKey (String p1, String p2, BlockException exception){
        //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
        return "deal_testHotKey友好界面!!!o(╥﹏╥)o";
    }

}
