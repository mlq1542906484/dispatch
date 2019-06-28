package com.jiadun.dispatch.exception;

import com.jiadun.dispatch.state.RestBusinessStatusInter;

/**
 * @description: 自定义远程调用业务异常
 * @author: hcl
 * @date: created in 2018/1/12 11:31
 */
public class RestBusinessException extends RestException {


    /**
     * @description: 根据远程调用业务状态和参数创建一个异常
     * @param: [restBusinessStatus, params]
     * @return:
     * @author:
     * @date: created in 2018/1/12 11:31
     */
    public RestBusinessException(RestBusinessStatusInter restBusinessStatus, String... params){
        super(restBusinessStatus,params);
    }


    /**
     * @description: 根据远程调用业务状态和参数创建一个异常
     * @param: [restBusinessStatus, params]
     * @return:
     * @author:
     * @date: created in 2018/1/12 11:31
     */
    public RestBusinessException(Exception e, RestBusinessStatusInter restBusinessStatus, String... params){
        super(e,restBusinessStatus,params);
    }


    /**
     * @description: 根据描述和远程调用业务状态创建一个异常
     * @param: [desc, restBusinessStatus]
     * @return:
     * @author:
     * @date: created in 2018/1/12 11:31
     */
    public RestBusinessException(String desc, RestBusinessStatusInter restBusinessStatus){
        super(desc,restBusinessStatus);
    }

    /**
     * @description: 根据描述和远程调用业务状态创建一个异常
     * @param: [desc, restBusinessStatus]
     * @return:
     * @author:
     * @date: created in 2018/1/12 11:31
     */
    public RestBusinessException(Exception e, String desc, RestBusinessStatusInter restBusinessStatus){
        super(e,desc,restBusinessStatus);
    }

}


