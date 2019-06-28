package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段长度区间验证, len >= minLength && len <= maxLength ;
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldLengthIntervalValidator extends FieldValidator<String>{

    /**
     * @description: 最大的长度
     */
    private int maxLength;
    /**
     * @description: 最小的长度
     */
    private int minLength;


    public FieldLengthIntervalValidator(VerifyField verifyField,int minLength, int maxLength){
        super(verifyField);
        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    public FieldLengthIntervalValidator(VerifyField verifyField,int minLength, int maxLength,String errorMessage){
        super(verifyField,errorMessage);
        this.maxLength = maxLength;
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
        if((this.maxLength < 0 || this.minLength < 0 || this.maxLength < this.minLength) ){
            throw new RuntimeException("字段长度区间验证异常，最小长度和最大长度必须大于等于0并且最小长度应该比最大长度大！");
        }
        if(this.minLength > s.length() || s.length() > this.maxLength){
            String message = null;
            if(this.getErrorMessage() != null){
                message = this.getErrorMessage();
            }else{
                message = String.format("%s长度必须在%d~%d位之间！",this.getFieldName(),this.minLength,this.maxLength);
            }
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        return true;
    }
}
