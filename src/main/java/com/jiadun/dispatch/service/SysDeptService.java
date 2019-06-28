package com.jiadun.dispatch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.sys.req.AddDeptVo;
import com.jiadun.dispatch.vo.sys.req.UpdateDeptVo;
import com.jiadun.dispatch.vo.sys.res.SysDeptVo;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-19
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * @description: 添加部门
     * @author: caiping
     * @date: 2019/6/21 14:25
     * @param: [addDeptVo]
     * @return: void
     */
    void addDept(AddDeptVo addDeptVo);

    /**
     * @description: 修改部门
     * @author: caiping
     * @date: 2019/6/21 14:49
     * @param: [updateDeptVo]
     * @return: void
     */
    void updateDept(UpdateDeptVo updateDeptVo);

    /**
     * @description: 分页查询单位
     * @author: caiping
     * @date: 2019/6/21 15:07
     * @param: [code, page]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.jiadun.dispatch.entity.SysDept>
     */
    IPage<SysDeptVo> listPage(String code, Page page);

    /**
     * @description: 根据code查询部门
     * @author: caiping
     * @date: 2019/6/21 15:56
     * @param: [code]
     * @return: java.util.List<com.jiadun.dispatch.entity.SysDept>
     */
    List<SysDept> getDeptByAreaCode(String code);

    /**
     * @description: 删除部门
     * @author: caiping
     * @date: 2019/6/21 16:13
     * @param: [id]
     * @return: void
     */
    void delDept(Long id);
}
