package com.jiadun.dispatch.validator;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.exception.RestException;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.vo.RestErrorInfos;
import com.jiadun.dispatch.vo.RestFieldValidErrorInfo;

import java.util.List;

/**
 * @description: 验证工具类
 * @author: hcl
 * @date: created in 2018/1/13 18:07
 */
public class ValidatorUtils {

    /**
     * @description: 判断是否通过验证, 如果未通过则抛出异常
     * @param: [result,isBusinessExcpetion]
     * @param: [exceptionClass] 异常类型
     * @return: void
     * @author: hcl
     * @date: created in 2018/1/16 19:34
     */
    public static void isSuccess(ComplexResult result) {
        if (!result.isSuccess()) {

            //验证失败获取异常对象
            List<ValidationError> errors = result.getErrors();
            RestErrorInfos restErrorInfos = null;
            String errorMsg = null;
            if(EmptyUtils.isNotEmpty(errors)){
                restErrorInfos = new RestErrorInfos();
                for (ValidationError error : errors) {
                    restErrorInfos.add(new RestFieldValidErrorInfo(error.getField(),error.getErrorMsg()));
                    if(errorMsg == null){
                        errorMsg = error.getErrorMsg();
                    }
                }
            }else{
                errorMsg = "参数验证失败！";
            }
            RestException restException = new RestBusinessException(errorMsg, RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR);
            restException.setErrorInfo(restErrorInfos);
            throw restException;
        }
    }

}