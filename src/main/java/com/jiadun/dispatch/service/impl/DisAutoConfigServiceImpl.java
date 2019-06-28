package com.jiadun.dispatch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.ReflectUtil;
import com.jiadun.dispatch.entity.*;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.mapper.DisAutoConfigMapper;
import com.jiadun.dispatch.service.*;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.dis.req.DisAutoConfigAddReqVo;
import com.jiadun.dispatch.vo.dis.req.DisAutoConfigQueryReqVo;
import com.jiadun.dispatch.vo.dis.req.DisOperationResultReqVo;
import com.jiadun.dispatch.vo.dis.req.ManualDisAutoConfigReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 任务自动下发配置表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Service
@Slf4j
public class DisAutoConfigServiceImpl extends ServiceImpl<DisAutoConfigMapper, DisAutoConfig> implements DisAutoConfigService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DisDataOperationService disDataOperationService;


    @Autowired
    private DisIssuedInstanceService disIssuedInstanceService;

    @Autowired
    private DisPersonnelDiscoverDataService disPersonnelDiscoverDataService;

    @Autowired
    private DisWarningDataService disWarningDataService;

    @Autowired
    private SysAreaService sysAreaService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private DisAuditService disAuditService;


    @Autowired
    private DisOperationResultService disOperationResultService;


    @Override
    public void add(DisAutoConfigAddReqVo disAutoConfigAddReqVo) {


        if(disAutoConfigAddReqVo.getIsAudit() == null){
            disAutoConfigAddReqVo.setIsAudit(false);
        }else if(disAutoConfigAddReqVo.getIsAudit() && EmptyUtils.isEmpty(disAutoConfigAddReqVo.getAuditUserId())){
            throw new RestBusinessException("审核用户不能为空！",RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR);
        }

        if(disAutoConfigAddReqVo.getCommitType() == null){
            disAutoConfigAddReqVo.setCommitType(2);
            disAutoConfigAddReqVo.setCommitDelayedMinute(0);
        }

        LambdaQueryWrapper<DisAutoConfig> compare = Wrappers.<DisAutoConfig>query().lambda()
                .eq(DisAutoConfig::getType,disAutoConfigAddReqVo.getType())
                .eq(DisAutoConfig::getDataType,disAutoConfigAddReqVo.getDataType())
                .eq(DisAutoConfig::getSystemCode,disAutoConfigAddReqVo.getSystemCode())
                .eq(DisAutoConfig::getMatterLevel,disAutoConfigAddReqVo.getMatterLevel());
        Long deptId = null;
        if(disAutoConfigAddReqVo.getType().equals(2)){
            SysUser sysUser = sysUserService.getById(SecurityUtils.getUser().getId());
            deptId = sysUser.getDeptId();
            if(EmptyUtils.isEmpty(deptId)){
                throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"当前用户部门");
            }
            compare.eq(DisAutoConfig::getDeptId,deptId);
        }
        DisAutoConfig disAutoConfig = super.getOne(compare);
        String userName = SecurityUtils.getUsername();
        if(EmptyUtils.isEmpty(disAutoConfig)){
            //新增
            disAutoConfig = new DisAutoConfig();
            BeanUtil.copyProperties(disAutoConfigAddReqVo,disAutoConfig);
            disAutoConfig.setDeptId(deptId);
            disAutoConfig.setIUser(userName);
            disAutoConfig.setUUser(userName);
            super.save(disAutoConfig);
        }else{
            //修改
            BeanUtil.copyProperties(disAutoConfigAddReqVo,disAutoConfig);
            disAutoConfig.setUUser(userName);
            super.updateById(disAutoConfig);
        }
    }

    @Override
    public DisAutoConfigAddReqVo find(DisAutoConfigQueryReqVo disAutoConfigQueryReqVo) {
        LambdaQueryWrapper<DisAutoConfig> compare = Wrappers.<DisAutoConfig>query().lambda()
                .eq(DisAutoConfig::getType,disAutoConfigQueryReqVo.getType())
                .eq(DisAutoConfig::getDataType,disAutoConfigQueryReqVo.getDataType())
                .eq(DisAutoConfig::getSystemCode,disAutoConfigQueryReqVo.getSystemCode())
                .eq(DisAutoConfig::getMatterLevel,disAutoConfigQueryReqVo.getMatterLevel());
        Long deptId = null;
        if(disAutoConfigQueryReqVo.getType().equals(2)){
            SysUser sysUser = sysUserService.getById(SecurityUtils.getUser().getId());
            deptId = sysUser.getDeptId();
            if(EmptyUtils.isEmpty(deptId)){
                throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"当前用户部门");
            }
            compare.eq(DisAutoConfig::getDeptId,deptId);
        }
        DisAutoConfig disAutoConfig = super.getOne(compare);
        DisAutoConfigAddReqVo disAutoConfigAddReqVo = new DisAutoConfigAddReqVo();
        if(EmptyUtils.isNotEmpty(disAutoConfig)){
            BeanUtil.copyProperties(disAutoConfig,disAutoConfigAddReqVo);
        }
        return disAutoConfigAddReqVo;
    }

    @Override
    @Transactional
    public void createOperation(Integer dataType,
                                Long id,
                                Long superiorDataOperationId,
                                Long deptId,
                                LocalDateTime acceptDeadline,
                                Boolean continueIssued) {
        if(EmptyUtils.isNotEmptys(dataType,id)){
            DisDataOperation disDataOperation = new DisDataOperation();
            disDataOperation.setType(dataType);
            disDataOperation.setDataId(id);
            disDataOperation.setState(1);
            disDataOperation.setPId(superiorDataOperationId);
            disDataOperation.setDeptId(deptId);
            disDataOperation.setAcceptDeadline(acceptDeadline);
            disDataOperation.setContinueIssued(continueIssued);
            disDataOperationService.save(disDataOperation);
            if(continueIssued){
                this.createIssuedInstance(disDataOperation);
            }
        }else{
            throw new RestBusinessException("类型、id 不能为空！", RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR);
        }
    }

    @Override
    @Transactional
    public void createIssuedInstance(DisDataOperation disDataOperation) {
        //获取数据
        Object data = getData(disDataOperation.getDataId(),disDataOperation.getType());

        Object systemCodeObj = ReflectUtil.executeGet(data,"newssource");
        String systemCode = EmptyUtils.isEmpty(systemCodeObj) ? null : systemCodeObj.toString();
        Object importanceObj = ReflectUtil.executeGet(data,"importance");
        Integer importance = EmptyUtils.isEmpty(importanceObj) ? null : Integer.parseInt(importanceObj.toString());
        if(EmptyUtils.isEmptys(systemCode,importance)){
            log.warn("缺少系统编号或重要级别，无法进行自动下发！");
            return ;
        }

        //获取配置
        Long deptId = disDataOperation.getDeptId();
        Integer type = 2;
        if(EmptyUtils.isEmpty(deptId)){
            type = 1;
        }
        LambdaQueryWrapper<DisAutoConfig> compare = Wrappers.<DisAutoConfig>query().lambda()
                .eq(DisAutoConfig::getType,type)
                .eq(DisAutoConfig::getDataType,disDataOperation.getType())
                .eq(DisAutoConfig::getSystemCode,systemCode)
                .eq(DisAutoConfig::getMatterLevel,importance);

        if(type.equals(2)){
            compare.eq(DisAutoConfig::getDeptId,deptId);
        }
        DisAutoConfig disAutoConfig = super.getOne(compare);
        if(EmptyUtils.isEmpty(disAutoConfig)){
            log.warn("系统或当前部门缺少自动转发配置，无法进行自动下发！");
            return ;
        }

        DisIssuedInstance disIssuedInstance = new DisIssuedInstance();
        disIssuedInstance.setOperationId(disDataOperation.getId());
        disIssuedInstance.setIsIssue(false);

        Integer issueDelayedMinute = disAutoConfig.getIssueDelayedMinute();
        boolean immediatelyIssued = false;//是否是立即下发
        if(EmptyUtils.isEmpty(issueDelayedMinute) || issueDelayedMinute.equals(0)){
            immediatelyIssued = true;
            issueDelayedMinute = 0;
        }
        disIssuedInstance.setIssueDate(LocalDateTime.now().plusMinutes(issueDelayedMinute));//设置下发时间(当前时间+延后下发分钟数)

        //获取下发地址
        String persionArea = null;
        if(EmptyUtils.isEmpty(disDataOperation.getPId())){
            //从基础信息中获取
            if(disAutoConfig.getPersionArea().equals(1)){
                //人员所在地
                Object currentSiteObj = ReflectUtil.executeGet(data,"currentSite");
                persionArea = EmptyUtils.isEmpty(currentSiteObj) ? null : currentSiteObj.toString();
            }else{
                //籍贯所在地
                Object nativePlaceSiteObj = ReflectUtil.executeGet(data,"nativePlaceSite");
                persionArea = EmptyUtils.isEmpty(nativePlaceSiteObj) ? null : nativePlaceSiteObj.toString();
            }
        }else {
            DisIssuedInstance pDisIssuedInstance = disIssuedInstanceService.getOne(Wrappers.<DisIssuedInstance>query().lambda().eq(DisIssuedInstance::getOperationId,disDataOperation.getPId()));
            persionArea = pDisIssuedInstance.getPersionArea();
        }
        if(EmptyUtils.isEmpty(persionArea)){
            log.warn("缺少人员地址，无法进行自动下发！");
            return ;
        }
        disIssuedInstance.setPersionArea(persionArea);
        disIssuedInstance.setAcceptDeadline(disAutoConfig.getAcceptDeadline());
        disIssuedInstance.setContinueIssued(disAutoConfig.getContinueIssued());
        disIssuedInstance.setIsAudit(disAutoConfig.getIsAudit());
        if(disIssuedInstance.getIsAudit()){
            disIssuedInstance.setAuditUserId(disAutoConfig.getAuditUserId());
        }
        disIssuedInstance.setCommitType(disAutoConfig.getCommitType());
        if(disIssuedInstance.getCommitType().equals(2)){
            disIssuedInstance.setCommitDelayedMinute(disAutoConfig.getCommitDelayedMinute());
        }
        disIssuedInstance.setIsFinish(false);

        //获取地址及地址类型获取地址编号
        String areaCode = sysAreaService.findAreaCode(persionArea,disAutoConfig.getDeptLevel());
        if(EmptyUtils.isEmpty(areaCode)){
            log.warn("地址中未获取到对应的地区,无法进行自动下发！");
            return ;
        }
        SysDept sysDept = sysDeptService.getOne(Wrappers.<SysDept>query().lambda().eq(SysDept::getAreaCode, areaCode).eq(SysDept::getType,disAutoConfig.getDeptType()));
        if(EmptyUtils.isEmpty(sysDept)){
            log.warn("未找到目标部门,无法进行自动下发！");
            return ;
        }
        if(sysDept.getId().equals(disDataOperation.getDeptId())){
            log.warn("接收机构与本机构相同,无法进行自动下发！");
            return ;
        }

        disIssuedInstance.setDeptId(sysDept.getId());
        disIssuedInstanceService.save(disIssuedInstance);
        disDataOperation.setTargetDeptId(disIssuedInstance.getDeptId());
        if(immediatelyIssued){
            //自动下发
            disDataOperation.setState(3);
            disDataOperationService.updateById(disDataOperation);
            this.createOperation(disDataOperation.getType(),
                    disDataOperation.getDataId(),
                    disDataOperation.getId(),
                    disIssuedInstance.getDeptId(),
                    LocalDateTime.now().plusMinutes(disAutoConfig.getAcceptDeadline()),
                    disIssuedInstance.getContinueIssued());

            disIssuedInstance.setIsIssue(true);
        }else{
            //等待定时器下发
            disDataOperation.setState(2);
            disDataOperationService.updateById(disDataOperation);
            disIssuedInstance.setIsIssue(false);
        }

        if(EmptyUtils.isNotEmpty(disDataOperation.getPId())){
            //修改上级操作
            DisDataOperation pDisDataOperation = disDataOperationService.getById(disDataOperation.getPId());
            pDisDataOperation.setState(5);
            disDataOperationService.updateById(pDisDataOperation);
        }

        disIssuedInstanceService.updateById(disIssuedInstance);


    }


    /**
     * @describe: 根据id和类获取 基础数据
     * @author: hcl
     * @date: 2019/6/24 16:34
     * @param: [id (数据id),
     *           type(1:人员发现,2:预警表)]
     * @return java.lang.Object
     */
    private Object getData(Long id,Integer type){
        Object obj = null;
        if(EmptyUtils.isNotEmptys(id,type)){
            if(type.equals(1)){
                obj = disPersonnelDiscoverDataService.getById(id);
            }else if(type.equals(2)){
                obj = disWarningDataService.getById(id);
            }else{
                log.error("没有为{}的类型!",type);
            }
        }
        return obj;
    }


    @Override
    @Transactional
    public void manualCreateIssuedInstance(Long disDataOperationId,ManualDisAutoConfigReqVo manualDisAutoConfigReqVo) {

        if(manualDisAutoConfigReqVo.getIsAudit() == null){
            manualDisAutoConfigReqVo.setIsAudit(false);
        }else if(manualDisAutoConfigReqVo.getIsAudit() && EmptyUtils.isEmpty(manualDisAutoConfigReqVo.getAuditUserId())){
            throw new RestBusinessException("审核用户不能为空！",RestCommBusinessStatus.BUSINESS_ATTRIBUTE_ILLEGAL_ERROR);
        }
        if(manualDisAutoConfigReqVo.getCommitType() == null){
            manualDisAutoConfigReqVo.setCommitType(2);
            manualDisAutoConfigReqVo.setCommitDelayedMinute(0);
        }


        DisDataOperation disDataOperation = disDataOperationService.getById(disDataOperationId);
        //判断是否能进行手动下发
        if(!disDataOperation.getContinueIssued() || (!disDataOperation.getState().equals(2) && !disDataOperation.getState().equals(4))){
            throw new RestBusinessException("当前数据不支持手动调度,请刷新后再试！",RestCommBusinessStatus.BUSINESS_DOES_NOT_SUPPORT_ERROR);
        }

        if(manualDisAutoConfigReqVo.getDeptId().equals(disDataOperation.getDeptId())){
            throw new RestBusinessException("接收机构与本机构相同,无法进行自动下发！",RestCommBusinessStatus.BUSINESS_DOES_NOT_SUPPORT_ERROR);
        }

        //删除下发实例，并且将下发实例的下发目标部门的操作数据给删除掉
        this.deleteIssue(disDataOperation.getId());

        //再次下发
        DisIssuedInstance disIssuedInstance = new DisIssuedInstance();
        disIssuedInstance.setOperationId(disDataOperation.getId());
        disIssuedInstance.setIsIssue(false);

        disIssuedInstance.setIssueDate(LocalDateTime.now());//设置下发时间(当前时间+延后下发分钟数)

        disIssuedInstance.setAcceptDeadline(manualDisAutoConfigReqVo.getAcceptDeadline());
        disIssuedInstance.setContinueIssued(manualDisAutoConfigReqVo.getContinueIssued());
        disIssuedInstance.setIsAudit(manualDisAutoConfigReqVo.getIsAudit());
        if(disIssuedInstance.getIsAudit()){
            disIssuedInstance.setAuditUserId(manualDisAutoConfigReqVo.getAuditUserId());
        }
        disIssuedInstance.setCommitType(manualDisAutoConfigReqVo.getCommitType());
        if(disIssuedInstance.getCommitType().equals(2)){
            disIssuedInstance.setCommitDelayedMinute(manualDisAutoConfigReqVo.getCommitDelayedMinute());
        }
        disIssuedInstance.setIsFinish(false);

        disIssuedInstance.setDeptId(manualDisAutoConfigReqVo.getDeptId());
        disIssuedInstanceService.save(disIssuedInstance);
        disDataOperation.setTargetDeptId(disIssuedInstance.getDeptId());
        //立即下发
        disDataOperation.setState(3);
        disDataOperationService.updateById(disDataOperation);
        this.createOperation(disDataOperation.getType(),
                disDataOperation.getDataId(),
                disDataOperation.getId(),
                disIssuedInstance.getDeptId(),
                LocalDateTime.now().plusMinutes(manualDisAutoConfigReqVo.getAcceptDeadline()),
                disIssuedInstance.getContinueIssued()
                );

        if(EmptyUtils.isNotEmpty(disDataOperation.getPId())){
            //修改上级操作
            DisDataOperation pDisDataOperation = disDataOperationService.getById(disDataOperation.getPId());
            pDisDataOperation.setState(5);
            disDataOperationService.updateById(pDisDataOperation);
        }
        disIssuedInstance.setIsIssue(true);
        disIssuedInstanceService.updateById(disIssuedInstance);

    }

    /**
     * @describe: 删除以前的下发操作
     * @author: hcl  
     * @date: 2019/6/26 16:16
     * @param: [disDataOperationId]  
     * @return void  
     */
    private void deleteIssue(Long disDataOperationId){
        disDataOperationService.remove(Wrappers.<DisDataOperation>query().lambda().eq(DisDataOperation::getPId, disDataOperationId));
        disIssuedInstanceService.remove(Wrappers.<DisIssuedInstance>query().lambda().eq(DisIssuedInstance::getOperationId, disDataOperationId));
    }


    @Override
    @Transactional
    public void manualAccept(Long disDataOperationId) {
        DisDataOperation disDataOperation = disDataOperationService.getById(disDataOperationId);
        //判断是否能进行手动接收
        if(!disDataOperation.getState().equals(1)){
            throw new RestBusinessException("当前数据不支持手动接收,请刷新后再试！", RestCommBusinessStatus.BUSINESS_DOES_NOT_SUPPORT_ERROR);
        }
        disDataOperation.setState(2);
        if(EmptyUtils.isNotEmpty(disDataOperation.getPId())){
            //修改上级操作
            DisDataOperation pDisDataOperation = disDataOperationService.getById(disDataOperation.getPId());
            pDisDataOperation.setState(5);
            disDataOperationService.updateById(pDisDataOperation);
        }
        disDataOperationService.updateById(disDataOperation);
    }

    @Override
    @Transactional
    public void userManualAccept(Long disDataOperationId) {
        DisDataOperation superiorDisDataOperation = disDataOperationService.getById(disDataOperationId);

        if(EmptyUtils.isNotEmpty(superiorDisDataOperation.getTargetDeptId())){
            throw new RestBusinessException("数据已下发，不能再进行接收！", RestCommBusinessStatus.BUSINESS_DOES_NOT_SUPPORT_ERROR);
        }

        if(superiorDisDataOperation.getType().equals(1)){
            if(!superiorDisDataOperation.getState().equals(2) && !superiorDisDataOperation.getState().equals(5)){
                throw new RestBusinessException("当前数据不支持手动接收,请刷新后再试！", RestCommBusinessStatus.BUSINESS_DOES_NOT_SUPPORT_ERROR);
            }
        }else if(superiorDisDataOperation.getType().equals(2)){
            if(!superiorDisDataOperation.getState().equals(2)){
                throw new RestBusinessException("当前数据不支持手动接收,请刷新后再试！", RestCommBusinessStatus.BUSINESS_DOES_NOT_SUPPORT_ERROR);
            }
        }

        DisDataOperation disDataOperation = new DisDataOperation();
        disDataOperation.setType(superiorDisDataOperation.getType());
        disDataOperation.setDataId(superiorDisDataOperation.getDataId());
        disDataOperation.setState(5);
        disDataOperation.setPId(superiorDisDataOperation.getId());
        disDataOperation.setDeptId(superiorDisDataOperation.getDeptId());
        disDataOperation.setAcceptDeadline(null);
        disDataOperation.setContinueIssued(false);
        disDataOperation.setUserId(SecurityUtils.getUser().getId());
        disDataOperationService.save(disDataOperation);
        superiorDisDataOperation.setState(5);
        disDataOperationService.updateById(superiorDisDataOperation);
    }

    @Override
    @Transactional
    public void userReceiveAudit(Long auditId) {
        DisAudit disAudit = disAuditService.getById(auditId);
        if(EmptyUtils.isEmpty(disAudit) || !disAudit.getUserId().equals(SecurityUtils.getUser().getId())){
            throw new RestBusinessException("您不能审核此数据，请刷新后再试！",RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR);
        }
        disAuditService.remove(Wrappers.<DisAudit>query().lambda().eq(DisAudit::getType,disAudit.getType()).eq(DisAudit::getDataId,disAudit.getDataId()).ne(DisAudit::getId,disAudit.getId()));
        disAudit.setAuditState(2);
        disAuditService.updateById(disAudit);
    }

    @Override
    @Transactional
    public void userSubmitResult(Long userId,DisOperationResultReqVo disOperationResultReqVo) {

        DisDataOperation disDataOperation = disDataOperationService.getById(disOperationResultReqVo.getOperationId());
        if(EmptyUtils.isEmpty(disDataOperation)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"操作记录");
        }
        DisIssuedInstance disIssuedInstance = disIssuedInstanceService.getOne(Wrappers.<DisIssuedInstance>query().lambda().eq(DisIssuedInstance::getOperationId,disDataOperation.getId()));

        boolean immediatelySubmitted = true; //立即提交结果
        LocalDateTime resultCommit = null;
        if(disIssuedInstance.getCommitType().equals(1) ){
            if(EmptyUtils.isEmpty(disOperationResultReqVo.getResultCommit())){
                throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"反馈时间");
            }
            resultCommit = disOperationResultReqVo.getResultCommit();
            if(LocalDateTime.now().isAfter(resultCommit)){
                immediatelySubmitted = false;
            }
        }else{
            if(new Integer(0).equals(disIssuedInstance.getCommitDelayedMinute())){
                //马上提交
                resultCommit = LocalDateTime.now();
            }else{
                resultCommit = LocalDateTime.now().plusMinutes(disIssuedInstance.getCommitDelayedMinute());
                immediatelySubmitted = false;
            }
        }

        DisOperationResult disOperationResult = new DisOperationResult();
        disOperationResult.setResultContent(disOperationResultReqVo.getResultContent());
        disOperationResult.setRemark(disOperationResultReqVo.getRemark());
        disOperationResult.setResultCommit(resultCommit);
        disOperationResult.setIsCommit(false);
        disOperationResult.setOperationId(disOperationResultReqVo.getOperationId());
        disOperationResult.setCommitUserId(userId);
        disOperationResultService.save(disOperationResult);
        if(immediatelySubmitted){
            DisOperationResultReqVo eisOperationResultReqVo = new DisOperationResultReqVo();
            eisOperationResultReqVo.setOperationId(disDataOperation.getPId());
            this.userSubmitResult(userId,eisOperationResultReqVo);
        }
    }





    /**
     * @describe: 任务下发定时器(每隔2分钟执行一次)
     * @author: hcl
     * @date: 2019/6/25 16:27
     * @param: []
     * @return void
     */
    @Scheduled(cron = "0 0/2 * * * ? ")
    @Transactional
    public void taskIssue() {

        List<DisIssuedInstance> disIssuedInstanceList =  disIssuedInstanceService.list(Wrappers.<DisIssuedInstance>query().lambda()
                .eq(DisIssuedInstance::getIsIssue, false)
                .gt(DisIssuedInstance::getIssueDate,LocalDateTime.now())
        );

        if(EmptyUtils.isNotEmpty(disIssuedInstanceList)){
            List<Long> operationIds = new LinkedList<>();
            for (DisIssuedInstance disIssuedInstance : disIssuedInstanceList) {
                operationIds.add(disIssuedInstance.getOperationId());
            }

            List<DisDataOperation> disDataOperations = disDataOperationService.list(Wrappers.<DisDataOperation>query().lambda()
                    .in(DisDataOperation::getId, operationIds)
            );
            Map<Long,DisDataOperation> map = new HashMap<>();
            for (DisDataOperation disDataOperation : disDataOperations) {
                if(disDataOperation.getState().equals(2)){
                    map.put(disDataOperation.getId(),disDataOperation);
                }
            }
            List<DisIssuedInstance> updateDisIssuedInstances = new LinkedList<>();
            for (DisIssuedInstance disIssuedInstance : disIssuedInstanceList) {
                DisDataOperation disDataOperation = map.get(disIssuedInstance.getOperationId());
                if(EmptyUtils.isNotEmpty(disDataOperation)){
                    disDataOperation.setState(3);
                    disDataOperationService.updateById(disDataOperation);
                    createOperation(disDataOperation.getType(),
                            disDataOperation.getDataId(),
                            disDataOperation.getId(),
                            disIssuedInstance.getDeptId(),
                            LocalDateTime.now().plusMinutes(disIssuedInstance.getAcceptDeadline()),
                            disIssuedInstance.getContinueIssued());

                    disIssuedInstance.setIsIssue(true);
                    updateDisIssuedInstances.add(disIssuedInstance);
                }
            }
            if(EmptyUtils.isNotEmpty(updateDisIssuedInstances)){
                disIssuedInstanceService.updateBatchById(updateDisIssuedInstances);
            }
        }
    }



    /**
     * @describe: 超时未接收，自动过期任务
     * @author: hcl
     * @date: 2019/6/25 16:27
     * @param: []
     * @return void
     */
    @Scheduled(cron = "0 0/2 * * * ? ")
    @Transactional
    public void taskPastDue() {

        List<DisDataOperation> list = disDataOperationService.list(Wrappers.<DisDataOperation>query().lambda().eq(DisDataOperation::getState,1).isNotNull(DisDataOperation::getAcceptDeadline).lt(DisDataOperation::getAcceptDeadline,LocalDateTime.now()));
        if(EmptyUtils.isNotEmpty(list)){
            List<Long> ids = new ArrayList<>();
            for (DisDataOperation disDataOperation : list) {
                ids.add(disDataOperation.getPId());
            }
            List<DisDataOperation> pList = disDataOperationService.list(Wrappers.<DisDataOperation>query().lambda().in(DisDataOperation::getId,ids));
            if(EmptyUtils.isNotEmpty(pList)){
                List<DisDataOperation> updateList = new ArrayList<>();
                for (DisDataOperation disDataOperation : pList) {
                    if(disDataOperation.getState().equals(3)){
                        disDataOperation.setState(4);
                        updateList.add(disDataOperation);
                    }
                }
                if(EmptyUtils.isNotEmpty(updateList)){
                    disDataOperationService.updateBatchById(updateList);
                }
            }
        }

    }

}
