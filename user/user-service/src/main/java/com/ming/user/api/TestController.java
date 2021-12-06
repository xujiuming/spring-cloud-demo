package com.ming.user.api;

import com.ming.common.api.service.ITestService;
import org.apache.dubbo.config.annotation.Reference;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
    private ITestService testService;
    @Autowired
    private MqService mqService;

    @GetMapping("/test")
    public String test(){
        Message<String> message= MessageBuilder
                .withPayload("testTopic消息")
                .build()
                ;
        mqService.out().send(message);
        return testService.hello("userTest");
    }
}
