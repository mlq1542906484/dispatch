package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import lombok.Getter;
import lombok.NonNull;

/**
 * @description: 字段验证
 * @author: hcl
 * @date: created in 2018/1/13 14:39
 */
@Getter
public abstract class FieldValidator<T> extends ValidatorHandler<T>{
    /**
     * @describe: 字段英文名
     * @author: hcl
     * @date: 2019/4/11 16:26
     */
    @NonNull
    private String field;

    /**
     * @description: 字段中文名
     */
    @NonNull
    private String fieldName;

    /**
     * @description: 异常提示
     */
    private String errorMessage;

    /**
     * @describe: 根据 VerifyField 创建 FieldValidator
     * @author: hcl
     * @date: 2019/4/11 19:01
     * @param: [verifyField]
     */
    public FieldValidator(VerifyField verifyField){
        this.field = verifyField.getField();
        this.fieldName = verifyField.getFieldName();
    }

    /**
     * @describe: 根据 VerifyField 和 errorMessage 创建 FieldValidator
     * @author: hcl
     * @date: 2019/4/11 19:02
     * @param: [verifyField, errorMessage]
     */
    public FieldValidator(VerifyField verifyField,String errorMessage){
        this.field = verifyField.getField();
        this.fieldName = verifyField.getFieldName();
        this.errorMessage = errorMessage;
    }

}
