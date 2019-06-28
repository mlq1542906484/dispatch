package com.jiadun.dispatch.vo.sys.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/19 14:06
 */
@ApiModel
@Data
public class AddUserVo {

    @ApiModelProperty("用户名")
    private String account;


    @ApiModelProperty("真实名称")
    private String name;

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("手机号")
    private String tel;

    @ApiModelProperty("工号")
    private String jobNum;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("地区编码")
    private String areaCode;

    @ApiModelProperty("警种ID")
    private Long policeTypeId;

}
