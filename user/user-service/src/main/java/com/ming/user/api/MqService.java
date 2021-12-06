package com.ming.user.api;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
public class MqService {
    @Bean
    public Consumer<String> testTopic() {
        return System.out::println;
    }
}
