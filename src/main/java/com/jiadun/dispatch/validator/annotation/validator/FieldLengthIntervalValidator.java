package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldLengthInterval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字段长度区间验证, len >= minLength && len <= maxLength ;
 * @author: hcl
 * @date: created in 2018/1/26 17:03
 */
public class FieldLengthIntervalValidator implements ConstraintValidator<FieldLengthInterval,Object> {

    private FieldLengthInterval fieldLengthInterval;

    @Override
    public void initialize(FieldLengthInterval fieldLengthInterval) {
        this.fieldLengthInterval = fieldLengthInterval;
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext context) {
        if(s == null){
            return true;
        }
        String str = s.toString();
        if(this.fieldLengthInterval.minLen() > str.length() || str.length() > this.fieldLengthInterval.maxLen()){
            if(EmptyUtils.isEmpty(fieldLengthInterval.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s长度必须在%d~%d位之间！",fieldLengthInterval.fieldName(),fieldLengthInterval.minLen(),fieldLengthInterval.maxLen())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
