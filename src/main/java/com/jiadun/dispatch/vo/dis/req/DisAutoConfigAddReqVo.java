package com.jiadun.dispatch.vo.dis.req;

import com.jiadun.dispatch.validator.annotation.FieldNotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DisAutoConfigAddReqVo {

    /**
     * 数据类型 1：预警,2:人员发现
     */
    @ApiModelProperty("数据类型 1：预警,2:人员发现")
    @FieldNotEmpty(fieldName = "数据类型")
    private Integer dataType;

    /**
     * 1:系统级别下发配置,2,:部门级别下发配置
     */
    @ApiModelProperty("配置类型(1:系统级别下发配置,2,:部门级别下发配置)")
    @FieldNotEmpty(fieldName = "配置类型")
    private Integer type;


    /**
     * 系统编号
     */
    @ApiModelProperty("系统编号")
    @FieldNotEmpty(fieldName = "系统编号")
    private String systemCode;

    /**
     * 重要级别,1、2、3、4
     */
    @ApiModelProperty("重要级别,1、2、3、4")
    @FieldNotEmpty(fieldName = "重要级别")
    private Integer matterLevel;

    /**
     * 消息下发延后分钟数,(0表示立即下发)
     */
    @ApiModelProperty("消息下发延后分钟数,(0表示立即下发)")
    @FieldNotEmpty(fieldName = "消息下发延后分钟数")
    private Integer issueDelayedMinute;

    /**
     * 1:人员所在地,2:籍贯所在地
     */
    @ApiModelProperty("接收结构地区：1:人员所在地,2:籍贯所在地")
    @FieldNotEmpty(fieldName = "接收结构地区")
    private Integer persionArea;

    /**
     * 接收部门的级别,1:省厅,2:市级,3:区县
     */
    @ApiModelProperty("接收部门的级别,1:省厅,2:市级,3:区县")
    @FieldNotEmpty(fieldName = "接收部门的级别")
    private Integer deptLevel;

    /**
     * 接收部门类型(1:治安,2: 刑侦,3: 技侦,4: 经侦,5: 网安.6: 禁毒.7:  国保)
     */
    @ApiModelProperty("接收部门类型(1:治安,2: 刑侦,3: 技侦,4: 经侦,5: 网安.6: 禁毒.7:  国保)")
    @FieldNotEmpty(fieldName = "接收部门类型")
    private Integer deptType;

    /**
     * 接收时限,分钟
     */
    @ApiModelProperty("接收时限,分钟")
    @FieldNotEmpty(fieldName = "接收时限")
    private Integer acceptDeadline;

    /**
     * 是否允许继续下发,1:允许,0:不允许
     */
    @ApiModelProperty("是否允许继续下发,1:允许,0:不允许")
    @FieldNotEmpty(fieldName = "是否允许继续下发")
    private Boolean continueIssued;

    /**
     * 是否审核(1:允许,0:不允许)
     */
    @ApiModelProperty("是否审核(1:允许,0:不允许)")
    private Boolean isAudit;

    /**
     * 审批用户id,可多选,逗号方式分割,先到先得(当is_audit为1时必填)
     */
    @ApiModelProperty("审批用户id,可多选,逗号方式分割,先到先得(当is_audit为1时必填)")
    private String auditUserId;

    /**
     * 结果提交方式(1,手动提交,2:自动提交)
     */
    @ApiModelProperty("结果提交方式(1,手动提交,2:自动提交)")
    private Integer commitType;

    /**
     * 反馈提交延迟分钟数，0为立即 (当commit_type为2时，必填)
     */
    @ApiModelProperty("反馈提交延迟分钟数，0为立即 (当commit_type为2时，必填)")
    private Integer commitDelayedMinute;

}
