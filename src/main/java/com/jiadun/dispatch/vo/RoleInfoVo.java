package com.jiadun.dispatch.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/1/2 16:33
 */
@ApiModel
@Data
public class RoleInfoVo {

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("角色代码")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String  roleName;

    @ApiModelProperty("角色描述")
    private String remark;

    @ApiModelProperty("1:启用 2:删除 0：禁用")
    private int status;

}
