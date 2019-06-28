package com.jiadun.dispatch.vo.dis.req;


import com.jiadun.dispatch.validator.annotation.FieldLengthLess;
import com.jiadun.dispatch.validator.annotation.FieldNotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DisJoinSystemUpdateReqVo {

    @ApiModelProperty("id")
    @FieldNotEmpty(fieldName = "id")
    private Long id;

    /**
     * 系统名称
     */
    @ApiModelProperty("系统名称")
    @FieldNotEmpty(fieldName = "系统名称")
    @FieldLengthLess(maxLen = 20,fieldName = "系统名称")
    private String name;


}
