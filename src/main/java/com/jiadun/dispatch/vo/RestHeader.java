package com.jiadun.dispatch.vo;


import lombok.*;

/**
 * @description: 远程调用结果 头信息
 * @author: hcl
 * @date: created in 2018/1/11 20:52
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestHeader {

    /**
     * @description: 返回代码
     */
    @NonNull
    private String code;
    /**
     * @description: 返回信息
     */
    @NonNull
    private String  msg;

}
