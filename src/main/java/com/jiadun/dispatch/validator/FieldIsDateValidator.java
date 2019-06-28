package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.TimeUtil;
import lombok.Getter;

/**
 * @description: 字段是否是一个指定的日期类型
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsDateValidator extends FieldValidator<String>{


    /** 
     * @description: 日期格式
     */
    private String format;


    public FieldIsDateValidator(VerifyField verifyField,String format){
        super(verifyField);
        this.format = format;
    }

    public FieldIsDateValidator(VerifyField verifyField,String format,String errorMessage){
        super(verifyField,errorMessage);
        this.format = format;
    }


    @Override
    public boolean validate(ValidatorContext context, String s) {
        if(EmptyUtils.isEmpty(s)){
            return true;
        }
        if(TimeUtil.strictValidateDate(s,this.format) == null){
            String message = null;
            if(this.getErrorMessage() != null){
                message = this.getErrorMessage();
            }else{
                message = String.format("%s格式必须为%s！",this.getFieldName(),this.format);
            }
            context.addError(ValidationError.create(message).setField(this.getField()));
            return false;
        }
        return true;
    }
}
