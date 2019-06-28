package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldNotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 验证对象是否不为空.和EmptyUtils.isNotEmpty()效果一样
 * @author: hcl
 * @date: created in 2018/1/26 14:21
 */
public class FieldNotEmptyValidator implements ConstraintValidator<FieldNotEmpty,Object>{
    private FieldNotEmpty fieldNotEmpty;
    @Override
    public void initialize(FieldNotEmpty fieldNotEmpty) {
        this.fieldNotEmpty = fieldNotEmpty;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if(EmptyUtils.isEmpty(o)){
            if(EmptyUtils.isEmpty(fieldNotEmpty.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s不能为空！",fieldNotEmpty.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
