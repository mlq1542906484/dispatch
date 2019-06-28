package com.jiadun.dispatch.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fit.utils.se.ThreadUtil;
import com.jiadun.dispatch.entity.SysRole;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.header.BaseContextHandler;
import com.jiadun.dispatch.service.SysRoleService;
import com.jiadun.dispatch.service.SysUserService;
import com.jiadun.dispatch.state.RestBusinessStatusInter;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.sys.req.AddSysRoleVo;
import com.jiadun.dispatch.vo.sys.req.UpdateSysRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@RestController
@RequestMapping("/sysRole")
@Api(description = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("查询有效的角色")
    @GetMapping("/getRoles")
    List<SysRole> getRoles(){
        return sysRoleService.list(Wrappers.<SysRole>query().lambda().eq(SysRole::getState,1));
    }

    @ApiOperation("新增角色")
    @PostMapping("/add")
    public void add(@RequestBody AddSysRoleVo reqVo){
        sysRoleService.addRole(reqVo);
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public void update(@RequestBody UpdateSysRoleVo reqVo){
        sysRoleService.updateRole(reqVo);
    }


    @ApiOperation("删除角色")
    @GetMapping("/delete")
    public void delete(@RequestParam("id") Long id){
        sysRoleService.delete(id);
    }


}
