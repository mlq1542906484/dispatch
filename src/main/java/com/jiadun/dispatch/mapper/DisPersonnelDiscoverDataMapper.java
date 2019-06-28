package com.jiadun.dispatch.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.DisPersonnelDiscoverData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiadun.dispatch.vo.disPersonnel.DisPersonnelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 人员发现表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Mapper
public interface DisPersonnelDiscoverDataMapper extends BaseMapper<DisPersonnelDiscoverData> {


    IPage<DisPersonnelVo> selectSystemPersonnelDiscovers(Page page, @Param("deptId")String deptId, @Param("newssource")String newssource, @Param("type")String type, @Param("state")String state);

    IPage<DisPersonnelVo> selectDeptPersonnelDiscovers(Page page, @Param("deptId")Long deptId, @Param("newssource")String newssource, @Param("type")String type, @Param("state")String state);

    IPage<DisPersonnelVo> selectStaffPersonnelDiscovers(Page page, @Param("deptId")Long deptId, @Param("newssource")String newssource, @Param("type")String type, @Param("state")String state);



}
