package com.jiadun.dispatch.vo.dis.req;


import com.jiadun.dispatch.validator.annotation.FieldLengthInterval;
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
public class DisJoinSystemAddReqVo {


    /**
     * 系统名称
     */
    @ApiModelProperty("系统名称")
    @FieldNotEmpty(fieldName = "系统名称")
    @FieldLengthLess(maxLen = 20,fieldName = "系统名称")
    private String name;

    /**
     * 系统编码,在推数据时，第三方系统会将此编码放到数据中
     */
    @ApiModelProperty("系统编码")
    @FieldNotEmpty(fieldName = "系统编码")
    @FieldLengthLess(maxLen = 10,fieldName = "系统编码")
    private String code;


}
