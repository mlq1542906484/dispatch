package com.jiadun.dispatch.validator.annotation;


import com.jiadun.dispatch.validator.annotation.validator.FieldMatchingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 匹配验证器,只有当内容匹配数组中的一个时返回true
 * @author: hcl
 * @date: created in 2018/1/26 19:54
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldMatchingValidator.class)
public @interface FieldMatching {

    /**
     * @description: 是否区分大小写,(默认为区分)
     * @author: hcl
     * @date: created in 2018/1/26 20:11
     */
    boolean isCaseSensitive() default true;
    /**
     * @description: 内容集合
     * @author: hcl
     * @date: created in 2018/1/26 20:26
     */
    String[] array();
    String fieldName();
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
