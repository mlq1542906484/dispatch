package com.jiadun.dispatch.controller;


import com.jiadun.dispatch.service.DisAutoConfigService;
import com.jiadun.dispatch.vo.dis.req.DisAutoConfigAddReqVo;
import com.jiadun.dispatch.vo.dis.req.DisAutoConfigQueryReqVo;
import com.jiadun.dispatch.vo.dis.req.DisJoinSystemAddReqVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 任务自动下发配置表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@RestController
@RequestMapping("/disAutoConfig")
public class DisAutoConfigController {

    @Autowired
    private DisAutoConfigService disAutoConfigService;

    @ApiOperation("添加自动下发配置")
    @PostMapping("/addDisAutoConfig")
    public void addDisAutoConfig(@RequestBody @Valid DisAutoConfigAddReqVo disAutoConfigAddReqVo){
        disAutoConfigService.add(disAutoConfigAddReqVo);
    }

    @ApiOperation("获取自动下发配置")
    @PostMapping("/getDisAutoConfig")
    public DisAutoConfigAddReqVo getDisAutoConfig(@RequestBody @Valid DisAutoConfigQueryReqVo disAutoConfigQueryReqVo){
        return disAutoConfigService.find(disAutoConfigQueryReqVo);
    }


}
