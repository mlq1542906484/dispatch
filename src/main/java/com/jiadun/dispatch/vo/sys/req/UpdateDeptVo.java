package com.jiadun.dispatch.vo.sys.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/21 14:41
 */
@ApiModel
@Data
public class UpdateDeptVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("1:公安部，2：国保，3：网安")
    private Integer type;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("地区code")
    private String areaCode;

}
