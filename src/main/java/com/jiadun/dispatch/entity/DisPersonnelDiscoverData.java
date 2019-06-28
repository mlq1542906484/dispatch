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
 * 人员发现表
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_dis_personnel_discover_data")
public class DisPersonnelDiscoverData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 时间
     */
    private String time;

    /**
     * 系统编号
     */
    private String newssource;

    /**
     * 重要级别,1、2、3、4
     */
    private Integer importance;

    /**
     * 人员类型
     */
    private String persontype;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * url
     */
    private String detailUrl;

    /**
     * 当前所在地
     */
    private String currentSite;

    /**
     * 籍贯所在地
     */
    private String nativePlaceSite;

    /**
     * 插入时间
     */
    private LocalDateTime iTime;

    /**
     * 编码:数据编码生成规则: 类型（人员发现、预警... 取前两个字首字母） 时间(精确到天如:20190527) 顺序(4位自增数)
     */
    private String code;
}
