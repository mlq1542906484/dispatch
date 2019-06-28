package com.jiadun.dispatch.vo.disPersonnel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DisWarningVo
 * @Description //TODO
 * @Author zjl
 * @Date 15:22   2019/6/24
 * @Version 1.0
 **/
@Getter
@Setter
public class DisWarningVo {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 时间
     */
    private String time;

    /**
     * 系统编号
     */
    private String newssource;

    /**
     * 重要级别,1、2、3、4
     */
    private Integer importance;

    /**
     * 预警类型
     */
    private String yjlx;

    /**
     * 预警内容
     */
    private String details;

    /**
     * 编码:数据编码生成规则: 类型（人员发现、预警... 取前两个字首字母） 时间(精确到天如:20190527) 顺序(4位自增数)
     */
    private String code;


    /**
     *  机构名称
     */
    private String deptName;

    /**
     *  机构ID
     */
    private String deptId;

    /**
     *  状态
     */
    private String state;

    /**
     *  操作ID
     */
    private String disDataOperationId;

}
