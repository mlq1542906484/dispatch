package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.StringUtil;
import com.jiadun.dispatch.validator.annotation.FieldNumberInterval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 字段值区间验证, val >= minVal && val <= maxVal ;
 * @author: hcl
 * @date: created in 2018/1/26 17:03
 */
public class FieldNumberIntervalValidator implements ConstraintValidator<FieldNumberInterval,Number> {

    private FieldNumberInterval fieldNumberInterval;

    @Override
    public void initialize(FieldNumberInterval fieldNumberInterval) {
        this.fieldNumberInterval = fieldNumberInterval;
    }

    @Override
    public boolean isValid(Number s, ConstraintValidatorContext context) {
        if(s == null){
            return true;
        }
        Double val = s.doubleValue();
        if(this.fieldNumberInterval.minVal() > val || val > this.fieldNumberInterval.maxVal()){
            if(EmptyUtils.isEmpty(fieldNumberInterval.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s必须在%s~%s大小之间！",fieldNumberInterval.fieldName(),StringUtil.toString(fieldNumberInterval.minVal()),StringUtil.toString(fieldNumberInterval.maxVal()))).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
