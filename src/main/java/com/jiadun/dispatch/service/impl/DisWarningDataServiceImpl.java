package com.jiadun.dispatch.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.entity.DisOperateRecord;
import com.jiadun.dispatch.entity.DisWarningData;
import com.jiadun.dispatch.entity.SysUser;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.DisWarningDataMapper;
import com.jiadun.dispatch.service.DisOperateRecordService;
import com.jiadun.dispatch.service.DisOperationResultService;
import com.jiadun.dispatch.service.DisWarningDataService;
import com.jiadun.dispatch.service.SysUserService;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.disPersonnel.DisWarningVo;
import com.jiadun.dispatch.vo.disPersonnel.GetPerSonnelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 预警表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Service
public class DisWarningDataServiceImpl extends ServiceImpl<DisWarningDataMapper, DisWarningData> implements DisWarningDataService {

    @Autowired
    private DisWarningDataMapper mapper;

    @Autowired
    private DisOperateRecordService operateRecordService;

    @Autowired
    private DisOperationResultService operationResultService;

    @Autowired
    private SysUserService userService;

    @Override
    public GetPerSonnelVo getWarningDataById(String id) {
        GetPerSonnelVo vo = new GetPerSonnelVo();
        DisWarningData data = super.getById(id);
        if (data == null) {
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR, "预警任务");
        }
        vo.setWarningData(data);
        List<DisOperateRecord> recordList = operateRecordService.list(Wrappers.<DisOperateRecord>query().lambda().eq(DisOperateRecord::getDataId, id).eq(DisOperateRecord::getType, 2).orderByAsc(DisOperateRecord::getITime));
        vo.setOperateRecords(recordList);
        List<Map<String,Object>> results = operationResultService.queryOperationResults(id,"2");
        vo.setOperationResults(results);
        return vo;
    }

    @Override
    public IPage<DisWarningVo> selectSystemWarning(Page<DisWarningVo> page, String deptId, String newssource, String type, String state) {
        return mapper.selectSystemWarning(page, deptId, newssource, type, state);
    }

    @Override
    public IPage<DisWarningVo> selectDeptWarning(Page<DisWarningVo> page, String deptId, String newssource, String type, String state) {
        SysUser user = userService.getById(SecurityUtils.getUser().getId());
        if (user == null || user.getDeptId() == null) {
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR, "用户");
        }
        return mapper.selectDeptWarning(page, user.getDeptId(), newssource, type, state);
    }

    @Override
    public IPage<DisWarningVo> selectStaffWarning(Page<DisWarningVo> page, Long deptId, String newssource, String type, String state) {
        SysUser user = userService.getById(SecurityUtils.getUser().getId());
        if (user == null || user.getDeptId() == null) {
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR, "用户");
        }
        return mapper.selectStaffWarning(page, user.getDeptId(), newssource, type, state);
    }
}
