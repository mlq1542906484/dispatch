package com.jiadun.dispatch.exception;

import com.jiadun.dispatch.state.RestSystemStatus;

/**
 * @description: 自定义远程调用系统异常
 * @author: hcl
 * @date: created in 2018/1/12 11:31
 */
public class RestSystemException extends RestException {


    /**
     * @description: 根据远程调用系统状态和参数创建一个异常
     * @param: [restSystemStatus, params[]]
     * @return:
     * @author:
     * @date: created in
     */
    public RestSystemException(RestSystemStatus restSystemStatus, String... params){
        super(restSystemStatus,params);
    }


    /**
     * @description: 根据远程调用系统状态和参数创建一个异常
     * @param: [restSystemStatus, params[]]
     * @return:
     * @author:
     * @date: created in
     */
    public RestSystemException(Exception e, RestSystemStatus restSystemStatus, String... params){
        super(e,restSystemStatus,params);
    }

    /**
     * @description: 根据描述和远程调用系统状态创建一个异常
     * @param: [restSystemStatus, params[]]
     * @return:
     * @author:
     * @date: created in
     */
    public RestSystemException(String desc, RestSystemStatus restSystemStatus){
        super(desc,restSystemStatus);
    }

    /**
     * @description: 根据描述和远程调用系统状态创建一个异常
     * @param: [restSystemStatus, params[]]
     * @return:
     * @author:
     * @date: created in
     */
    public RestSystemException(Exception e, String desc, RestSystemStatus restSystemStatus){
        super(e,desc,restSystemStatus);
    }

}


