package com.jiadun.dispatch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.entity.DisType;
import com.jiadun.dispatch.mapper.DisTypeMapper;
import com.jiadun.dispatch.service.DisTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.vo.dis.res.DisTypeResVo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 数据类型表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Service
public class DisTypeServiceImpl extends ServiceImpl<DisTypeMapper, DisType> implements DisTypeService {

    @Override
    public List<DisTypeResVo> findAll() {
        List<DisType> list = super.list(Wrappers.<DisType>query().lambda().eq(DisType::getState,1).orderByAsc(DisType::getITime));
        List<DisTypeResVo> result = new LinkedList<>();
        if(EmptyUtils.isNotEmpty(list)){
            for (DisType disType : list) {
                DisTypeResVo disTypeResVo = new DisTypeResVo();
                BeanUtil.copyProperties(disType,disTypeResVo);
                result.add(disTypeResVo);
            }
        }
        return result;
    }
}
