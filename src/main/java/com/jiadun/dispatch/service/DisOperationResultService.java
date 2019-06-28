package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.DisOperationResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据反馈结果表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
public interface DisOperationResultService extends IService<DisOperationResult> {


    List<Map<String,Object>>  queryOperationResults(String dataId,String type);

}
