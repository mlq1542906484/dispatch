package com.jiadun.dispatch.validator;

import lombok.Getter;

/**
 * @description: 字段是否是一个正确的邮箱
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsEmailValidator extends FieldRegexValidator{

    public FieldIsEmailValidator(VerifyField verifyField){
        super(verifyField,"^[a-zA-Z0-9][a-zA-Z0-9_\\-\\.]{0,19}@(?:[a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$", String.format("%s格式不正确！", verifyField.getFieldName()));
    }

    public FieldIsEmailValidator(VerifyField verifyField, String errorMessage){
        super(verifyField,"^[a-zA-Z0-9][a-zA-Z0-9_\\-\\.]{0,19}@(?:[a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$",errorMessage != null ? errorMessage : String.format("%s格式不正确！",verifyField.getFieldName()));
    }
}
