package com.jiadun.dispatch.state;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @description: 远程调用业务状态
 *  业务状态码说明：
        1000~1999       公共的业务状态
 *
 * @author: hcl
 * @date: created in 2018/1/12 18:53
 */
@Getter
@AllArgsConstructor
public enum RestCommBusinessStatus implements RestBusinessStatusInter {

    //成功
    BUSINESS_SUCCESS("200","操作成功！"),

    //授权失败
    BUSINESS_INVALID_GRANT_ERROR("1001", "用户名或密码错误！"),
    BUSINESS_GRANT_ERROR("1002", "授权失败！"),
    BUSINESS_SMS_CODE_VALID_ERROR("1003", "验证码错误！"),
    BUSINESS_IP_VALID_ERROR("1004", "IP验证失败！"),

    //字段属性不合法
    BUSINESS_ATTRIBUTE_ILLEGAL_ERROR("1100", "属性[%s]不合法！"),
    //对象不存在
    BUSINESS_OBJECT_NOT_FOUND_ERROR("1101", "[%s]不存在！"),
    //对象重复
    BUSINESS_OBJECT_ALREADY_ERROR("1102", "[%s]已存在！"),
    //SQL执行受影响行数为0
    BUSINESS_OBJECT_SAVE_ERROR("1103", "保存[%s]失败！"),
    BUSINESS_OBJECT_UPDATE_ERROR("1104", "修改[%s]失败！"),
    BUSINESS_OBJECT_DELETE_ERROR("1105", "删除[%s]失败！"),
    //导入失败
    BUSINESS_OBJECT_IMPORT_ERROR("1106", "导入[%s]失败！"),
    //创建失败
    BUSINESS_OBJECT_CREATE_ERROR("1107", "创建[%s]失败！"),
    //不支持的操作
    BUSINESS_DOES_NOT_SUPPORT_ERROR("1108", "[%s]不支持[%s]！"),
    //导出失败
    BUSINESS_OBJECT_EXPORT_ERROR("1109","[%s]导出失败！"),
    //对象转换失败
    BUSINESS_OBJECT_CAST_ERROR("1110","[%s]转换为[%s]失败！"),
    //读取失败
    BUSINESS_READ_OBJECT_ERROR("1111","[%s]读取失败！"),
    //对象初始化错误
    BUSINESS_OBJECT_INIT_ERROR("1112","[%s]初始化失败！"),

    //消息发送失败
    BUSINESS_SEND_MESSAGE_ERROR("1113","[%s]消息发送失败！"),

    //远程调用异常
    BUSINESS_REMOTE_PROCEDURE_CALL_ERROR("1114","远程调用异常！"),
    //接口调用失败
    BUSINESS_INTERFACE_VISIT_ERROR("1114","接口调用失败！编码：[%s],错误描述：[%s]。"),

    SEND_MESSAGE_ERROR("4011", "发送消息错误"),
    OBJECT_TO_JSON_ERROR("4008", "对象转JSON失败"),
    JSON_TO_OBJECT_ERROR("4009", "对象转JSON失败"),
    VALIDERROR("40014","");

    private String code;
    private String desc;

}
