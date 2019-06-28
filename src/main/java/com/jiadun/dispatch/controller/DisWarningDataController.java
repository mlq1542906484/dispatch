package com.jiadun.dispatch.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.service.DisWarningDataService;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.disPersonnel.DisPersonnelVo;
import com.jiadun.dispatch.vo.disPersonnel.DisWarningVo;
import com.jiadun.dispatch.vo.disPersonnel.GetPerSonnelVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 预警表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@Slf4j
@RestController
@RequestMapping("/disWarningData")
public class DisWarningDataController {

    @Autowired
    private DisWarningDataService service;


    @ApiOperation("分页获取系统预警")
    @RequestMapping(value = {"/getSystemWarnings"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public IPage<DisWarningVo> getAllWarnings(@RequestParam(value = "pageNum") Integer pageNum,
                                                          @RequestParam(value = "pageSize") Integer pageSize,
                                                        @RequestParam(value = "deptId",required = false) String deptId,
                                                        @RequestParam(value = "newssource" ,required = false) String newssource,
                                                        @RequestParam(value = "type" ,required = false) String type,
                                                        @RequestParam(value = "state" ,required = false) String state){

        Page<DisWarningVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        IPage<DisWarningVo> a = service.selectSystemWarning(page,deptId,newssource,type,state);
        return a;
    }


    @ApiOperation("分页获取部门预警")
    @RequestMapping(value = {"/getDeptWarnings"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public IPage<DisWarningVo> getDeptWarnings(@RequestParam(value = "pageNum") Integer pageNum,
                                              @RequestParam(value = "pageSize") Integer pageSize,
                                              @RequestParam(value = "newssource" ,required = false) String newssource,
                                              @RequestParam(value = "type" ,required = false) String type,
                                              @RequestParam(value = "state" ,required = false) String state){

        Page<DisWarningVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);

        IPage<DisWarningVo> a = service.selectDeptWarning(page,"",newssource,type,state);
        return a;
    }


    @ApiOperation("分页获取用户预警")
    @RequestMapping(value = {"/getStaffWarnings"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public IPage<DisWarningVo> getStaffWarnings(@RequestParam(value = "pageNum") Integer pageNum,
                                               @RequestParam(value = "pageSize") Integer pageSize,
                                               @RequestParam(value = "userId",required = false) Long userId,
                                               @RequestParam(value = "newssource" ,required = false) String newssource,
                                               @RequestParam(value = "type" ,required = false) String type,
                                               @RequestParam(value = "state" ,required = false) String state){

        Page<DisWarningVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        IPage<DisWarningVo> a = service.selectStaffWarning(page,userId,newssource,type,state);
        return a;
    }




    @ApiOperation("根据ID获取预警详情")
    @RequestMapping(value = {"/getWarningDataById"}, method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public GetPerSonnelVo getPersonnelById(@RequestParam(value = "id") String id) {
        try {
            return service.getWarningDataById(id);
        } catch (Exception e) {
            log.error("根据ID获取详情失败", e);
            return null;
        }
    }

}
