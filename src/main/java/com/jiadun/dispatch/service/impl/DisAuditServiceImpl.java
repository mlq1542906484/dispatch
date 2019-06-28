package com.jiadun.dispatch.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.entity.DisAudit;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.DisAuditMapper;
import com.jiadun.dispatch.service.DisAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.service.DisAutoConfigService;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.dis.res.DisPersonnelDiscoverDataResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 审批表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Service
public class DisAuditServiceImpl extends ServiceImpl<DisAuditMapper, DisAudit> implements DisAuditService {

    @Autowired
    private DisAuditMapper disAuditMapper;

    @Autowired
    private DisAutoConfigService disAutoConfigService;

    @Override
    public IPage<DisPersonnelDiscoverDataResVo> getAudit(Integer type,String newssource, Page page) {
        return disAuditMapper.getAudit(page,type,newssource,SecurityUtils.getUser().getId());
    }

    @Override
    public void receiveAudit(Long auditId) {
        if(EmptyUtils.isEmpty(auditId)){
            throw new RestBusinessException("审批id不能为空！",RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR);
        }
        disAutoConfigService.userReceiveAudit(auditId);
    }
}
