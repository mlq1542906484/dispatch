package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldIsBoolean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 布尔验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsBooleanValidator implements ConstraintValidator<FieldIsBoolean,Object> {
    private FieldIsBoolean fieldIsBoolean;
    @Override
    public void initialize(FieldIsBoolean fieldIsBoolean) {
        this.fieldIsBoolean = fieldIsBoolean;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(value instanceof Boolean){
            return true;
        }
        String str = value.toString();
        if(!str.matches("^true|false$")){
            if(EmptyUtils.isEmpty(fieldIsBoolean.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s必须是true或者false！",fieldIsBoolean.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
