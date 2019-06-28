package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldIsColumn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字段格式验证器
 * @author: MLQ
 * @date: created in 2018/8/2 18:05
 */

public class FieldIsColumnValidator implements ConstraintValidator<FieldIsColumn,String> {
    private FieldIsColumn fieldIsColumn;
    @Override
    public void initialize(FieldIsColumn fieldIsColumn) {
        this.fieldIsColumn = fieldIsColumn;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(!value.matches("^[0-9a-zA-Z_]+$")){
            if(EmptyUtils.isEmpty(fieldIsColumn.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s不是字段格式！",fieldIsColumn.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
