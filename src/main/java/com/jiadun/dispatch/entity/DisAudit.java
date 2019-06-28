package com.jiadun.dispatch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 审批表
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_dis_audit")
public class DisAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 审批用户id
     */
    private Long userId;

    /**
     * 1:人员发现,2:预警表
     */
    private Integer type;

    /**
     * 数据id(人员发现、预警 id)
     */
    private Long dataId;

    /**
     * 审批状态,1:待审批,2:审批中,已审批
     */
    private Integer auditState;


    /**
     * @describe: 研判结果内容
     * @author: hcl  
     * @date: 2019/6/27 10:54
     */
    private String resultContent;

    /**
     * 审批时间
     */
    private LocalDateTime auditDate;


    /**
     * 插入时间
     */
    private LocalDateTime iTime;
}
