package com.jiadun.dispatch.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.DisAudit;
import com.jiadun.dispatch.service.DisAuditService;
import com.jiadun.dispatch.vo.dis.res.DisPersonnelDiscoverDataResVo;
import com.jiadun.dispatch.vo.dis.res.DisTypeResVo;
import com.jiadun.dispatch.vo.sys.res.SysDeptVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 审批表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@RestController
@RequestMapping("/disAudit")
public class DisAuditController {

    @Autowired
    private DisAuditService disAuditService;


    @ApiOperation("获取审批数据(待审批 type:1，已审批 type:2)")
    @GetMapping("/getAudit")
    public IPage<DisPersonnelDiscoverDataResVo> getAudit(@RequestParam("type") Integer type,@RequestParam("newssource") String newssource, Page page){
        return disAuditService.getAudit(type,newssource,page);
    }

    @ApiOperation("接收审批")
    @GetMapping("/receiveAudit")
    public void receiveAudit(@RequestParam("auditId") Long auditId){
        disAuditService.receiveAudit(auditId);
    }






}
