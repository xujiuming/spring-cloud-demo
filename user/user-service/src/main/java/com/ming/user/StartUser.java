package com.ming.user;


import com.ming.user.api.TestMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({TestMqService.class})
@Slf4j
public class StartUser {
    public static void main(String[] args) {
        SpringApplication.run(StartUser.class, args);
    }


    @StreamListener("testTopic-in-0")
    public void re(String message){
        log.info("消费111:+"+message);
    }
}
