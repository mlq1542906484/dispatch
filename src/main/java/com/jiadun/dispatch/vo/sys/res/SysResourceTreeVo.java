package com.jiadun.dispatch.vo.sys.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/21 9:00
 */
@ApiModel
@Data
public class SysResourceTreeVo {

    /**
     * 主键
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 资源名称
     */
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("标准名称")
    private String standardName;

    /**
     * 资源描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 资源代码
     */
    @ApiModelProperty("资源代码")
    private String resourceCode;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer orderNum;

    /**
     * 状态=1: 启用,2:停用,3:删除
     */
    @ApiModelProperty("状态=1: 启用,2:停用,3:删除")
    private Integer state;

    /**
     * 菜单=1:是,0:否
     */
    @ApiModelProperty("是否是菜单")
    private Boolean isMenu;

    /**
     * 组件
     */
    @ApiModelProperty("主键")
    private String component;

    /**
     * 父资源id
     */
    @ApiModelProperty("上级id")
    private Long pId;

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

    private List<SysResourceTreeVo> child;

}
