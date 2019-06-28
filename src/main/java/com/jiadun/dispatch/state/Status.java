package com.jiadun.dispatch.state;

/**
 * @description: 状态顶级接口
 * @author: hcl
 * @date: created in 2018/1/12 18:53
 */
public interface Status {

    /**
     * @description: 获取错误代码
     * @param: []
     * @return: void
     * @author: hcl
     * @date: created in 2018/1/12 19:00
     */
    public String getCode();

    /**
     * @description: 获取错误描述
     * @param: []
     * @return: void
     * @author: hcl
     * @date: created in 2018/1/12 19:00
     */
    public String getDesc();

}
