package com.jiadun.dispatch.validator.annotation;


import com.jiadun.dispatch.validator.annotation.validator.FieldEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 基于spring的注解验证器
 *                 验证邮箱
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldEmailValidator.class)
public @interface FieldEmail {
    String regex() default "^[a-zA-Z0-9][a-zA-Z0-9_\\-\\.]{0,19}@(?:[a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$";
    String message() default "";
    String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
