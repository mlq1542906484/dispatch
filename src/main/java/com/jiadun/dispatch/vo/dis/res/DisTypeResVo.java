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
public class DisTypeResVo {


    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 类型名称
     */
    @ApiModelProperty("类型名称")
    private String name;

    /**
     * 1：预警,2:人员发现
     */
    @ApiModelProperty("1：预警,2:人员发现")
    private Integer type;


    /**
     * kafka topic名称,不同的系统,推送同一个类型时,使用的topic一样
     */
    @ApiModelProperty("topic")
    private String topic;



    /**
     * @describe: 字段说明
     */
    @ApiModelProperty("字段说明")
    private String fieldExplain;

    /**
     * @describe: 格式说明
     */
    @ApiModelProperty("格式说明")
    private String formatExplain;
}
