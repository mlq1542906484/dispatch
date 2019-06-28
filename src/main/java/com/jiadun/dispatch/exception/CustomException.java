package com.jiadun.dispatch.exception;


import com.jiadun.dispatch.state.Status;

/**
 * @description: 自定义异常顶级接口
 * @author: hcl
 * @date: created in 2018/1/12 10:36
 */
public interface CustomException {

    /**
     * @description: 获取状态对象
     * @param: []
     * @return: java.lang.String
     * @author: hcl
     * @date: created in 2018/1/12 11:33
     */
    public Status getStatus();

    /**
     * @description: 获取异常描述
     * @param: []
     * @return: java.lang.String
     * @author: hcl
     * @date: created in 2018/1/12 11:34
     */
    public String getDesc();


    /**
     * @describe: 返回数据
     * @author: hcl  
     * @date: 2018/7/1 23:51
     * @param: []  
     * @return java.lang.Object  
     */
    public Object getRespData();
    
    /**
     * @describe: 获取详细的异常信息
     * @author: hcl  
     * @date: 2018/7/1 23:46
     * @param: []  
     * @return java.lang.Object  
     */
    public Object getErrorInfo();
    
}
