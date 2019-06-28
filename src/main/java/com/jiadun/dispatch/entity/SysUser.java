package com.jiadun.dispatch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录名
     */
    private String account;

    /**
     * 登录密码
     */
    private String pwd;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别=1男,2,女
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 工号
     */
    private String jobNum;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 警种id
     */
    private Long policeTypeId;


    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 状态=1: 启用,2:停用,3:删除
     */
    private Integer state;

    /**
     * 插入用户的登录名
     */
    private String iUser;

    /**
     * 插入时间
     */
    private LocalDateTime iTime;

    /**
     * 最后更新用户的登录名
     */
    private String uUser;

    /**
     * 最后更新时间
     */
    private LocalDateTime uTime;

    /**
     * 地区编码
     */
    private String areaCode;


}
