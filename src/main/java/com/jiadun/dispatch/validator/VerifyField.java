package com.jiadun.dispatch.validator;


import com.fit.utils.se.StringUtil;
import lombok.Getter;

/**
 * @describe: 验证的字段对象
 * @author: hcl
 * @date: 2019/4/11 18:01
 */
@Getter
public class VerifyField {

    /**
     * @describe: 字段英文名
     * @author: hcl
     * @date: 2019/4/11 17:53
     */
    private String field;

    /**
     * @describe: 字段中文名
     * @author: hcl
     * @date: 2019/4/11 17:53
     */
    private String fieldName;

    private VerifyField(String field, String fieldName){
        this.field = field;
        this.fieldName = StringUtil.getObjectStr(fieldName);
    }

    /**
     * @describe: 根据字段英文和中文名获取验证的字段
     * @author: hcl  
     * @date: 2019/4/11 17:55
     * @param: [field, fieldName]  
     * @return com.jiadun.povertyrelief.common.publics.validator.VerifyField  
     */
    public static VerifyField getVerifyField(String field,String fieldName){
        return new VerifyField(field,fieldName);
    }


}
