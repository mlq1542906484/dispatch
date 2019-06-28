package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段长度验证
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldLengthValidator extends FieldValidator<String>{
    /**
     * @description: 长度
     */
    private int length;


    public FieldLengthValidator(VerifyField verifyField,int length){
        super(verifyField);
        this.length = length;
    }


    public FieldLengthValidator(VerifyField verifyField,int length,String errorMessage){
        super(verifyField,errorMessage);
        this.length = length;
    }

    /**
     * @description: 验证字段长度是否合法
     * @param: [context, s]
     * @return: boolean
     * @author: hcl
     * @date: created in 2018/1/13 14:55
     */
    @Override
    public boolean validate(ValidatorContext context, String s) {
        if(EmptyUtils.isEmpty(s)){
            return true;
        }

        if( s.length() != this.length){
            String message = null;
            if(this.getErrorMessage() != null){
                message = this.getErrorMessage();
            }else{
                message = String.format("%s长度必须为%d位！",this.getFieldName(),this.getLength());
            }
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        return true;
    }
}
