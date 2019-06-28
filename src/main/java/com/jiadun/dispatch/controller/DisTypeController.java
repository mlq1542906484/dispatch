package com.jiadun.dispatch.controller;


import com.jiadun.dispatch.service.DisTypeService;
import com.jiadun.dispatch.vo.dis.res.DisTypeResVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 数据类型表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@RestController
@RequestMapping("/disType")
@Api(description = "类型管理")
public class DisTypeController {

    @Autowired
    private DisTypeService disTypeService;

    @ApiOperation("获取类型")
    @GetMapping("/getDisType")
    public List<DisTypeResVo> getDisType(){
        return disTypeService.findAll();
    }



}
