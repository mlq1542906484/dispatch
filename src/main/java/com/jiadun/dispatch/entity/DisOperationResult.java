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
 * 数据反馈结果表
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_dis_operation_result")
public class DisOperationResult implements Serializable {

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
     * 需提交时间(为空表示实时提交)
     */
    private LocalDateTime resultCommit;

    /**
     * 是否已经提交
     */
    private Boolean isCommit;

    /**
     * @describe: 操作id
     * @author: hcl  
     * @date: 2019/6/27 10:52
     */
    private Long operationId;


    /**
     * @describe: 提交用户id
     * @author: hcl  
     * @date: 2019/6/27 11:05
     */
    private Long commitUserId;

}
