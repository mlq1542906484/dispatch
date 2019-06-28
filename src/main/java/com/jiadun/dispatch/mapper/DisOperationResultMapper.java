package com.jiadun.dispatch.mapper;

import com.jiadun.dispatch.entity.DisOperationResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据反馈结果表 Mapper 接口
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Mapper
public interface DisOperationResultMapper extends BaseMapper<DisOperationResult> {

    List<Map<String,Object>> queryOperationResults(@Param("dataId") String dataId,@Param("type") String type);
}
