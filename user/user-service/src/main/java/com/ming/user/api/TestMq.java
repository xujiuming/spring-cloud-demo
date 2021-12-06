package com.ming.user.api;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestMq {
    @StreamListener("testTopic")
    public void test(@Payload String text){
        System.out.println("mq消费:"+text+":"+ LocalDateTime.now());
    }
}
