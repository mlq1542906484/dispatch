package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldLengthGreater;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字段长度验证,字段长度必须大于或等于指定的长度
 * @author: hcl
 * @date: created in 2018/1/26 16:55
 */
public class FieldLengthGreaterValidator implements ConstraintValidator<FieldLengthGreater,Object> {

    private FieldLengthGreater fieldLengthGreater;

    @Override
    public void initialize(FieldLengthGreater fieldLengthGreater) {
        this.fieldLengthGreater = fieldLengthGreater;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        String str = value.toString();
        if(str.length() < fieldLengthGreater.minLen()){
            if(EmptyUtils.isEmpty(fieldLengthGreater.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s长度必须大于等于%s位！",fieldLengthGreater.fieldName(),fieldLengthGreater.minLen())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
