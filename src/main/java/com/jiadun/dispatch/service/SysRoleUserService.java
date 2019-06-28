package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
public interface SysRoleUserService extends IService<SysRoleUser> {

    void deleteByUserId(Long id);

    List<Long> findRoleIds(Long userId);
}
