package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.*;
import com.fit.utils.se.EmptyUtils;
import lombok.Getter;

/**
 * @description: 字段是否是一个数组
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public class FieldIsArrayValidator extends FieldValidator<String[]>{
    /**
     * @description: 内容验证器
     */
    private FieldValidator<String> fieldValidator;


    public FieldIsArrayValidator(VerifyField verifyField, FieldValidator<String> fieldValidator){
        super(verifyField);
        this.fieldValidator = fieldValidator;
    }


    public FieldIsArrayValidator(VerifyField verifyField, FieldValidator<String> fieldValidator, String errorMessage){
        super(verifyField,errorMessage);
        this.fieldValidator = fieldValidator;
    }


    /**
     * @description: 验证字段长度是否合法
     * @param: [context, s]
     * @return: boolean
     * @author: hcl
     * @date: created in 2018/1/13 14:55
     */
    @Override
    public boolean validate(ValidatorContext context, String[] s) {
        if(EmptyUtils.isEmpty(s)){
            return true;
        }
        FluentValidator fluentValidator = FluentValidator.checkAll();
        for(String str : s){
            fluentValidator.on(str,this.fieldValidator);
        }
        ComplexResult result = fluentValidator.doValidate().result(ResultCollectors.toComplex());
        if(!result.isSuccess()){
            //验证失败获取异常对象
            context.addError(ValidationError.create(String.format("%s格式不正确！",this.fieldValidator.getFieldName())).setField(this.fieldValidator.getField()));
            return false;
        }
        return true;
    }

}
