package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldLengthLess;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字段长度验证,字段长度必须小于或等于指定的长度
 * @author: hcl
 * @date: created in 2018/1/26 16:38
 */
public class FieldLengthLessValidator implements ConstraintValidator<FieldLengthLess,Object> {

    private FieldLengthLess fieldLengthLess;

    @Override
    public void initialize(FieldLengthLess fieldLengthLess) {
        this.fieldLengthLess = fieldLengthLess;
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext context) {
        if(s == null){return true;}
        String str = s.toString();
        if(str.length() > fieldLengthLess.maxLen()){
            if(EmptyUtils.isEmpty(fieldLengthLess.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s长度必须小于等于%s位！",fieldLengthLess.fieldName(),fieldLengthLess.maxLen())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
