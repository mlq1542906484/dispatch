package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.StringUtil;
import com.jiadun.dispatch.validator.annotation.FieldNumberGreater;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字段长度验证,字段长度必须大于或等于指定的长度
 * @author: hcl
 * @date: created in 2018/1/26 16:55
 */
public class FieldNumberGreaterValidator implements ConstraintValidator<FieldNumberGreater,Number> {

    private FieldNumberGreater fieldNumberGreater;

    @Override
    public void initialize(FieldNumberGreater fieldNumberGreater) {
        this.fieldNumberGreater = fieldNumberGreater;
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        Double val = value.doubleValue();
        if(val < fieldNumberGreater.minVal()){
            if(EmptyUtils.isEmpty(fieldNumberGreater.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s必须大于等于%s！", fieldNumberGreater.fieldName(),StringUtil.toString(fieldNumberGreater.minVal()))).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
