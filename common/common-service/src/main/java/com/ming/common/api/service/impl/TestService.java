package com.ming.common.api.service.impl;

import com.ming.common.api.service.ITestService;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

@Service
public class TestService implements ITestService {
    @Override
    public String hello(String name) {
        return name + "::" + LocalDateTime.now();
    }
}
