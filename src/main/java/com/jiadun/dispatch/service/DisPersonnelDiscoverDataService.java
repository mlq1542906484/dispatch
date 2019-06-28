package com.jiadun.dispatch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.DisPersonnelDiscoverData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.disPersonnel.DisPersonnelVo;
import com.jiadun.dispatch.vo.disPersonnel.GetPerSonnelVo;

/**
 * <p>
 * 人员发现表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
public interface DisPersonnelDiscoverDataService extends IService<DisPersonnelDiscoverData> {

    IPage<DisPersonnelVo> selectSystemPersonnelDiscovers(Page page, String deptId, String newssource, String type, String state);

    IPage<DisPersonnelVo> selectDeptPersonnelDiscovers(Page page, String deptId, String newssource, String type, String state);

    IPage<DisPersonnelVo> selectStaffPersonnelDiscovers(Page page, Long userId, String newssource, String type, String state);




    GetPerSonnelVo getPersonnelById(String id);
}
