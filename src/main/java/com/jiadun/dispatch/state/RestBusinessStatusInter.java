package com.jiadun.dispatch.state;


/**
 * @describe: 远程调用业务状态 顶级接口
 *
 *  业务状态码说明：
        1000~1999       公共的业务状态
        2000~2999       upms-sys 运维系统的业务状态
        3000~3999       resource 资源系统业务状态
        4000~4999       battle 作战系统业务状态
        5000~5999       asset 资产系统业务状态
        6000~6999       scheduler 任务调度业务状态
 *
 * @author: hcl  
 * @date: 2018/7/2 13:21
 */
public interface RestBusinessStatusInter extends RestStatus{
}
