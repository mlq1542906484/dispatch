package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.validator.annotation.FieldIsUrl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: URL验证器,验证字符串必须是一个正规的url
 * @author: caiping
 * @date: created in 2018/1/26 18:05
 */

public class FieldIsUrlValidator implements ConstraintValidator<FieldIsUrl,String> {
    private FieldIsUrl fieldIsUrl;
    @Override
    public void initialize(FieldIsUrl fieldIsUrl) {
        this.fieldIsUrl = fieldIsUrl;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(!value.matches("^((ht|f)tps?):\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+([\\w\\-\\.,@?^=%&:\\/~\\+#]*[\\w\\-\\@?^=%&\\/~\\+#])?$")){
            if(EmptyUtils.isEmpty(fieldIsUrl.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s格式不正确！",fieldIsUrl.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}
