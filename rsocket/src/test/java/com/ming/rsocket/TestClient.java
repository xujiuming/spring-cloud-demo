package com.ming.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

public class TestClient {
    public static void main(String[] args) {
        RSocket clientRSocket =
                RSocketConnector.create()
                        // Enable Zero Copy
                        .payloadDecoder(PayloadDecoder.ZERO_COPY)
                        .connect(TcpClientTransport.create(20000))
                        .block();
//        clientRSocket.requestResponse(DefaultPayload.create("test-rsocket-req-res"))
//                .subscribe(s-> System.out.println(s.data().toString()));

        try {
            Flux<Payload> s = clientRSocket.requestStream(DefaultPayload.create("ffffffffffff", "test-rsocket-req-res"));
            s.take(10).doOnNext(p -> System.out.println(p.getDataUtf8())).blockLast();
        } finally {
            clientRSocket.dispose();
        }
    }
}
