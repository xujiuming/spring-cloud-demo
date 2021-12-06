package com.ming.user.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 测试mq 服务
 *
 * @author ming
 * @date 2021-12-06 14:23:49
 */

public interface TestMqService {
//    @Bean()
//    public Consumer<String> testTopic() {
//        log.info("消费者");
//        return msg -> log.info("消费:" + msg);
//    }

    @Input("testTopic-in-0")
    SubscribableChannel input();
}
