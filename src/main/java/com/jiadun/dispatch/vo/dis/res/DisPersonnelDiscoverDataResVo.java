package com.jiadun.dispatch.vo.dis.res;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DisPersonnelDiscoverDataResVo {


    /**
     * 主键
     */
    @ApiModelProperty("审核id")
    private Long auditId;

    @ApiModelProperty("数据id")
    private Long id;

    /**
     * 时间
     */
    @ApiModelProperty("时间")
    private String time;

    /**
     * 系统编号
     */
    @ApiModelProperty("系统编号")
    private String newssource;

    /**
     * 重要级别,1、2、3、4
     */
    @ApiModelProperty("重要级别,1、2、3、4")
    private Integer importance;

    /**
     * 人员类型
     */
    @ApiModelProperty("人员类型")
    private String persontype;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 身份证
     */
    @ApiModelProperty("身份证")
    private String idcard;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * QQ号码
     */
    @ApiModelProperty("QQ号码")
    private String qq;

    /**
     * 微信号
     */
    @ApiModelProperty("微信号")
    private String wechat;

    /**
     * url
     */
    @ApiModelProperty("url")
    private String detailUrl;

    /**
     * 当前所在地
     */
    @ApiModelProperty("当前所在地")
    private String currentSite;

    /**
     * 籍贯所在地
     */
    @ApiModelProperty("籍贯所在地")
    private String nativePlaceSite;


    /**
     * 编码:数据编码生成规则: 类型（人员发现、预警... 取前两个字首字母） 时间(精确到天如:20190527) 顺序(4位自增数)
     */
    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("审批状态,1:待审批,2:审批中,3:已审批")
    private Integer auditState;

    @ApiModelProperty("研判结果内容")
    private Integer resultContent;

    /**
     * 审批时间
     */
    @ApiModelProperty("审批时间")
    private LocalDateTime auditDate;

    /**
     * 插入时间
     */
    @ApiModelProperty("插入时间")
    private LocalDateTime iTime;


}
