package com.jiadun.dispatch.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.DisAudit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiadun.dispatch.vo.dis.res.DisPersonnelDiscoverDataResVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 审批表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Mapper
public interface DisAuditMapper extends BaseMapper<DisAudit> {

    /**
     * @describe: 查询待审核数据
     * @author: hcl  
     * @date: 2019/6/27 11:31
     * @param: [page, type, id]  
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.jiadun.dispatch.vo.dis.res.DisPersonnelDiscoverDataResVo>  
     */
    IPage<DisPersonnelDiscoverDataResVo> getAudit(Page page, @Param("type") Integer type,@Param("newssource")String newssource,@Param("userId") Long userId);
}
