package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 邮箱验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldEmailValidator implements ConstraintValidator<FieldEmail,String> {
    private FieldEmail fieldEmail;
    @Override
    public void initialize(FieldEmail fieldEmail) {
        this.fieldEmail = fieldEmail;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }

        if(!value.matches(fieldEmail.regex())){
            if(EmptyUtils.isEmpty(fieldEmail.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s格式不正确！", fieldEmail.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
