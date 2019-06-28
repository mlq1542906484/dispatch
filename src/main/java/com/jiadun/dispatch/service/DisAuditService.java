package com.jiadun.dispatch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.DisAudit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.dis.res.DisPersonnelDiscoverDataResVo;

/**
 * <p>
 * 审批表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
public interface DisAuditService extends IService<DisAudit> {

    /**
     * @describe: 获取待审批或已审批数据
     * @author: hcl  
     * @date: 2019/6/27 9:50
     * @param: [type, page]  
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.jiadun.dispatch.vo.dis.res.DisPersonnelDiscoverDataResVo>  
     */
    IPage<DisPersonnelDiscoverDataResVo> getAudit(Integer type,String newssource, Page page);

    /**
     * @describe: 接收审批id
     * @author: hcl  
     * @date: 2019/6/27 13:55
     * @param: [auditId]  
     * @return void  
     */
    void receiveAudit(Long auditId);
}
