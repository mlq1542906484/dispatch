package com.jiadun.dispatch.vo.sys.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/21 15:16
 */
@ApiModel
@Data
public class SysDeptVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("1:公安部，2：国保，3：网安")
    private Integer type;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("部门地区")
    private String areaCode;

    /**
     * 状态=1: 启用,2:停用,3:删除
     */
    @ApiModelProperty("状态=1: 启用,2:停用,3:删除")
    private Integer state;

    /**
     * 插入用户的登录名
     */
    private String iUser;

    /**
     * 插入时间
     */
    private LocalDateTime iTime;

    /**
     * 最后更新用户的登录名
     */
    private String uUser;

    /**
     * 最后更新时间
     */
    private LocalDateTime uTime;
}
