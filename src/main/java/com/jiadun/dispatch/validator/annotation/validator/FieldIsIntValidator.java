package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.StringUtil;
import com.jiadun.dispatch.validator.annotation.FieldIsInt;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: int 验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsIntValidator implements ConstraintValidator<FieldIsInt,Object> {
    private FieldIsInt fieldIsInt;
    @Override
    public void initialize(FieldIsInt fieldIsInt) {
        this.fieldIsInt = fieldIsInt;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(value instanceof Integer){
            return true;
        }
        String str = value.toString();
        str = StringUtil.deleteDecimalAfterZero(str);
        if(!str.matches("^-?\\d+$")){
            if(EmptyUtils.isEmpty(fieldIsInt.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s不是一个整数！",fieldIsInt.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
