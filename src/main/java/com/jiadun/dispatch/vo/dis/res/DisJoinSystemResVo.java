package com.jiadun.dispatch.vo.dis.res;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DisJoinSystemResVo {

    /**
     * 主键
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;


    /**
     * 系统编码,在推数据时，第三方系统会将此编码放到数据中
     */
    @ApiModelProperty("系统编码")
    private String code;


}
