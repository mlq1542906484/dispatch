package com.jiadun.dispatch.validator.annotation;


import com.jiadun.dispatch.validator.annotation.validator.FieldNumberIntervalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 验证字段值区间,
 * @author: hcl
 * @date: created in 2018/1/26 17:04
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldNumberIntervalValidator.class)
public @interface FieldNumberInterval {
    /**
     * @description: 指定最小值
     */
    double minVal();
    /**
     * @description: 指定最大值
     */
    double maxVal();
    String fieldName();
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
