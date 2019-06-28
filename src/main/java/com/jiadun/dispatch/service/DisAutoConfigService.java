package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.DisAutoConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.entity.DisDataOperation;
import com.jiadun.dispatch.vo.dis.req.DisAutoConfigAddReqVo;
import com.jiadun.dispatch.vo.dis.req.DisAutoConfigQueryReqVo;
import com.jiadun.dispatch.vo.dis.req.DisOperationResultReqVo;
import com.jiadun.dispatch.vo.dis.req.ManualDisAutoConfigReqVo;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务自动下发配置表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
public interface DisAutoConfigService extends IService<DisAutoConfig> {

    /**
     * @describe: 添加自动转发配置
     * @author: hcl  
     * @date: 2019/6/24 9:45  
     * @param: [disAutoConfigAddReqVo]  
     * @return void  
     */
    void add(DisAutoConfigAddReqVo disAutoConfigAddReqVo);

    /**
     * @describe: 根据条件获取配置
     * @author: hcl  
     * @date: 2019/6/24 11:38  
     * @param: [disAutoConfigQueryReqVo]  
     * @return com.jiadun.dispatch.vo.dis.req.DisAutoConfigAddReqVo  
     */
    DisAutoConfigAddReqVo find(DisAutoConfigQueryReqVo disAutoConfigQueryReqVo);


    
    /**
     * @describe: 创建操作
     * @author: hcl  
     * @date: 2019/6/24 15:54
     * @param: [type (1:人员发现,2:预警表),
     *              id (数据id),
     *              superiorDataOperationId (上级操作id，为null表示顶级),
     *              deptId (接收的部门id,为null表示系统),
     *              acceptDeadline (最后接收时间,为null表示不限制),
     *              continueIssued (是否允许自动下发)
     *              ]
     * @return void  
     */
    void createOperation(Integer dataType,
                         Long id,
                         Long superiorDataOperationId,
                         Long deptId,
                         LocalDateTime acceptDeadline,
                         Boolean continueIssued);

    /**
     * @describe: 根据操作记录,创建下发实例
     * @author: hcl  
     * @date: 2019/6/24 16:07  
     * @param: [disDataOperation]  
     * @return void  
     */
    void createIssuedInstance(DisDataOperation disDataOperation);

    /**
     * @describe: 手动根据操作记录id,创建下发实例
     * @author: hcl
     * @date: 2019/6/24 16:07
     * @param: [disDataOperation (操作记录id),
     *          manualDisAutoConfigReqVo (手动调度配置)]
     * @return void
     */
    void manualCreateIssuedInstance(Long disDataOperationId,ManualDisAutoConfigReqVo manualDisAutoConfigReqVo);

    /**
     * @describe: 根据操作记录id,手动接收数据
     * @author: hcl
     * @date: 2019/6/25 14:59
     * @param: [disDataOperationId (操作记录id)]
     * @return void
     */
    void manualAccept(Long disDataOperationId);


    /**
     * @describe: 用户根据操作记录id,手动接收数据(人员发现可接收多次)
     * @author: hcl
     * @date: 2019/6/25 14:59
     * @param: [disDataOperationId (操作记录id)]
     * @return void
     */
    void userManualAccept(Long disDataOperationId);

    /**
     * @describe: 用户接收审批
     * @author: hcl
     * @date: 2019/6/27 13:57
     * @param: [auditId]
     * @return void
     */
    void userReceiveAudit(Long auditId);




    /**
     * @describe: 用户提交结果或者给操作提交结果
     * @author: hcl  
     * @date: 2019/6/27 14:24
     * @param: [disOperationResultReqVo]  
     * @return void  
     */
    void userSubmitResult(Long userId,DisOperationResultReqVo disOperationResultReqVo);


    /**
     * @describe: 下一步操作
     * @author: hcl  
     * @date: 2019/6/27 17:36
     * @param: [disDataOperationId]  
     * @return void  
     */
//    void nextStepOperation(DisDataOperation disDataOperation);


}
