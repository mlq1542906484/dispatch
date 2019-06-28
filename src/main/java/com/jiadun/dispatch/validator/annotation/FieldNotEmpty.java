package com.jiadun.dispatch.validator.annotation;


import com.jiadun.dispatch.validator.annotation.validator.FieldNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 基于spring的注解验证器
 *                 验证对象是否为不等于null或者""字符串,和EmptyUtils.isNotEmpty()效果一样
 * @author: hcl
 * @date: created in 2018/1/26 14:18
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldNotEmptyValidator.class)
public @interface FieldNotEmpty {
    String message() default "";
    String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
