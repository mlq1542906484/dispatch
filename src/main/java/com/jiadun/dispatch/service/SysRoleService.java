package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.sys.req.AddSysRoleVo;
import com.jiadun.dispatch.vo.sys.req.UpdateSysRoleVo;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> findRolesByUserId(Long id);

    void updateRole(UpdateSysRoleVo reqVo);

    void addRole(AddSysRoleVo reqVo);

    void delete(Long id);

}
