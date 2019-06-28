package com.jiadun.dispatch.vo.sys.res;

import com.jiadun.dispatch.entity.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/20 15:04
 */
@ApiModel
@Data
public class UserInfoVo {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户真实姓名")
    private String name;

    @ApiModelProperty("用户名称")
    private String account;

    @ApiModelProperty("机构名称")
    private String deptName;

    @ApiModelProperty("机构id")
    private Long deptId;

    @ApiModelProperty("警种id")
    private Long policeTypeId;

    @ApiModelProperty("警种名称")
    private String policeTypeName;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("工号")
    private String jobNum;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("添加时间")
    private LocalDateTime itime;

    @ApiModelProperty("添加用户")
    private String createUser;

    @ApiModelProperty("地区id集合")
    private List<String> codes;

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("用户菜单")
    private List<SysResourceTreeVo> menus;

}
