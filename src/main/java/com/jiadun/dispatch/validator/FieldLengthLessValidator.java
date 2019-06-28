package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段长度验证,字段长度必须小于或等于指定的长度
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldLengthLessValidator extends FieldValidator<String>{

    /**
     * @description: 最大的长度
     */
    private int maxLength;

    public FieldLengthLessValidator(VerifyField verifyField, int maxLength){
        super(verifyField);
        this.maxLength = maxLength;
    }
    public FieldLengthLessValidator(VerifyField verifyField, int maxLength, String errorMessage){
        super(verifyField,errorMessage);
        this.maxLength = maxLength;
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
        if(s.length() > this.maxLength){
            String message = null;
            if(this.getErrorMessage() != null){
                message = this.getErrorMessage();
            }else{
                message = String.format("%s长度必须小于等于%d位！",this.getFieldName(),this.maxLength);
            }
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        return true;
    }
}
