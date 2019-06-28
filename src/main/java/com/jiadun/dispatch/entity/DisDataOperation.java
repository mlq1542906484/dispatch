package com.jiadun.dispatch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据操作表
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_dis_data_operation")
public class DisDataOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 1:人员发现,2:预警表
     */
    private Integer type;

    /**
     * 数据id(人员发现、预警 id)
     */
    private Long dataId;

    /**
     * 1:未接收,2:待下发,3:已下发,4:超时未接收,5:处理中,6:提交中,7:审核中,8:已完成
     */
    private Integer state;

    /**
     * 操作上级的id(系统级别的上级pid为null)
     */
    private Long pId;

    /**
     * 当前部门id,如果为空表示系统
     */
    private Long deptId;

    /**
     * 最后的接收时间(为null表示不限制)
     */
    private LocalDateTime acceptDeadline;

    /**
     * 是否允许继续下发,1:允许,0:不允许
     */
    private Boolean continueIssued;


    /**
     * 下发部门id,如果为空表示未下发
     */
    private Long targetDeptId;


    /**
     * 接收的用户id
     */
    private Long userId;
}
