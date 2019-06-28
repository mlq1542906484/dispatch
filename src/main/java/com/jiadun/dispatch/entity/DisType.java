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
 * 数据类型表
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_dis_type")
public class DisType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 1：预警,2:人员发现
     */
    private Integer type;

    /**
     * 状态=1: 启用,2:停用,3:删除
     */
    private Integer state;

    /**
     * kafka topic名称,不同的系统,推送同一个类型时,使用的topic一样
     */
    private String topic;

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
     * @describe: 字段说明
     */
    private String fieldExplain;

    /**
     * @describe: '格式说明'
     */
    private String formatExplain;
}
