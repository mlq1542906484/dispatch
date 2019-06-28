package com.jiadun.dispatch.validator;

import lombok.Getter;

/**
 * @description: 字段是否是一个数字(包含了小数和整数)
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsNumValidator extends FieldRegexValidator{

    public FieldIsNumValidator(VerifyField verifyField){
        super(verifyField,"^-?\\d+(\\.\\d+)?$", String.format("%s不是一个数字！", verifyField.getFieldName()));
    }

    public FieldIsNumValidator(VerifyField verifyField,String errorMessage){
        super(verifyField,"^-?\\d+(\\.\\d+)?$",errorMessage != null ? errorMessage : String.format("%s不是一个数字！", verifyField.getFieldName()));
    }
}
