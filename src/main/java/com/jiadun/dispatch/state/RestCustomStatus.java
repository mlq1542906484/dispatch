package com.jiadun.dispatch.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @describe: 自定义远程状态
 * @author: hcl
 * @date: 2018/6/8 19:31
 */
@Getter
@Setter
@AllArgsConstructor
public class RestCustomStatus implements RestStatus{

    /**
     * @describe: 状态码
     * @author: hcl
     * @date: 2018/6/8 19:30
     */
    private String code;
    /**
     * @describe: 描述信息
     * @author: hcl
     * @date: 2018/6/8 19:30
     */
    private String desc;
}
