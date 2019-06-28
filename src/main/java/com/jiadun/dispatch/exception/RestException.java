package com.jiadun.dispatch.exception;

import com.jiadun.dispatch.state.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 自定义远程调用异常
 * @author: hcl
 * @date: created in 2018/1/12 11:31
 */
@Getter
@Setter
public class RestException extends RuntimeException implements CustomException{

    /**
     * @description: 状态枚举对象
     */
    private Status status;

    /**
     * @description: 自定义异常描述
     */
    private String desc;

    /**
     * @description: 返回内容
     */
    private Object respData;

    /**
     * @describe: 异常详细信息
     */
    private Object errorInfo;


    /**
     * @description: 根据状态和参数创建一个异常
     * @param: [status, params[]]
     * @return:
     * @author:
     * @date: created in
     */
    public RestException(Status status, String... params){
        super(params != null && params.length != 0 ? String.format(status.getDesc(), (Object[]) params):status.getDesc());
        this.status = status;
        if(params != null && params.length != 0){
            desc = String.format(status.getDesc(), (Object[]) params);
        }else{
            desc = status.getDesc();
        }
    }

    /**
     * @description: 根据状态和参数创建一个异常
     * @param: [status, params[]]
     * @return:
     * @author:
     * @date: created in
     */
    public RestException(Exception e,Status status, String... params){
        super(e);
        this.status = status;
        if(params != null && params.length != 0){
            desc = String.format(status.getDesc(), (Object[]) params);
        }else{
            desc = status.getDesc();
        }

    }


    /**
     * @description: 根据描述和状态创建一个异常
     * @param: [desc, status]
     * @return:
     * @author:
     * @date: created in
     */
    public RestException(String desc, Status status) {
        super(desc);
        this.status = status;
        this.desc = desc;
    }

    /**
     * @description: 根据描述和状态创建一个异常
     * @param: [desc, status]
     * @return:
     * @author:
     * @date: created in
     */
    public RestException(Exception e,String desc, Status status) {
        super(e);
        this.status = status;
        this.desc = desc;
    }

}


