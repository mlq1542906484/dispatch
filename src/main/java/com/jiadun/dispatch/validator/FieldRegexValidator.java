package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段匹配正则表达式
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldRegexValidator extends FieldValidator<String>{
    /**
     * @description: 正则表达式
     */
    private String regex;


    public FieldRegexValidator(VerifyField verifyField, String regex){
        super(verifyField);
        this.regex = regex;
    }

    public FieldRegexValidator(VerifyField verifyField, String regex, String errorMessage){
        super(verifyField,errorMessage);
        this.regex = regex;
    }

    /**
     * @description: 验证字段是否匹配正则表达式
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
        if(!s.matches(regex)){
            String message = null;
            if(this.getErrorMessage() != null){
                message = this.getErrorMessage();
            }else{
                message = String.format("%s格式不正确！",this.getFieldName());
            }
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        return true;
    }
}
