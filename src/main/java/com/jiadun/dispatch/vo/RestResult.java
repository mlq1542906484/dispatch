package com.jiadun.dispatch.vo;


import lombok.*;

/**
 * @description: 远程返回调用结果model
 * @author: hcl
 * @date: created in 2018/1/11 20:52
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class RestResult<T> {

    /**
     * 返回头信息
     */
    @NonNull
    private RestHeader status;


    /**
     * 返回结果
     */
    private T data;


    /**
     * @describe: 错误详细信息
     */
    private Object errorInfo;


}