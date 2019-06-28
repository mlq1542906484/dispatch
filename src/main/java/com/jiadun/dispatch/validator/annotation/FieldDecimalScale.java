package com.jiadun.dispatch.validator.annotation;


import com.jiadun.dispatch.validator.annotation.validator.FieldDecimalScaleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 字段小数位数只能小于maxScale的
 * @author: caiping
 * @date: created in 2018/1/26 19:30
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldDecimalScaleValidator.class)
public @interface FieldDecimalScale {
    int maxScale();
    String message() default "";
    String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
