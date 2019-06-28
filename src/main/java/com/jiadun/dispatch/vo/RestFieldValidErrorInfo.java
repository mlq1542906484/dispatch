package com.jiadun.dispatch.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @describe: 远程调用单个字段调用异常信息
 * @author: hcl
 * @date: 2018/5/10 17:30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestFieldValidErrorInfo implements RestErrorInfo{

    /**
     * @describe: 字段名称
     */
    private String fieldName;


    /**
     * @describe: 错误描述
     */
    private String errorMsg;
}
