package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段长度验证,字段长度必须大于或等于指定的长度。
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldLengthGreaterValidator extends FieldValidator<String>{
    /**
     * @description: 最小的长度
     */
    private int minLength;


    public FieldLengthGreaterValidator(VerifyField verifyField, int minLength){
        super(verifyField);
        this.minLength = minLength;
    }

    public FieldLengthGreaterValidator(VerifyField verifyField, int minLength, String errorMessage){
        super(verifyField,errorMessage);
        this.minLength = minLength;
    }

    /**
     * @description: 验证字段长度, len >= minLength && len <= maxLength ;
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
        if(s.length() < this.minLength ){
            String message = null;
            if(this.getErrorMessage() != null){
                message = this.getErrorMessage();
            }else{
                message = String.format("%s长度必须大于等于%d位！",this.getFieldName(),this.minLength);
            }
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        return true;
    }
}
