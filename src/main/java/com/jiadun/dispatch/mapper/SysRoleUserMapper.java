package com.jiadun.dispatch.mapper;

import com.jiadun.dispatch.entity.SysRoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    List<Long> findRoleIds(Long userId);
}
