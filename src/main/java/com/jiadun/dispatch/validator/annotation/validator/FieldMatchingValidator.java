package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.StringUtil;
import com.fit.utils.se.Tools;
import com.jiadun.dispatch.validator.annotation.FieldMatching;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 枚举验证器,只有当内容是枚举中的值时,才验证成功
 * @author: hcl
 * @date: created in 2018/1/26 19:55
 */
public class FieldMatchingValidator implements ConstraintValidator<FieldMatching,Object> {

    private FieldMatching fieldMatching;

    @Override
    public void initialize(FieldMatching fieldMatching) {
        this.fieldMatching = fieldMatching;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(fieldMatching.isCaseSensitive()){
            if(!Tools.equals(value.toString(),fieldMatching.array())){
                if(EmptyUtils.isEmpty(fieldMatching.message())){
                    context.buildConstraintViolationWithTemplate(String.format("%s必须不区分大小写匹配(%s)！",fieldMatching.fieldName(),StringUtil.join(fieldMatching.array()))).addConstraintViolation();
                }
                return false;
            }
        }else{
            if(!Tools.equalsNotDistinguish (value.toString(),fieldMatching.array())){
                if(EmptyUtils.isEmpty(fieldMatching.message())){
                    context.buildConstraintViolationWithTemplate(String.format("%s必须匹配(%s)！",fieldMatching.fieldName(),StringUtil.join(fieldMatching.array()))).addConstraintViolation();
                }
                return false;
            }
        }
        return true;
    }
}
