package com.jiadun.dispatch.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.DisWarningData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiadun.dispatch.vo.disPersonnel.DisPersonnelVo;
import com.jiadun.dispatch.vo.disPersonnel.DisWarningVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 预警表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Mapper
public interface DisWarningDataMapper extends BaseMapper<DisWarningData> {


    IPage<DisWarningVo> selectSystemWarning(Page page, @Param("deptId")String deptId, @Param("newssource")String newssource, @Param("type")String type, @Param("state")String state);

    IPage<DisWarningVo> selectDeptWarning(Page page, @Param("deptId")Long deptId, @Param("newssource")String newssource, @Param("type")String type, @Param("state")String state);

    IPage<DisWarningVo> selectStaffWarning(Page page, @Param("deptId")Long deptId, @Param("newssource")String newssource, @Param("type")String type, @Param("state")String state);


}
