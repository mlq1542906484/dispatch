package com.jiadun.dispatch.vo.sys.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/20 17:21
 */
@ApiModel
@Data
public class SysAreaTreeVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("code")
    private String code;

    @ApiModelProperty("上级code")
    private String pCode;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("类型 1:省；2:市；3:区、县；")
    private String type;

    @ApiModelProperty("子节点")
    private List<SysAreaTreeVo> child;

}
