package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段不能为空或者空集合等（会去除前后空格）
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldNotEmptyValidator extends FieldValidator<Object>{

    public FieldNotEmptyValidator(VerifyField verifyField){
        super(verifyField);
    }

    public FieldNotEmptyValidator(VerifyField verifyField, String errorMessage){
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
        if(EmptyUtils.isEmpty(s)){
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
