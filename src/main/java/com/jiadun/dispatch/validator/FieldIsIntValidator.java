package com.jiadun.dispatch.validator;

import lombok.Getter;

/**
 * @description: 字段是否是一个整数, 注意：1.0算是一个小数(包含了负数)
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsIntValidator extends FieldRegexValidator{

    public FieldIsIntValidator(VerifyField verifyField){
        super(verifyField,"^-?\\d+$", String.format("%s不是一个整数！", verifyField.getFieldName()));
    }

    public FieldIsIntValidator(VerifyField verifyField, String errorMessage){
        super(verifyField,"^-?\\d+$",errorMessage != null ? errorMessage : String.format("%s不是一个整数！",verifyField.getFieldName()));
    }
}
