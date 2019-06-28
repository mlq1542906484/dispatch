package com.jiadun.dispatch.vo.disPersonnel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DisPersonnelVo
 * @Description //TODO
 * @Author zjl
 * @Date 10:06   2019/6/24
 * @Version 1.0
 **/
@ApiModel
@Getter
@Setter
public class DisPersonnelVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 时间
     */
    private String time;

    /**
     * 系统编号
     */
    private String newssource;

    /**
     * 重要级别,1、2、3、4
     */
    private Integer importance;

    /**
     * 人员类型
     */
    private String persontype;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 微信号
     */
    private String wechat;

    /**
     *  机构名称
     */
    private String deptName;

    /**
     *  机构ID
     */
    private String deptId;

    /**
     *  状态
     */
    private String state;

    /**
     * 编号
     */
    private String code;

    /**
     * 是否允许继续下发,1:允许,0:不允许
     */
    private boolean continueIssued;

    /**
     *  操作ID
     */
    private Long disDataOperationId;

}
