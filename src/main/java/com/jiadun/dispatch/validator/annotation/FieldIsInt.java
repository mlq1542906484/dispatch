package com.jiadun.dispatch.validator.annotation;

import com.jiadun.dispatch.validator.annotation.validator.FieldIsIntValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 基于spring的注解验证器
 *                 是否是int
 * @author: caiping
 * @date: created in 2018/1/26 19:14
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldIsIntValidator.class)
public @interface FieldIsInt {
    String message() default "";
    String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
