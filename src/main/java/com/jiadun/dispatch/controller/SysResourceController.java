package com.jiadun.dispatch.controller;


import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.service.SysResourceService;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.WindCloudUser;
import com.jiadun.dispatch.vo.sys.req.AddMenuVo;
import com.jiadun.dispatch.vo.sys.req.UpdateMenuVo;
import com.jiadun.dispatch.vo.sys.res.SysResourceTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 访问资源表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@RestController
@RequestMapping("/sysResource")
@Api(description = "资源管理")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @ApiOperation("加载所有的菜单")
    @GetMapping("/loadAllMenuTree")
    List<SysResourceTreeVo> loadAllMenuTree(){
        return sysResourceService.loadAllMenu();
    }

    @ApiOperation("添加资源")
    @PostMapping("/addMenu")
    public void addMenu(@RequestBody @Valid AddMenuVo addMenuVo){
        sysResourceService.addMenu(addMenuVo);
    }

    @ApiModelProperty("修改资源")
    @PostMapping("/updateMenu")
    public void updateMenu(@RequestBody @Valid UpdateMenuVo updateMenuVo){
        sysResourceService.updateMenu(updateMenuVo);
    }

    @ApiModelProperty("删除资源")
    @GetMapping("/delMenu")
    public void delMenu(@RequestParam("id")Long id){
        sysResourceService.delMenu(id);
    }

    @ApiModelProperty("加载资源树")
    @GetMapping("/loadResoucesTree")
    public List<SysResourceTreeVo> loadResoucesTree(){
        return sysResourceService.loadResoucesTree();
    }

    @ApiModelProperty("按角色查询资源")
    @GetMapping("/findRoleResource")
    public List<Long> findRoleResource(@RequestParam(value = "roleId") Long roleId){
        return sysResourceService.findRoleResource(roleId);
    }

    @ApiModelProperty("返回当前用户的资源树")
    @GetMapping("/findRoleResourceByRoleId")
    public List<SysResourceTreeVo> findRoleResourceByRoleId(){
        return sysResourceService.findRoleResourceByRoleId();
    }

}
