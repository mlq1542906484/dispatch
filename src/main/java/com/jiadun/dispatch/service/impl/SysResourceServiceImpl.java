package com.jiadun.dispatch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.TreeUtil;
import com.jiadun.dispatch.entity.SysResource;
import com.jiadun.dispatch.entity.SysRoleResource;
import com.jiadun.dispatch.entity.SysRoleUser;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.SysResourceMapper;
import com.jiadun.dispatch.service.SysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.service.SysRoleResourceService;
import com.jiadun.dispatch.service.SysRoleUserService;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.MenuVO;
import com.jiadun.dispatch.vo.WindCloudUser;
import com.jiadun.dispatch.vo.sys.req.AddMenuVo;
import com.jiadun.dispatch.vo.sys.req.UpdateMenuVo;
import com.jiadun.dispatch.vo.sys.res.SysResourceTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 访问资源表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Override
    public List<MenuVO> findMenuByRoleId(Long roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    public List<SysResourceTreeVo> loadAllMenu() {
        List<SysResourceTreeVo> list = baseMapper.loadAllMenu(1);
        return TreeUtil.assemblyTree(list,"id","pId","child",0l);
    }

    @Override
    public void addMenu(AddMenuVo addMenuVo) {
        if(EmptyUtils.isEmpty(addMenuVo.getPId())){
            addMenuVo.setPId(0l);
        }
        SysResource sysResource = new SysResource();
        BeanUtil.copyProperties(addMenuVo,sysResource);
        sysResource.setUUser(SecurityUtils.getUsername());
        sysResource.setIUser(SecurityUtils.getUsername());
        super.save(sysResource);
    }

    @Override
    public void updateMenu(UpdateMenuVo updateMenuVo) {
        SysResource sysResource = super.getById(updateMenuVo.getId());
        if(EmptyUtils.isEmpty(sysResource)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"菜单");
        }
        sysResource.setComponent(updateMenuVo.getComponent());
        sysResource.setDescription(updateMenuVo.getDescription());
        sysResource.setIsMenu(updateMenuVo.getIsMenu());
        sysResource.setName(updateMenuVo.getName());
        sysResource.setOrderNum(updateMenuVo.getOrderNum());
        sysResource.setPId(updateMenuVo.getPId());
        sysResource.setStandardName(updateMenuVo.getStandardName());
        sysResource.setResourceCode(updateMenuVo.getResourceCode());
        sysResource.setState(updateMenuVo.getState());
        sysResource.setUUser(SecurityUtils.getUsername());
        super.updateById(sysResource);
    }

    @Override
    public void delMenu(Long id) {
        SysResource sysResource = super.getById(id);
        if(EmptyUtils.isEmpty(sysResource)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"菜单");
        }
        sysResource.setState(3);
        super.updateById(sysResource);
    }

    @Override
    public List<SysResourceTreeVo> loadResoucesTree() {
        List<SysResourceTreeVo> list = baseMapper.loadAllMenu(null);
        return TreeUtil.assemblyTree(list,"id","pId","child",0l);
    }

    @Override
    public List<Long> findRoleResource(Long roleId) {
        List<SysRoleResource> list = sysRoleResourceService.list(Wrappers.<SysRoleResource>query().lambda().eq(SysRoleResource :: getRoleId , roleId));
        List<Long> resourceIds = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            resourceIds = list.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList());
        }
        return resourceIds;
    }

    @Override
    public List<SysResourceTreeVo> findRoleResourceByRoleId() {
        WindCloudUser windCloudUser = SecurityUtils.getUser();
        List<Long> roleIds = sysRoleUserService.findRoleIds(windCloudUser.getId());
        List<SysResourceTreeVo> list = baseMapper.findRoleResourceByRoleId(roleIds);
        return TreeUtil.assemblyTree(list,"id","pId","child",0l);
    }
}
