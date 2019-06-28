package com.jiadun.dispatch.controller;


import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.jiadun.dispatch.service.DisJoinSystemService;
import com.jiadun.dispatch.validator.FieldNotEmptyValidator;
import com.jiadun.dispatch.validator.ValidatorUtils;
import com.jiadun.dispatch.validator.VerifyField;
import com.jiadun.dispatch.vo.dis.req.DisJoinSystemAddReqVo;
import com.jiadun.dispatch.vo.dis.req.DisJoinSystemUpdateReqVo;
import com.jiadun.dispatch.vo.dis.res.DisJoinSystemResVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 接入系统表 前端控制器
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
@RestController
@RequestMapping("/disJoinSystem")
@Api(description = "接入系统管理")
public class DisJoinSystemController {

    @Autowired
    private DisJoinSystemService disJoinSystemService;

    @ApiOperation("添加接入系统")
    @PostMapping("/addJoinSystem")
    void addJoinSystem(@RequestBody @Valid DisJoinSystemAddReqVo disJoinSystemReqVo){
        disJoinSystemService.add(disJoinSystemReqVo);
    }


    @ApiOperation("修改接入系统")
    @PostMapping("/updateJoinSystem")
    void updateJoinSystem(@RequestBody @Valid DisJoinSystemUpdateReqVo disJoinSystemUpdateReqVo){
        disJoinSystemService.update(disJoinSystemUpdateReqVo);
    }

    @ApiOperation("获取有效的接入系统")
    @GetMapping("/findJoinSystemAll")
    List<DisJoinSystemResVo> findJoinSystemAll(){
        return disJoinSystemService.findAll();
    }


    @ApiOperation("删除接入系统")
    @GetMapping("/deleteJoinSystem")
    void deleteJoinSystem(@RequestParam("id")Long id){

        VerifyField idVerify = VerifyField.getVerifyField("id","id");
        //验证
        ComplexResult result = FluentValidator.checkAll()
                .on(id,new FieldNotEmptyValidator(idVerify))
                .doValidate()
                .result(ResultCollectors.toComplex());

        //判断结果验证是否成功
        ValidatorUtils.isSuccess(result);
        //验证成功
        disJoinSystemService.deleteById(id);
    }

}
