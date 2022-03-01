package com.ming.common.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RefreshScope
public class CommonTestController {

    @Value("${test.config}")
    private String testConfig;

    @GetMapping("/test")
    public String test() {
        return "commonTest" + LocalDateTime.now();
    }

    @GetMapping("/test-config")
    public String testConfig() {
        return testConfig;
    }
}
