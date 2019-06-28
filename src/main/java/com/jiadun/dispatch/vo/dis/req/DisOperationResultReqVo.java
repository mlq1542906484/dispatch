package com.jiadun.dispatch.vo.dis.req;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jiadun.dispatch.validator.annotation.FieldNotEmpty;
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
public class DisOperationResultReqVo {


    @ApiModelProperty("操作id")
    @FieldNotEmpty(fieldName = "操作id")
    private Long operationId;

    /**
     * 结果内容
     */
    @ApiModelProperty("结果内容")
    @FieldNotEmpty(fieldName = "结果内容")
    private String resultContent;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;


    /**
     * 需提交时间(为空表示实时提交)
     */
    @ApiModelProperty("需提交时间(为空表示实时提交)")
    private LocalDateTime resultCommit;

}
