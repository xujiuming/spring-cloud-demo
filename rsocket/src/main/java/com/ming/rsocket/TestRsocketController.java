package com.ming.rsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class TestRsocketController {

    @MessageMapping("test-rsocket-req-res")
    public Mono<String> requestResponse(String message) {
        log.info("测试rsocket req-res模式:{}", message);
        return Mono.just("测试rsocket req-res模式," + message);
    }

    @MessageMapping("test-rsocket-req-stream")
    public Flux<String> requestStream(String message) {
        log.info("测试req-stream模式:{}", message);
        return Flux.range(0, 100).map(m -> "测试req-stream模式" + m + ":" + message + ":" + LocalDateTime.now());
    }

    @MessageMapping("test-rsocket-fire-and-forget")
    public Mono<Void> fireAndForget(String message) {
        log.info("测试fire-and-forget模式:{}", message);
        return Mono.empty();
    }

    @MessageExceptionHandler
    public Mono<String> handlerException(Exception e) {
        return Mono.just(e.getMessage());
    }
}
