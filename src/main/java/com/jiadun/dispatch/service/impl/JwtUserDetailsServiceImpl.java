package com.jiadun.dispatch.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiadun.dispatch.common.SecurityConstants;
import com.jiadun.dispatch.entity.SysUser;
import com.jiadun.dispatch.mapper.SysResourceMapper;
import com.jiadun.dispatch.mapper.SysRoleResourceMapper;
import com.jiadun.dispatch.mapper.SysRoleUserMapper;
import com.jiadun.dispatch.mapper.SysUserMapper;
import com.jiadun.dispatch.service.SysUserService;
import com.jiadun.dispatch.vo.UserInfo;
import com.jiadun.dispatch.vo.WindCloudUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.selectOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getAccount, username));

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            UserInfo info = sysUserService.findUserInfo(user);
            Set<String> dbAuthsSet = new HashSet<>();
            if (ArrayUtil.isNotEmpty(info.getRoles())) {
                // 获取资源
                dbAuthsSet.addAll(info.getPermissions());
            }

            Collection<? extends GrantedAuthority> authorities
                    = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
            // 构造security用户
            return new WindCloudUser(user.getId(), user.getAccount(), user.getPwd(), true,
                    true, true,true, authorities);
//            return JwtUserFactory.create(info);
        }
    }

}
