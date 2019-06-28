package com.jiadun.dispatch.validator.annotation;

import com.jiadun.dispatch.validator.annotation.validator.FieldLengthLessValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 字段长度验证,字段长度必须小于或等于指定的长度
 * @author: hcl
 * @date: created in 2018/1/26 16:19
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldLengthLessValidator.class)
public @interface FieldLengthLess {
    /**
     * @description: 指定最大长度
     */
    int maxLen();
    String message() default "";
    String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
