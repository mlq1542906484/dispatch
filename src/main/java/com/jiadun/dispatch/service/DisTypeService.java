package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.DisType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.dis.res.DisTypeResVo;

import java.util.List;

/**
 * <p>
 * 数据类型表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
public interface DisTypeService extends IService<DisType> {

    /**
     * @describe: 获取所有有效的类型
     * @author: hcl  
     * @date: 2019/6/21 11:38  
     * @param: []  
     * @return java.util.List<com.jiadun.dispatch.entity.DisType>  
     */
    List<DisTypeResVo> findAll();
}
