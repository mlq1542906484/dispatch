package com.jiadun.dispatch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务下发实例表
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_dis_issued_instance")
public class DisIssuedInstance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作id
     */
    private Long operationId;

    /**
     * 是否下发
     */
    private Boolean isIssue;

    /**
     * 需要下发的部门id
     */
    private Long deptId;

    /**
     * 消息下发时间(如果未下发,需要写定时器检查下发)
     */
    private LocalDateTime issueDate;

    /**
     * 人员地址
     */
    private String persionArea;

    /**
     * 接收时限,分钟
     */
    private Integer acceptDeadline;

    /**
     * 是否允许继续下发,1:允许,0:不允许
     */
    private Boolean continueIssued;

    /**
     * 是否审核(1:允许,0:不允许)
     */
    private Boolean isAudit;

    /**
     * 审批用户id,可多选,逗号方式分割,先到先得(当is_audit为1时必填)
     */
    private String auditUserId;

    /**
     * 结果提交方式(1,手动提交,2:自动提交)
     */
    private Integer commitType;

    /**
     * 反馈提交延迟分钟数，0为立即 (当commit_type为2时，必填)
     */
    private Integer commitDelayedMinute;

    /**
     * 是否完成
     */
    private Boolean isFinish;

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
