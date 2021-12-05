package com.ming.user.api;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MqService {

    @Output("testTopic")
    MessageChannel out();

    @Input("testTopic")
    SubscribableChannel in();
}
