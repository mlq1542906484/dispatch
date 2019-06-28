package com.jiadun.dispatch.vo;

import com.jiadun.dispatch.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/1/4 9:50
 */
@Data
public class UserInfo {

    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private List<String> permissions;

    /**
     * 角色集合
     */
    private Long [] roles;

}
