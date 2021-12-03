package com.ming.core.annotation;

import org.apache.dubbo.config.annotation.Service;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Service
public @interface DubboService {
}
