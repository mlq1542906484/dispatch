package com.jiadun.dispatch.controller;


import com.fit.utils.se.TreeUtil;
import com.jiadun.dispatch.service.SysAreaService;
import com.jiadun.dispatch.vo.sys.res.SysAreaTreeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-20
 */
@RestController
@RequestMapping("/sysArea")
public class SysAreaController {

    @Autowired
    private SysAreaService sysAreaService;

    @ApiOperation("查询所有地区")
    @GetMapping("/getAreas")
    List<SysAreaTreeVo> getAreaTree(){
        List<SysAreaTreeVo> list = sysAreaService.getAreaTree();
        return TreeUtil.assemblyTree(list,"code","pCode","child","0");
    }

}
