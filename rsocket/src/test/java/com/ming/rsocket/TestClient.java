package com.ming.rsocket;

import io.rsocket.RSocket;
import io.rsocket.core.RSocketClient;
import io.rsocket.transport.netty.client.TcpClientTransport;

public class TestClient {
    public static void main(String[] args) {
        RSocketClient client = RSocketClient.from(TcpClientTransport.create(20000));
    }
}
