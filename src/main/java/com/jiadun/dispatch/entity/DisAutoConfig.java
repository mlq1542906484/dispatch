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
 * 任务自动下发配置表
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_dis_auto_config")
public class DisAutoConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 1:系统级别下发配置,2,:部门级别下发配置
     */
    private Integer type;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 系统编号
     */
    private String systemCode;

    /**
     * 重要级别,1、2、3、4
     */
    private Integer matterLevel;

    /**
     * 消息下发延后分钟数,(0表示立即下发)
     */
    private Integer issueDelayedMinute;

    /**
     * 1:人员所在地,2:籍贯所在地
     */
    private Integer persionArea;

    /**
     * 接收部门的级别,1:省厅,2:市级,3:区县
     */
    private Integer deptLevel;

    /**
     * 接收部门类型(1:治安,2: 刑侦,3: 技侦,4: 经侦,5: 网安.6: 禁毒.7:  国保)
     */
    private Integer deptType;

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
     * 1：预警,2:人员发现
     */
    private Integer dataType;
}
