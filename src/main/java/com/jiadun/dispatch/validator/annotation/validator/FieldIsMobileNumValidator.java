package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldIsMobileNum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 手机号验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsMobileNumValidator implements ConstraintValidator<FieldIsMobileNum,String> {
    private FieldIsMobileNum fieldIsMobileNum;
    @Override
    public void initialize(FieldIsMobileNum fieldIsMobileNum) {
        this.fieldIsMobileNum = fieldIsMobileNum;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(!value.matches("^1[0-9]{10}$")){
            if(EmptyUtils.isEmpty(fieldIsMobileNum.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s格式不正确！",fieldIsMobileNum.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
