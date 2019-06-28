package com.jiadun.dispatch.validator;

import lombok.Getter;

/**
 * @description: 字段是否是一个boolean类型(是否只包含了true和false)
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsBooleanValidator extends FieldRegexValidator{

    public FieldIsBooleanValidator(VerifyField verifyField){
        super(verifyField,"^true|false$",String.format("%s必须是true或false！", verifyField.getFieldName()));
    }

    public FieldIsBooleanValidator(VerifyField verifyField, String errorMessage){
        super(verifyField,"^true|false$",errorMessage != null ? errorMessage : String.format("%s必须是true或false！",verifyField.getFieldName()));
    }
}
