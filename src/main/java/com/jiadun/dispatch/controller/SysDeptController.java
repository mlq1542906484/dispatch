package com.jiadun.dispatch.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.SysDept;
import com.jiadun.dispatch.service.SysDeptService;
import com.jiadun.dispatch.vo.sys.req.AddDeptVo;
import com.jiadun.dispatch.vo.sys.req.UpdateDeptVo;
import com.jiadun.dispatch.vo.sys.res.SysDeptVo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-19
 */
@RestController
@RequestMapping("/sysDept")
@ApiOperation("部门管理")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation("添加部门")
    @PostMapping("/addDept")
    public void addDept(@RequestBody @Valid AddDeptVo addDeptVo){
        sysDeptService.addDept(addDeptVo);
    }

    @ApiOperation("修改部门")
    @PostMapping("/updateDept")
    public void updateDept(@RequestBody @Valid UpdateDeptVo updateDeptVo){
        sysDeptService.updateDept(updateDeptVo);
    }

    @ApiOperation("查询部门列表")
    @GetMapping("/listPage")
    public IPage<SysDeptVo>  listPage(@RequestParam("code")String code, Page page){
        return sysDeptService.listPage(code,page);
    }

    @ApiModelProperty("根据地区code查询部门")
    @GetMapping("/getDeptByAreaCode")
    public List<SysDept> getDeptByAreaCode(@RequestParam("code")String code){
        return sysDeptService.getDeptByAreaCode(code);
    }

    @ApiModelProperty("删除部门")
    @GetMapping("/delDept")
    public void delDept(@RequestParam("id")Long id){
        sysDeptService.delDept(id);
    }


}
