package com.ming.core.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 描述
 *
 * @author ming
 * @date 2020-04-21 13:18:14
 */
@Target(value = {ElementType.FIELD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface Describe {
    /**
     * 描述内容
     */
    String value();
}
