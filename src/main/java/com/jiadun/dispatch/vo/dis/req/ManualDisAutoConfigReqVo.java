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
public class ManualDisAutoConfigReqVo {

    @ApiModelProperty("部门id")
    @FieldNotEmpty(fieldName = "接收部门")
    private Long deptId;


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
