package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldIsDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 小数验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsDecimalValidator implements ConstraintValidator<FieldIsDecimal,Object> {
    private FieldIsDecimal fieldIsDecimal;
    @Override
    public void initialize(FieldIsDecimal fieldIsDecimal) {
        this.fieldIsDecimal = fieldIsDecimal;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        String str = value.toString();
        if(!str.matches("^-?\\d+\\.\\d+$")){
            if(EmptyUtils.isEmpty(fieldIsDecimal.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s必须是小数格式！",fieldIsDecimal.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
