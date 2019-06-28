package com.jiadun.dispatch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.entity.DisJoinSystem;
import com.jiadun.dispatch.entity.DisType;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.DisJoinSystemMapper;
import com.jiadun.dispatch.service.DisJoinSystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.dis.req.DisJoinSystemAddReqVo;
import com.jiadun.dispatch.vo.dis.req.DisJoinSystemUpdateReqVo;
import com.jiadun.dispatch.vo.dis.res.DisJoinSystemResVo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 接入系统表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Service
public class DisJoinSystemServiceImpl extends ServiceImpl<DisJoinSystemMapper, DisJoinSystem> implements DisJoinSystemService {

    @Override
    public void add(DisJoinSystemAddReqVo disJoinSystemReqVo) {
        int count = super.count(Wrappers.<DisJoinSystem>query().lambda().eq(DisJoinSystem::getCode,disJoinSystemReqVo.getCode()));
        if(count > 0){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_ALREADY_ERROR,"系统编码");
        }
        DisJoinSystem disJoinSystem = new DisJoinSystem();
        BeanUtil.copyProperties(disJoinSystemReqVo,disJoinSystem);
        disJoinSystem.setState(1);
        String userName = SecurityUtils.getUsername();
        disJoinSystem.setIUser(userName);
        disJoinSystem.setUUser(userName);
        super.save(disJoinSystem);
    }

    @Override
    public void update(DisJoinSystemUpdateReqVo disJoinSystemUpdateReqVo) {
        DisJoinSystem disJoinSystem = super.getById(disJoinSystemUpdateReqVo.getId());
        if(EmptyUtils.isNotEmpty(disJoinSystem)){
            BeanUtil.copyProperties(disJoinSystemUpdateReqVo,disJoinSystem);
            disJoinSystem.setUUser(SecurityUtils.getUsername());
            super.updateById(disJoinSystem);
        }else{
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"接入系统");
        }
    }

    @Override
    public List<DisJoinSystemResVo> findAll() {
        List<DisJoinSystem> list = super.list(Wrappers.<DisJoinSystem>query().lambda().eq(DisJoinSystem::getState,1).orderByAsc(DisJoinSystem::getITime));
        List<DisJoinSystemResVo> result = new LinkedList<>();
        if(EmptyUtils.isNotEmpty(list)){
            for (DisJoinSystem disJoinSystem : list) {
                DisJoinSystemResVo disJoinSystemResVo = new DisJoinSystemResVo();
                BeanUtil.copyProperties(disJoinSystem,disJoinSystemResVo);
                result.add(disJoinSystemResVo);
            }
        }

        return result;
    }

    @Override
    public void deleteById(Long id) {
        DisJoinSystem disJoinSystem = super.getById(id);
        if(EmptyUtils.isNotEmpty(disJoinSystem) || disJoinSystem.getState().equals(3)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"系统");
        }
        disJoinSystem.setState(3);
        disJoinSystem.setUUser(SecurityUtils.getUsername());
        super.updateById(disJoinSystem);

    }


}
