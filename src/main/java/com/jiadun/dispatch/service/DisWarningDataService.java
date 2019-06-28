package com.jiadun.dispatch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.DisWarningData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.disPersonnel.DisPersonnelVo;
import com.jiadun.dispatch.vo.disPersonnel.DisWarningVo;
import com.jiadun.dispatch.vo.disPersonnel.GetPerSonnelVo;

/**
 * <p>
 * 预警表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
public interface DisWarningDataService extends IService<DisWarningData> {

    GetPerSonnelVo getWarningDataById(String id);

    /**
     *  获取系统级别预警
     */
    IPage<DisWarningVo> selectSystemWarning(Page<DisWarningVo> page, String deptId, String newssource, String type, String state);

    /**
     *  获取部门级别预警
     */
    IPage<DisWarningVo> selectDeptWarning(Page<DisWarningVo> page, String deptId, String newssource, String type, String state);

    /**
     *  获取人员级别预警
     */
    IPage<DisWarningVo> selectStaffWarning(Page<DisWarningVo> page, Long userId, String newssource, String type, String state);
}
