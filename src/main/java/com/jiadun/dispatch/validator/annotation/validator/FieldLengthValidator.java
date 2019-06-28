package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 验证字符串长度
 * @author: hcl
 * @date: created in 2018/1/26 14:38
 */
public class FieldLengthValidator implements ConstraintValidator<FieldLength,Object>{

    private FieldLength fieldLength;

    @Override
    public void initialize(FieldLength fieldLength) {
        this.fieldLength = fieldLength;
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return true;
        }
        String str = s.toString();

        if(str.length() != this.fieldLength.len()){
            if(EmptyUtils.isEmpty(fieldLength.message())){
                constraintValidatorContext.buildConstraintViolationWithTemplate(String.format("%s长度必须等于%s位！",fieldLength.fieldName(),fieldLength.len())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
