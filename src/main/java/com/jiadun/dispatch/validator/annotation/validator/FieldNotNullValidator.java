package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 验证对象是否不为null.和field != null效果一样
 * @author: hcl
 * @date: created in 2018/1/26 14:32
 */
public class FieldNotNullValidator implements ConstraintValidator<FieldNotNull,Object>{

    private FieldNotNull fieldNotNull;
    @Override
    public void initialize(FieldNotNull fieldNotNull) {
        this.fieldNotNull = fieldNotNull;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if(o == null){
            if(EmptyUtils.isEmpty(fieldNotNull.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s不能为null！",fieldNotNull.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
