package com.ming.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CommonTestController {

    @GetMapping("/test")
    public String test(){
        return "commonTest"+ LocalDateTime.now();
    }
}
