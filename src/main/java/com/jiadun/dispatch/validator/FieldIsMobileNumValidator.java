package com.jiadun.dispatch.validator;

import lombok.Getter;

/**
 * @description: 字段是否是一个手机号
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsMobileNumValidator extends FieldRegexValidator{

    public FieldIsMobileNumValidator(VerifyField verifyField){
        super(verifyField,"^1[0-9]{10}$",String.format("%s格式不正确！",verifyField.getFieldName()));
    }

    public FieldIsMobileNumValidator(VerifyField verifyField, String errorMessage){
        super(verifyField,"^1[0-9]{10}$",errorMessage != null ? errorMessage : String.format("%s格式不正确！", verifyField.getFieldName()));
    }
}
