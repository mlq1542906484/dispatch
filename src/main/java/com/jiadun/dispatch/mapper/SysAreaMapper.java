package com.jiadun.dispatch.mapper;

import com.jiadun.dispatch.entity.SysArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiadun.dispatch.vo.sys.res.SysAreaTreeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-20
 */
@Mapper
public interface SysAreaMapper extends BaseMapper<SysArea> {

    List<SysAreaTreeVo> getAreaTree();
}
