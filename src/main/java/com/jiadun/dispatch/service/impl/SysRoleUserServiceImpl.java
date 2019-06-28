package com.jiadun.dispatch.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.entity.SysRoleUser;
import com.jiadun.dispatch.mapper.SysRoleUserMapper;
import com.jiadun.dispatch.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

    @Override
    public void deleteByUserId(Long id) {
        super.remove(Wrappers.<SysRoleUser>query().lambda().eq(SysRoleUser::getUserId,id));
    }

    @Override
    public List<Long> findRoleIds(Long userId) {
        return baseMapper.findRoleIds(userId);
    }

}
