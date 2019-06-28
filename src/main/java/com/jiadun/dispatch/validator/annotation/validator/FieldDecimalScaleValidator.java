package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldDecimalScale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description:
 * @author: caiping
 * @date: created in 2018/1/26 19:31
 */
public class FieldDecimalScaleValidator implements ConstraintValidator<FieldDecimalScale,Object> {
    private FieldDecimalScale fieldDecimalScale;

    @Override
    public void initialize(FieldDecimalScale fieldDecimalScale) {
        this.fieldDecimalScale = fieldDecimalScale;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }
        String str = value.toString();
        if(!str.matches(String.format("^-?\\d+(\\.\\d{1,%d})?$",fieldDecimalScale.maxScale()))){
            if(EmptyUtils.isEmpty(fieldDecimalScale.message())){
                context.buildConstraintViolationWithTemplate( String.format("%s必须是一个数字,并且最多保留%s位小数！",fieldDecimalScale.fieldName(),fieldDecimalScale.maxScale())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }

}
