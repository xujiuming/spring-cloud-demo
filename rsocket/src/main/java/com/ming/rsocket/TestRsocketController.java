package com.ming.rsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class TestRsocketController {

    @MessageMapping("test-rsocket-req-res")
    String requestResponse(String message) {
        log.info("测试rsocket req-res模式:{}", message);
        return "测试rsocket req-res模式," + message;
    }

}
