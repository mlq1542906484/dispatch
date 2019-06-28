package com.jiadun.dispatch.mapper;

import com.jiadun.dispatch.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiadun.dispatch.vo.MenuVO;
import com.jiadun.dispatch.vo.sys.res.SysResourceTreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 访问资源表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Mapper
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<MenuVO> listMenusByRoleId(@Param("roleId") Long roleId);

    List<SysResourceTreeVo> loadAllMenu(@Param("isMenu") Integer isMenu);

    List<SysResourceTreeVo> findRoleResourceByRoleId(@Param("roleIds") List<Long> roleId);
}
