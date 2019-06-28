package com.jiadun.dispatch.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiadun.dispatch.vo.sys.res.SysDeptVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-19
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * @description: 分页查询单位
     * @author: caiping
     * @date: 2019/6/21 15:10
     * @param: [page, code]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.jiadun.dispatch.entity.SysDept>
     */
    IPage<SysDeptVo> listPage(Page page,@Param("code") String code);
}
