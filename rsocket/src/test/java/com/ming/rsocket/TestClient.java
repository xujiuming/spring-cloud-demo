package com.ming.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestClient {
    public static void main(String[] args) {
        RSocket clientRSocket =
                RSocketConnector.create()
                        // Enable Zero Copy
                        .payloadDecoder(PayloadDecoder.ZERO_COPY)
                        .connect(TcpClientTransport.create(20000))
                        .name("test-rsocket-req-res")
                        .block();
//        clientRSocket.requestResponse(DefaultPayload.create("test-rsocket-req-res"))
//                .subscribe(s-> System.out.println(s.data().toString()));

        try {
            Mono<Payload> s = clientRSocket.requestResponse(DefaultPayload.create("ffffffffffff", "test-rsocket-req-res"));
            s.doOnNext(p -> System.out.println(p.getDataUtf8())).block();
        } finally {
            clientRSocket.dispose();
        }
    }
}
