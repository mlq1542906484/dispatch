package com.jiadun.dispatch.vo;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/1/4 9:19
 */

import com.jiadun.dispatch.validator.annotation.FieldLengthInterval;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class LoginInfo {

    @ApiModelProperty("登录账号")
    @FieldLengthInterval(minLen = 2,maxLen = 20,fieldName = "登录账号")
    private String username;

    @ApiModelProperty("密码")
    @FieldLengthInterval(minLen = 6,maxLen = 32,fieldName = "密码")
    private String password;

}
