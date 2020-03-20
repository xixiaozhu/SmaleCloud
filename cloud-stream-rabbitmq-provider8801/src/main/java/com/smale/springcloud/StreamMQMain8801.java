package com.smale.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringCloud-Stream常用注解说明
 *      @Input: 标识输入通道,通过该输入通道接收到的消息进入应用程序
 *      @Output:    输出通道,发出的消息将通过该通道离开应用程序
 *      @StreamListener:    监听队列,用于消费者的消息接收
 *      @EnableBinding:     将信道Channel和Exchange绑定一起
 */
@SpringBootApplication
public class StreamMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class, args);
    }
}
