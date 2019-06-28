package com.jiadun.dispatch.validator.annotation.validator;

import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.ReflectUtil;
import com.jiadun.dispatch.validator.annotation.FieldCollection;
import com.jiadun.dispatch.validator.annotation.FieldRegex;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * @description: 字段集合验证器
 * @author: hcl
 * @date: created in 2018/1/26 18:27
 */
public class FieldCollectionValidator implements ConstraintValidator<FieldCollection,Object> {

    private FieldCollection fieldCollection;

    @Override
    public void initialize(FieldCollection fieldCollection) {
        this.fieldCollection = fieldCollection;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(value.getClass().isArray()){
            Object[] arr = (Object[])value;
            for(Object obj : arr){
                if(!this.validNull(obj,context)){
                    return false;
                }
                if(!this.valid(obj.toString(),context)){
                    return false;
                }
            }
        }else if(value instanceof Collection){
            Collection coll = (Collection)value;
            for(Object obj : coll){
                if(!this.validNull(obj,context)){
                    return false;
                }
                if(!this.valid(obj.toString(),context)){
                    return false;
                }
            }
        }else{
            throw new RuntimeException("被验证的内容不是一个集合！" + value.toString());
        }
        return true;
    }

    /**
     * @description: 验证null
     * @param: [obj, context]
     * @return: boolean
     * @author: hcl
     * @date: created in 2018/1/26 19:18
     */
    private boolean validNull(Object obj,ConstraintValidatorContext context){
        if(obj == null){
            if(EmptyUtils.isEmpty(fieldCollection.message())){
                context.buildConstraintViolationWithTemplate(String.format("%s不能为空！",fieldCollection.fieldName())).addConstraintViolation();
            }
            return false;
        }
        return true;
    }

    /**
     * @description: 验证
     * @param: [value]
     * @return: boolean
     * @author: hcl
     * @date: created in 2018/1/26 19:08
     */
    private boolean valid(String value,ConstraintValidatorContext context){
        FieldRegex[] fieldRegexs = fieldCollection.fieldRegex();
        for(FieldRegex fieldRegex : fieldRegexs){
            Constraint constraint = ReflectUtil.getAnnotation(FieldRegex.class, Constraint.class);
            if(constraint != null){
                Class<FieldRegexValidtor>[] constraintValidators = (Class<FieldRegexValidtor>[])constraint.validatedBy();
                for(Class<FieldRegexValidtor> constraintValidator : constraintValidators){
                    try {
                        FieldRegexValidtor c = constraintValidator.newInstance();
                        c.initialize(fieldRegex);
                        if(!c.isValid(value,context)){
                            if(EmptyUtils.isNotEmpty(fieldRegex.message())){
                                context.buildConstraintViolationWithTemplate(fieldRegex.message()).addConstraintViolation();
                            }else{
                                context.buildConstraintViolationWithTemplate(String.format("%s不符合规范！",fieldCollection.fieldName())).addConstraintViolation();
                            }
                            return false;
                        }
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                throw new RuntimeException("未获取到验证器！"+fieldRegex.toString());
            }
        }
        return true;
    }

}
