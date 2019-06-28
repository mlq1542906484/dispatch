package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldRegex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字符串验证  正则表达式验证
 * @author: caiping
 * @date: created in 2018/1/26 18:20
 */
public class FieldRegexValidtor implements ConstraintValidator<FieldRegex,String> {

    private FieldRegex fieldRegex;

    @Override
    public void initialize(FieldRegex fieldRegex) {
        this.fieldRegex = fieldRegex;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(!value.matches(fieldRegex.regex())){
            if(EmptyUtils.isEmpty(fieldRegex.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s格式不正确！",fieldRegex.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
