package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段是否是一个身份证号
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsIdCardNumValidator extends FieldRegexValidator{

    public FieldIsIdCardNumValidator(VerifyField verifyField){
        super(verifyField,"");
    }

    public FieldIsIdCardNumValidator(VerifyField verifyField, String errorMessage){
        super(verifyField,"",errorMessage);
    }

    /**
     * @description: 验证字段是否是一个身份证号
     * @param: [context, s]
     * @return: boolean
     * @author: hcl
     * @date: created in 2018/1/13 17:19
     */
    @Override
    public boolean validate(ValidatorContext context, String s) {
        if(EmptyUtils.isEmpty(s)){
            return true;
        }

        if(s.length() != 15 && s.length() != 18){
            String message = String.format("%s长度有误！",this.getFieldName() );
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        if(s.length() == 15){
            if(!s.matches("^[1-9]\\d{7}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}$")){
                String message = null;
                if(this.getErrorMessage() != null){
                    message = this.getErrorMessage();
                }else{
                    message = String.format("%s格式不正确！",this.getFieldName());
                }
                context.addError(ValidationError.create(message).setField(this.getField()));
                return false;
            }
        }else{
            if(!s.matches("^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}([0-9]||X||x)$")){
                String message = null;
                if(this.getErrorMessage() != null){
                    message = this.getErrorMessage();
                }else{
                    message = String.format("%s格式不正确！",this.getFieldName());
                }
                context.addError(ValidationError.create(message).setField(this.getField()));
                return false;
            }
        }
        return true;
    }
}
