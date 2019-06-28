package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldIsNum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 数字验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsNumValidator implements ConstraintValidator<FieldIsNum,Object> {
    private FieldIsNum fieldIsNum;
    @Override
    public void initialize(FieldIsNum fieldIsNum) {
        this.fieldIsNum = fieldIsNum;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(value instanceof Number){
            return true;
        }
        String str = value.toString();
        if(!str.matches("^-?\\d+(\\.\\d+)?$")){
            if(EmptyUtils.isEmpty(fieldIsNum.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s必须是一个数字！",fieldIsNum.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
