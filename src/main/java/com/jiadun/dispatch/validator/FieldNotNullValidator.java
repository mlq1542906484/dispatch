package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import lombok.Getter;

/**
 * @description: 字段不能为空（会去除前后空格）
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldNotNullValidator extends FieldValidator<Object>{

    public FieldNotNullValidator(VerifyField verifyField){
        super(verifyField);
    }

    public FieldNotNullValidator(VerifyField verifyField,String errorMessage){
        super(verifyField,errorMessage);
    }

    /**
     * @description: 验证字段不能为空（会去除前后空格）
     * @param: [context, s]
     * @return: boolean
     * @author: hcl
     * @date: created in 2018/1/13 14:55
     */
    @Override
    public boolean validate(ValidatorContext context, Object s) {
        if(s == null){
            String message = null;
            if(this.getErrorMessage() != null){
                message = this.getErrorMessage();
            }else{
                message = String.format("%s不能为空！",this.getFieldName());
            }
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        return true;
    }
}
