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
public class DisAutoConfigQueryReqVo {

    /**
     * 数据类型 1：预警,2:人员发现
     */
    @ApiModelProperty("数据类型 1：预警,2:人员发现")
    @FieldNotEmpty(fieldName = "数据类型")
    private Integer dataType;

    @ApiModelProperty("配置类型(1:系统级别下发配置,2,:部门级别下发配置)")
    @FieldNotEmpty(fieldName = "配置类型")
    private Integer type;


    @ApiModelProperty("系统编号")
    @FieldNotEmpty(fieldName = "系统编号")
    private String systemCode;


    @ApiModelProperty("重要级别,1、2、3、4")
    @FieldNotEmpty(fieldName = "重要级别")
    private Integer matterLevel;
}
