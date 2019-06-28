package com.jiadun.dispatch.vo.sys.req;

import com.jiadun.dispatch.validator.annotation.FieldNotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: mlq
 * @Description:
 * @Date: Create in  2019/6/21 14:20
 */
@Data
@ApiModel
public class UpdatePolicetypeVo {

    @ApiModelProperty("警种ID")
    @FieldNotEmpty(fieldName = "警种id")
    private Long id;

    @ApiModelProperty("警种名称")
    @FieldNotEmpty(fieldName = "警种名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("备注")
    private String remark;

}
