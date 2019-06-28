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
 * 数据审批结果表
 * </p>
 *
 * @author gen
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dis_audit_result")
public class DisAuditResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 结果内容
     */
    private String resultContent;

    /**
     * 备注
     */
    private String remark;

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
     * 审批id
     */
    private Long auditId;

    /**
     * 1:人员发现,2:预警表
     */
    private Integer type;

    /**
     * 数据id(人员发现、预警 id)
     */
    private Long dataId;

    /**
     * 结果提交用户id
     */
    private Long commitUserId;


}
