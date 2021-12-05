package com.ming.user;


import com.ming.user.api.MqService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(MqService.class)
public class StartUser {
    public static void main(String[] args) {
        SpringApplication.run(StartUser.class, args);
    }
}
