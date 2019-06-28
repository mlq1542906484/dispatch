package com.jiadun.dispatch.validator.annotation;


import com.jiadun.dispatch.validator.annotation.validator.FieldNumberGreaterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 字段值验证,字段值必须大于或等于指定的值
 * @author: hcl
 * @date: created in 2018/1/26 16:53
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldNumberGreaterValidator.class)
public @interface FieldNumberGreater {
    /**
     * @description: 指定最小值
     */
    double minVal();
    String fieldName();
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
