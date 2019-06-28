package com.jiadun.dispatch.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 远程调用系统状态
 * 系统状态码说明：
 * 0~999          系统状态
 * @author: hcl
 * @date: created in 2018/1/12 18:53
 */
@Getter
@AllArgsConstructor
public enum RestSystemStatus implements RestStatus {

    //成功
    SYS_SUCCESS("200", "操作成功！"),

    //系统异常(SYS_开头)
    SYS_RESOURCES_NOT_FOUND("404", "资源[%s]不存在！"),
    SYS_ERROR("500", "服务器异常！"),
    SYS_UNKNOWN_ERROR("501", "未知的异常！"),
    SYS_ACCESS_DENIED_ERROR("502", "权限不足，访问被拒绝！"),
    SYS_AUTHENTICATION_FAILED_ERROR("503","身份验证失败！"),
    SYS_REQUEST_PARAMETER_ERROR("504", "请求参数解析异常！"),
    SYS_REQUEST_TYPE_ERROR("505", "请求方式[%s]不支持！"),
    SYS_ATTRIBUTE_ILLEGAL_ERROR("506", "属性[%s]不合法！"),
    SYS_UNSUPPORTED_ENCODING_ERROR("507", "不支持的编码异常！"),
    SYS_REMOTE_SERVICE_INVOCATION_ERROR("508","远程调用服务异常！"),
    SYS_MICROSERVICE_SERVICE_ERROR("509","没有可用的服务节点, 请稍后再试！"),
    SYS_SYSTEM_TIMEOUT_ERROR("510","网关超时！"),

    SYS_LOGOUT_ERROR("998", "未登录，注销失败！"),
    SYS_CUSTOMER_ERROR("999", "%s");

    private String code;
    private String desc;
}