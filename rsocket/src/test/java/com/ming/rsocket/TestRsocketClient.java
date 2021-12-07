package com.ming.rsocket;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.rsocket.RSocketRequester;

public class TestRsocketClient {
    RSocketRequester requester = RSocketRequester.builder().tcp("localhost", 20000);

    @Test
    public void testReqRes(){
        String str = requester.route("test-rsocket-req-res")
                .data("fffffffffffff")
                .retrieveMono(String.class)
                .block();
        System.out.println(str);
    }

}
