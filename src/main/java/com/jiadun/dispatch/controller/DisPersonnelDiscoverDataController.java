package com.jiadun.dispatch.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.service.DisAutoConfigService;
import com.jiadun.dispatch.service.DisPersonnelDiscoverDataService;
import com.jiadun.dispatch.vo.disPersonnel.DisPersonnelVo;
import com.jiadun.dispatch.vo.disPersonnel.GetPerSonnelVo;
import com.jiadun.dispatch.vo.disPersonnel.ManualCreateVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 人员发现表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Slf4j
@RestController
@RequestMapping("/disPersonnelDiscoverData")
public class DisPersonnelDiscoverDataController {

    @Autowired
    private DisPersonnelDiscoverDataService service;

    @Autowired
    private DisAutoConfigService autoConfigService;



    @ApiOperation("分页获取系统人员发现")
    @RequestMapping(value = {"/getSystemPersonnelDiscovers"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public IPage<DisPersonnelVo> getSystemPersonnelDiscovers(@RequestParam(value = "pageNum") Integer pageNum,
                                                             @RequestParam(value = "pageSize") Integer pageSize,
                                                             @RequestParam(value = "deptId", required = false) String deptId,
                                                             @RequestParam(value = "newssource", required = false) String newssource,
                                                             @RequestParam(value = "type", required = false) String type,
                                                             @RequestParam(value = "state", required = false) String state) {

        Page<DisPersonnelVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        IPage<DisPersonnelVo> a = service.selectSystemPersonnelDiscovers(page, deptId, newssource, type, state);

        return a;
    }

    @ApiOperation("分页获取部门人员发现")
    @RequestMapping(value = {"/getDeptPersonnelDiscovers"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public IPage<DisPersonnelVo> getDeptPersonnelDiscovers(@RequestParam(value = "pageNum") Integer pageNum,
                                                           @RequestParam(value = "pageSize") Integer pageSize,
                                                           @RequestParam(value = "deptId", required = false) String deptId,
                                                           @RequestParam(value = "newssource", required = false) String newssource,
                                                           @RequestParam(value = "type", required = false) String type,
                                                           @RequestParam(value = "state", required = false) String state) {

        Page<DisPersonnelVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        IPage<DisPersonnelVo> a = service.selectDeptPersonnelDiscovers(page, deptId, newssource, type, state);

        return a;
    }

    @ApiOperation("分页获取系统人员发现")
    @RequestMapping(value = {"/getStaffPersonnelDiscovers"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public IPage<DisPersonnelVo> getStaffPersonnelDiscovers(@RequestParam(value = "pageNum") Integer pageNum,
                                                            @RequestParam(value = "pageSize") Integer pageSize,
                                                            @RequestParam(value = "deptId", required = false) Long deptId,
                                                            @RequestParam(value = "newssource", required = false) String newssource,
                                                            @RequestParam(value = "type", required = false) String type,
                                                            @RequestParam(value = "state", required = false) String state) {

        Page<DisPersonnelVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        IPage<DisPersonnelVo> a = service.selectStaffPersonnelDiscovers(page, deptId, newssource, type, state);

        return a;
    }


    @ApiOperation("根据ID获取详情")
    @RequestMapping(value = {"/getPersonnelById"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public GetPerSonnelVo getPersonnelById(@RequestParam(value = "id") String id) {
        try {
            return service.getPersonnelById(id);
        } catch (Exception e) {
            log.error("根据ID获取详情失败", e);
            return null;
        }
    }

    @ApiOperation("手动调度")
    @RequestMapping(value = {"/manualCreateIssued"}, method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void ManualCreateIssued(@RequestBody @Valid ManualCreateVo vo) {
        autoConfigService.manualCreateIssuedInstance(vo.getDisDataOperationId(), vo.getVo());
    }

    @ApiOperation("用户接收")
    @RequestMapping(value = {"/userManualAccept"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void userManualAccept(@RequestParam(value = "disDataOperationId") Long disDataOperationId){
            autoConfigService.userManualAccept(disDataOperationId);
    }


    @ApiOperation("部门接收")
    @RequestMapping(value = {"/manualAccept"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void manualAccept(@RequestParam(value = "disDataOperationId") Long disDataOperationId){
        autoConfigService.manualAccept(disDataOperationId);
    }


}
