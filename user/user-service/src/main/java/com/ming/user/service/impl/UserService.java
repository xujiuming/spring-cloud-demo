package com.ming.user.service.impl;

import com.ming.user.api.service.IUserService;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements IUserService {
    @Override
    public String hello(String name) {
        return name + "FFF" + LocalDateTime.now();
    }
}
