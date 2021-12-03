package com.ming.common.api.service.impl;

import com.ming.common.api.service.ITestService;
import com.ming.core.annotation.DubboService;

import java.time.LocalDateTime;

@DubboService
public class TestService implements ITestService {
    @Override
    public String hello(String name) {
        return name + "::" + LocalDateTime.now();
    }
}
