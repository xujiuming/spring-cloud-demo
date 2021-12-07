package com.ming.rsocket;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.rsocket.RSocketRequester;

public class TestRsocketClient {
    RSocketRequester requester = RSocketRequester.builder().tcp("localhost", 20000);

    @Test
    public void testReqRes() {
        String str = requester.route("test-rsocket-req-res")
                .data("fffffffffffff")
                .retrieveMono(String.class)
                .block();
        System.out.println(str);
    }

    @Test
    public void testReqStream() {
        requester.route("test-rsocket-req-stream")
                .data("fffffffffffffffffffffffffff")
                .retrieveFlux(String.class)
                .map(m -> {
                    System.out.println(m);
                    return m;
                })
                .blockLast();

    }

    @Test
    public void testFireAndForget() {
        requester.route("test-rsocket-fire-and-forget")
                .data("fffffffffffffffffffffffffffffffffffff")
                .send()
                .block();
    }
}
