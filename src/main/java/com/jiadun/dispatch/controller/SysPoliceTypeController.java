package com.jiadun.dispatch.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.SysPoliceType;
import com.jiadun.dispatch.entity.SysRole;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.service.SysPoliceTypeService;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.sys.req.AddPolicetypeVo;
import com.jiadun.dispatch.vo.sys.req.UpdatePolicetypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 警种 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-20
 */
@RestController
@RequestMapping("/sysPoliceType")
@Api(description = "警种管理")
public class SysPoliceTypeController {

    @Autowired
    private SysPoliceTypeService sysPoliceTypeService;

    @ApiOperation("查询有效的警种")
    @GetMapping("/getPoliceTypes")
    List<SysPoliceType> getPoliceTypes(){
        return sysPoliceTypeService.list(Wrappers.<SysPoliceType>query().lambda().eq(SysPoliceType::getState,1));
    }

    @ApiOperation("分页查询有效的警种")
    @GetMapping("/getPoliceTypePages")
    IPage<SysPoliceType> getPoliceTypes(@RequestParam("pageSize") Long pageSize,@RequestParam("pageNum") Long pageNum){
        Page page = new Page();
        page.setSize(pageSize);
        page.setPages(pageNum);
        return sysPoliceTypeService.page(page,Wrappers.<SysPoliceType>query().lambda().eq(SysPoliceType::getState,1));
    }

    @ApiOperation("新增警种")
    @PostMapping("/add")
    void add(@RequestBody @Valid AddPolicetypeVo reqVo){
        SysPoliceType model = new SysPoliceType();
        BeanUtils.copyProperties(reqVo,model);
        model.setState(1);
        LocalDateTime now = LocalDateTime.now();
        String loginName = SecurityUtils.getUsername();
        model.setITime(now);
        model.setIUser(loginName);
        model.setUTime(now);
        model.setUUser(loginName);
        sysPoliceTypeService.saveOrUpdate(model);
    }

    @ApiOperation("修改警种")
    @PostMapping("/update")
    void update(@RequestBody @Valid UpdatePolicetypeVo reqVo){
        SysPoliceType model = new SysPoliceType();
        SysPoliceType sysPoliceType = sysPoliceTypeService.getById(reqVo.getId());
        if(sysPoliceType == null){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"ID");
        }
        BeanUtils.copyProperties(reqVo,model);
        model.setState(1);
        LocalDateTime now = LocalDateTime.now();
        String loginName = SecurityUtils.getUsername();
        model.setUTime(now);
        model.setUUser(loginName);
        sysPoliceTypeService.saveOrUpdate(model);
    }

    @ApiOperation("删除警种")
    @GetMapping("/delete")
    void delete(@RequestParam("id") Long id){
        SysPoliceType model = new SysPoliceType();
        model.setId(id);
        model.setState(3);
        sysPoliceTypeService.saveOrUpdate(model);
    }


}
