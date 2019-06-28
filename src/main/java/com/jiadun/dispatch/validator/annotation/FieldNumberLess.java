package com.jiadun.dispatch.validator.annotation;


import com.jiadun.dispatch.validator.annotation.validator.FieldNumberLessValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 字段值验证,字段值必须小于或等于指定的值
 * @author: hcl
 * @date: created in 2018/1/26 16:19
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldNumberLessValidator.class)
public @interface FieldNumberLess {
    /**
     * @description: 指定最大值
     */
    double maxVal();
    String fieldName();
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
