package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldIsIdCardNum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 身份证验证器
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsIdCardNumValidator implements ConstraintValidator<FieldIsIdCardNum,String> {
    private FieldIsIdCardNum fieldIsIdCardNum;
    @Override
    public void initialize(FieldIsIdCardNum fieldIsIdCardNum) {
        this.fieldIsIdCardNum = fieldIsIdCardNum;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }


        if(value.length() != 15 && value.length() != 18){
            if(EmptyUtils.isEmpty(fieldIsIdCardNum.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s长度不正确！",fieldIsIdCardNum.fieldName())).addConstraintViolation();
            }
            return false;
        }
        if(value.length() == 15){
            if(!value.matches("^[1-9]\\d{7}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}$")){
                if(EmptyUtils.isEmpty(fieldIsIdCardNum.message())) {
                    context.buildConstraintViolationWithTemplate(String.format("%s格式不正确！",fieldIsIdCardNum.fieldName())).addConstraintViolation();
                }
                return false;
            }
        }else{
            if(!value.matches("^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}([0-9]||X||x)$")){
                if(EmptyUtils.isEmpty(fieldIsIdCardNum.message())){
                    context.buildConstraintViolationWithTemplate(String.format("%s格式不正确！",fieldIsIdCardNum.fieldName())).addConstraintViolation();
                }
                return false;
            }
        }
        return true;
    }
}
