package com.jiadun.dispatch.vo.disPersonnel;

import com.jiadun.dispatch.vo.dis.req.ManualDisAutoConfigReqVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * @ClassName ManualCreateVo
 * @Description //TODO
 * @Author zjl
 * @Date 14:13   2019/6/25
 * @Version 1.0
 **/
@Getter
@Setter
public class ManualCreateVo {

    @ApiModelProperty("基础细信息")
    @Valid
   private ManualDisAutoConfigReqVo vo;
    @ApiModelProperty("ID")
   private Long disDataOperationId;

}
