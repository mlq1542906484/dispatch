package com.jiadun.dispatch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gen
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("t_sys_area")
public class SysArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 省份城市名称
     */
    private String name;

    /**
     * 省份城市代码
     */
    private String code;

    /**
     * 父级code
     */
    private String pCode;

    /**
     * 1:省；2:市；3:区、县；
     */
    private Integer type;


}
