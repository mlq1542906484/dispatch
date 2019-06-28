package com.jiadun.dispatch.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.entity.SysUser;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.service.SysUserService;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.vo.LoginInfo;
import com.jiadun.dispatch.vo.R;
import com.jiadun.dispatch.vo.sys.req.AddUserVo;
import com.jiadun.dispatch.vo.sys.req.UpdateUserVo;
import com.jiadun.dispatch.vo.sys.res.UserInfoVo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation("登录")
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody @Valid LoginInfo info){
        Map<String,Object> map = sysUserService.login(info);
        if(EmptyUtils.isEmpty(map)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_INVALID_GRANT_ERROR);
        }
        return map;
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public void add(@RequestBody @Valid AddUserVo addUserVo){
        sysUserService.add(addUserVo);
    }

    @ApiOperation("修改用户")
    @PostMapping("/updateUser")
    public void updateUser(@RequestBody @Valid UpdateUserVo updateUserVo){
        sysUserService.updateUser(updateUserVo);
    }

    @ApiOperation("删除用户")
    @GetMapping("/delUser")
    public void delUser(@RequestParam("id")Long id){
        sysUserService.deleteUser(id);
    }

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public IPage<UserInfoVo> pageList(@RequestParam("pageNum") Long pageNum, @RequestParam("pageSize") Long pageSize ){

        Page page = new Page();
        page.setSize(pageSize);
        page.setPages(pageNum);
        return sysUserService.pageList(page);
    }

    @ApiOperation("根据机构ID查询用户列表")
    @GetMapping("/queryByDeptId")
    public IPage<SysUser> queryByDeptId(@RequestParam("deptId") Long deptId, @RequestParam("pageNum") Long pageNum, @RequestParam("pageSize") Long pageSize ){

        Page page = new Page();
        page.setSize(pageSize);
        page.setPages(pageNum);
        return sysUserService.page(page, Wrappers.<SysUser>query().lambda().eq(SysUser :: getDeptId, deptId));
    }

    @ApiOperation("获取当前用户的地区级别")
    @GetMapping("/getLoginAreaType")
    public Integer getLoginAreaType() {
        return sysUserService.getLoginAreaType();
    }

    @ApiOperation("根据用户id列表获取用户信息")
    @PostMapping("/queryUserInfoByUserIds")
    public Collection<SysUser> queryUserInfoByUserIds(@RequestBody List<Long> ids) {
        return sysUserService.listByIds(ids);
    }

    @ApiModelProperty("获取当前用户信息")
    @GetMapping("/getUserInfo")
    UserInfoVo getUserInfo(){
        return sysUserService.getUserInfo();
    }

}
