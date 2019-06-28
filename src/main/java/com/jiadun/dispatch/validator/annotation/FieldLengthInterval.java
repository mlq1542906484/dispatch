package com.jiadun.dispatch.validator.annotation;

import com.jiadun.dispatch.validator.annotation.validator.FieldLengthIntervalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 验证字段长度区间,
 * @author: hcl
 * @date: created in 2018/1/26 17:04
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldLengthIntervalValidator.class)
public @interface FieldLengthInterval {
    /**
     * @description: 指定最小长度
     */
    int minLen();
    /**
     * @description: 指定最大长度
     */
    int maxLen();
    String message() default "";
    String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
