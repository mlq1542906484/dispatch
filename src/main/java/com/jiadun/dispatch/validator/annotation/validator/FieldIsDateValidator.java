package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.TimeUtil;
import com.jiadun.dispatch.validator.annotation.FieldIsDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 时间验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsDateValidator implements ConstraintValidator<FieldIsDate,String> {
    private FieldIsDate fieldIsDate;
    @Override
    public void initialize(FieldIsDate fieldIsDate) {
        this.fieldIsDate = fieldIsDate;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(TimeUtil.strictValidateDate(value,fieldIsDate.format()) == null){
            if(EmptyUtils.isEmpty(fieldIsDate.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s格式必须为%s！",fieldIsDate.fieldName(),fieldIsDate.format())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
