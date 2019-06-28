package com.jiadun.dispatch.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.config.Clients;
import com.jiadun.dispatch.entity.SysRole;
import com.jiadun.dispatch.entity.SysRoleResource;
import com.jiadun.dispatch.entity.SysRoleUser;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.SysRoleMapper;
import com.jiadun.dispatch.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.sys.req.AddSysRoleVo;
import com.jiadun.dispatch.vo.sys.req.UpdateSysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private Clients clients;

    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public List<SysRole> findRolesByUserId(Long id) {
        return baseMapper.listRolesByUserId(id);
    }

    @Override
    @Transactional
    public void updateRole(UpdateSysRoleVo reqVo) {
        List<SysRole> list = super.list(Wrappers.<SysRole>query().lambda().eq(SysRole::getName, reqVo.getName()).eq(SysRole::getState,1));
        if(CollectionUtils.isNotEmpty(list) && list.get(0).getId() != reqVo.getId()){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_ALREADY_ERROR,"名称");
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(reqVo.getId());
        sysRole.setName(reqVo.getName());
        sysRole.setDescription(reqVo.getDescription());
        sysRole.setState(1);
        String loginName = SecurityUtils.getUsername();
        LocalDateTime now = LocalDateTime.now();
        sysRole.setUUser(loginName);
        sysRole.setUTime(now);
        super.saveOrUpdate(sysRole);

        //解除和系统及资源的关系
        releaseRelationship(reqVo.getId());
        //重新添加关系
        addRelationship(reqVo.getId(),reqVo.getSysResourceIds());

    }

    @Override
    @Transactional
    public void addRole(AddSysRoleVo reqVo) {
        Integer count = super.count(Wrappers.<SysRole>query().lambda().eq(SysRole::getName, reqVo.getName()).eq(SysRole::getState,1));
        if(count > 0){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_ALREADY_ERROR,"名称");
        }
        SysRole sysRole = new SysRole();
        sysRole.setState(1);
        sysRole.setName(reqVo.getName());
        sysRole.setDescription(reqVo.getDescription());
        String loginName = SecurityUtils.getUsername();
        LocalDateTime now = LocalDateTime.now();
        sysRole.setIUser(loginName);
        sysRole.setITime(now);
        sysRole.setUUser(loginName);
        sysRole.setUTime(now);
        super.saveOrUpdate(sysRole);

        addRelationship(sysRole.getId(),reqVo.getSysResourceIds());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        releaseRelationship(id);
        SysRole model = new SysRole();
        model.setId(id);
        model.setState(3);
        super.updateById(model);
    }

    @Transactional
    void releaseRelationship(Long roleId){
        sysRoleUserService.remove(Wrappers.<SysRoleUser>query().lambda().eq(SysRoleUser :: getRoleId , roleId));
        sysRoleResourceService.remove(Wrappers.<SysRoleResource>query().lambda().eq(SysRoleResource :: getRoleId , roleId));
    }

    @Transactional
    void addRelationship(Long roleId, List<Long> sysResourceIds){
        if(CollectionUtils.isEmpty(sysResourceIds)){
            return;
        }
        String loginName = SecurityUtils.getUsername();
        LocalDateTime now = LocalDateTime.now();
        List<SysRoleResource> list = new ArrayList<>();
        for(Long resourceId : sysResourceIds){
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setResourceId(resourceId);
            sysRoleResource.setRoleId(roleId);
            sysRoleResource.setITime(now);
            sysRoleResource.setUTime(now);
            sysRoleResource.setIUser(loginName);
            sysRoleResource.setUUser(loginName);
            list.add(sysRoleResource);
        }
        sysRoleResourceService.saveBatch(list);

    }
}
