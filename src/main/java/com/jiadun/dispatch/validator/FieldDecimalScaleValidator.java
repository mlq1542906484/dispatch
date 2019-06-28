package com.jiadun.dispatch.validator;

import lombok.Getter;

/**
 * @description: 字段小数位数只能小于maxScale的
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldDecimalScaleValidator extends FieldRegexValidator{

    public FieldDecimalScaleValidator(VerifyField verifyField,int maxScale){
        super(verifyField,String.format("^-?\\d+(\\.\\d{1,%d})?$",maxScale),String.format("%s只能是数字类型，最多保留%s位小数！",verifyField.getFieldName() ,maxScale));
    }


    public FieldDecimalScaleValidator(VerifyField verifyField,int maxScale,String errorMsg){
        super(verifyField,String.format("^-?\\d+(\\.\\d{1,%d})?$",maxScale),errorMsg);
    }


}
