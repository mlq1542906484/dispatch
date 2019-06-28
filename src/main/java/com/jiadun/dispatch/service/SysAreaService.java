package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.SysArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.sys.res.SysAreaTreeVo;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-20
 */
public interface SysAreaService extends IService<SysArea> {

    List<SysAreaTreeVo> getAreaTree();

    /**
     * @description: 从下往上找地区树所有的id
     * @author: caiping
     * @date: 2019/6/20 17:37
     * @param: [code]
     * @return: void
     */
    List<String> getAreaTree(List<String> result, String code);

    /**
     * @describe: 根据类型从地址中获取对应地区的编码
     * @author: hcl  
     * @date: 2019/6/24 19:33  
     * @param: [persionArea (地址:XX省XX市XX区), deptLevel(级别,1:省厅,2:市级,3:区县)]
     * @return java.lang.String  
     */
    String findAreaCode(String persionArea, Integer deptLevel);
}
