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
 * 访问资源表
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_sys_resource")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    private String standardName;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 资源代码
     */
    private String resourceCode;

    /**
     * 排序号
     */
    private Integer orderNum;

    /**
     * 状态=1: 启用,2:停用,3:删除
     */
    private Integer state;

    /**
     * 菜单=1:是,0:否
     */
    private Boolean isMenu;

    /**
     * 组件
     */
    private String component;

    /**
     * 父资源id
     */
    private Long pId;

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
