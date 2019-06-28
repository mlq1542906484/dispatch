package com.jiadun.dispatch.validator;

import lombok.Getter;

/**
 * @description: 字段是否是一个带小数的数字, 注意：1.0算是一个小数 (包含了负数)
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsDecimalValidator extends FieldRegexValidator{

    public FieldIsDecimalValidator(VerifyField verifyField){
        super(verifyField,"^-?\\d+\\.\\d+$",String.format("%s不是一个小数！", verifyField.getFieldName()));
    }

    public FieldIsDecimalValidator(VerifyField verifyField, String errorMessage){
        super(verifyField,"^-?\\d+\\.\\d+$",errorMessage != null ? errorMessage : String.format("%s不是一个小数！",verifyField.getFieldName()));
    }
}
