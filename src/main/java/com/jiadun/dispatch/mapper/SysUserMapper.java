package com.jiadun.dispatch.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiadun.dispatch.vo.sys.res.UserInfoVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * @description: 分页查询用户列表
     * @author: caiping
     * @date: 2019/6/20 15:16
     * @param: [page]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.jiadun.dispatch.vo.sys.res.UserInfoVo>
     */
    IPage<UserInfoVo> pageList(Page page);

    Integer getLoginAreaType(@Param("loginName") String loginName);

}
