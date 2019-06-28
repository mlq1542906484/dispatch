package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.StringUtil;
import com.jiadun.dispatch.validator.annotation.FieldNumberLess;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字段值验证,字段值必须小于或等于指定的值
 * @author: hcl
 * @date: created in 2018/1/26 16:38
 */
public class FieldNumberLessValidator implements ConstraintValidator<FieldNumberLess,Number> {

    private FieldNumberLess fieldNumberLess;

    @Override
    public void initialize(FieldNumberLess fieldNumberLess) {
        this.fieldNumberLess = fieldNumberLess;
    }

    @Override
    public boolean isValid(Number s, ConstraintValidatorContext context) {
        if(s == null){return true;}
        Double val = s.doubleValue();
        if(val > fieldNumberLess.maxVal()){
            if(EmptyUtils.isEmpty(fieldNumberLess.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s必须小于等于%s！",fieldNumberLess.fieldName(),StringUtil.toString(fieldNumberLess.maxVal()))).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
