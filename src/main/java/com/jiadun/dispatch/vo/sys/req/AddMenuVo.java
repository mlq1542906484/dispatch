package com.jiadun.dispatch.vo.sys.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/21 9:21
 */
@ApiModel
@Data
public class AddMenuVo {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("标准名称")
    private String standardName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("资源代码")
    private String resourceCode;

    @ApiModelProperty("排序号")
    private Integer orderNum;

    @ApiModelProperty("状态=1: 启用,2:停用,3:删除")
    private Integer state;

    @ApiModelProperty("是否是菜单")
    private Boolean isMenu;

    @ApiModelProperty("主键")
    private String component;

    @ApiModelProperty("上级id")
    private Long pId;

}
