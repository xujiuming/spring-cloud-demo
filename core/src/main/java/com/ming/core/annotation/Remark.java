package com.ming.core.annotation;

import java.lang.annotation.*;

/**
 * 备注
 *
 * @author ming
 * @date 2021-11-10 09:46:05
 */
@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Remark {
    /**
     * 备注名称
     */
    String name() default "";

    /**
     * 详细描述
     */
    String describe() default "";
}
