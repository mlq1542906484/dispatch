package com.jiadun.dispatch.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.entity.*;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.DisPersonnelDiscoverDataMapper;
import com.jiadun.dispatch.service.*;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.disPersonnel.DisPersonnelVo;
import com.jiadun.dispatch.vo.disPersonnel.GetPerSonnelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人员发现表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Service
public class DisPersonnelDiscoverDataServiceImpl extends ServiceImpl<DisPersonnelDiscoverDataMapper, DisPersonnelDiscoverData> implements DisPersonnelDiscoverDataService {

    @Autowired
    private DisPersonnelDiscoverDataMapper mapper;

    @Autowired
    private DisOperateRecordService operateRecordService;

    @Autowired
    private DisOperationResultService operationResultService;

    @Autowired
    private DisAuditResultService resultService;

    @Autowired
    private SysUserService userService;

    @Override
    public IPage<DisPersonnelVo> selectSystemPersonnelDiscovers(Page page, String deptId, String newssource, String type, String state) {
        return mapper.selectSystemPersonnelDiscovers(page, deptId, newssource, type, state);
    }

    @Override
    public IPage<DisPersonnelVo> selectDeptPersonnelDiscovers(Page page, String deptId, String newssource, String type, String state) {
        SysUser user = userService.getById(SecurityUtils.getUser().getId());
        if (user == null || user.getDeptId() == null) {
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR, "用户");
        }
        return mapper.selectDeptPersonnelDiscovers(page, user.getDeptId(), newssource, type, state);
    }

    @Override
    public IPage<DisPersonnelVo> selectStaffPersonnelDiscovers(Page page, Long deptId, String newssource, String type, String state) {
        SysUser user = userService.getById(SecurityUtils.getUser().getId());
        if (user == null || user.getDeptId() == null) {
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR, "用户");
        }
        return mapper.selectStaffPersonnelDiscovers(page, user.getDeptId(), newssource, type, state);
    }

    @Override
    public GetPerSonnelVo getPersonnelById(String id) {
        GetPerSonnelVo vo = new GetPerSonnelVo();
        DisPersonnelDiscoverData data = super.getById(id);
        if (data == null) {
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR, "任务");
        }
        vo.setPersonnelDiscoverData(data);
        List<DisOperateRecord> recordList = operateRecordService.list(Wrappers.<DisOperateRecord>query().lambda().eq(DisOperateRecord::getDataId, id).eq(DisOperateRecord::getType, 1).orderByAsc(DisOperateRecord::getITime));
        vo.setOperateRecords(recordList);
        List<Map<String,Object>> results = operationResultService.queryOperationResults(id,"1");
        vo.setOperationResults(results);
        List<DisAuditResult>  auditResults = resultService.list(Wrappers.<DisAuditResult>query().lambda().eq(DisAuditResult::getDataId, id).eq(DisAuditResult::getType, 1).orderByAsc(DisAuditResult::getITime));
        vo.setAuditResults(auditResults);
        return vo;
    }
}
