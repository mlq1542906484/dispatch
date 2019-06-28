package com.jiadun.dispatch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.entity.SysDept;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.SysDeptMapper;
import com.jiadun.dispatch.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.sys.req.AddDeptVo;
import com.jiadun.dispatch.vo.sys.req.UpdateDeptVo;
import com.jiadun.dispatch.vo.sys.res.SysDeptVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-19
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public void addDept(AddDeptVo addDeptVo) {
        List<SysDept> sysDepts = baseMapper.selectList(Wrappers.<SysDept>query().lambda().eq(SysDept::getAreaCode,addDeptVo.getAreaCode()).eq(SysDept::getType,addDeptVo.getType()));
        if(EmptyUtils.isNotEmpty(sysDepts)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"该地区同类型的部门");
        }
        SysDept sysDept = new SysDept();
        BeanUtil.copyProperties(addDeptVo,sysDept);
        sysDept.setState(1);
        String username = SecurityUtils.getUsername();
        sysDept.setIUser(username);
        sysDept.setUUser(username);
        baseMapper.insert(sysDept);
    }

    @Override
    public void updateDept(UpdateDeptVo updateDeptVo) {
        SysDept sysDept = baseMapper.selectById(updateDeptVo.getId());
        if(EmptyUtils.isEmpty(sysDept)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"");
        }

        List<SysDept> sysDepts = baseMapper.selectList(Wrappers.<SysDept>query().lambda().eq(SysDept::getAreaCode,updateDeptVo.getAreaCode()).eq(SysDept::getType,updateDeptVo.getType()).ne(SysDept::getId,updateDeptVo.getId()));
        if(EmptyUtils.isNotEmpty(sysDepts)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"该地区同类型的部门");
        }
        BeanUtil.copyProperties(updateDeptVo,sysDept);
        String username = SecurityUtils.getUsername();
        sysDept.setUUser(username);
        baseMapper.updateById(sysDept);

    }

    @Override
    public IPage<SysDeptVo> listPage(String code, Page page) {
        return baseMapper.listPage(page,code);
    }

    @Override
    public List<SysDept> getDeptByAreaCode(String code) {
        return baseMapper.selectList(Wrappers.<SysDept>query().lambda().eq(SysDept::getAreaCode,code).eq(SysDept::getState,1).orderByDesc(SysDept::getType));
    }

    @Override
    public void delDept(Long id) {
        SysDept sysDept = baseMapper.selectById(id);
        sysDept.setState(3);
        baseMapper.updateById(sysDept);

    }
}
