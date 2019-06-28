package com.jiadun.dispatch.service.impl;

import com.jiadun.dispatch.entity.DisOperationResult;
import com.jiadun.dispatch.mapper.DisOperationResultMapper;
import com.jiadun.dispatch.service.DisOperationResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据反馈结果表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Service
public class DisOperationResultServiceImpl extends ServiceImpl<DisOperationResultMapper, DisOperationResult> implements DisOperationResultService {

    @Autowired
    private DisOperationResultMapper mapper;

    @Override
    public List<Map<String, Object>> queryOperationResults(String dataId, String type) {
        return mapper.queryOperationResults(dataId,type);
    }
}
