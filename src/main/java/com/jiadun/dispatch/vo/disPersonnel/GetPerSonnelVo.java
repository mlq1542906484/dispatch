package com.jiadun.dispatch.vo.disPersonnel;

import com.jiadun.dispatch.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GetPerSonnelVo
 * @Description //TODO
 * @Author zjl
 * @Date 14:28   2019/6/25
 * @Version 1.0
 **/
@Getter
@Setter
public class GetPerSonnelVo {

    @ApiModelProperty("基础人员信息")
    private DisPersonnelDiscoverData personnelDiscoverData;

    @ApiModelProperty("基础预警信息")
    private DisWarningData warningData;

    @ApiModelProperty("扭转记录")
    private List<DisOperateRecord> operateRecords;

    @ApiModelProperty("数据反馈结果")
    private List<Map<String,Object>> operationResults;

    @ApiModelProperty("数据审批结果")
    private List<DisAuditResult> auditResults;


}
