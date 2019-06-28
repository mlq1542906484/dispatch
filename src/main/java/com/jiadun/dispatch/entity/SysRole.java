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
 * 角色表
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

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


}
