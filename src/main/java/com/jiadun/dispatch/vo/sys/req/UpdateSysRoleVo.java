package com.jiadun.dispatch.vo.sys.req;

import com.jiadun.dispatch.validator.annotation.FieldNotEmpty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class UpdateSysRoleVo {

    @FieldNotEmpty(fieldName = "角色ID")
    private Long Id;

    /**
     * 角色名称
     */
    @FieldNotEmpty(fieldName = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * @describe: 系统资源id集合
     */
    private List<Long> sysResourceIds;
}
