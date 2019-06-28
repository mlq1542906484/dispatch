package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.MenuVO;
import com.jiadun.dispatch.vo.sys.req.AddMenuVo;
import com.jiadun.dispatch.vo.sys.req.UpdateMenuVo;
import com.jiadun.dispatch.vo.sys.res.SysResourceTreeVo;

import java.util.List;

/**
 * <p>
 * 访问资源表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
public interface SysResourceService extends IService<SysResource> {

    List<MenuVO> findMenuByRoleId(Long roleId);

    /**
     * @description: 加载所有的菜单
     * @author: caiping
     * @date: 2019/6/21 9:08
     * @param: []
     * @return: java.util.List<com.jiadun.dispatch.vo.sys.res.SysResourceTreeVo>
     */
    List<SysResourceTreeVo> loadAllMenu();

    /**
     * @description: 添加菜单
     * @author: caiping
     * @date: 2019/6/21 9:29
     * @param: [addMenuVo]
     * @return: void
     */
    void addMenu(AddMenuVo addMenuVo);

    /**
     * @description: 修改菜单
     * @author: caiping
     * @date: 2019/6/21 9:45
     * @param: [updateMenuVo]
     * @return: void
     */
    void updateMenu(UpdateMenuVo updateMenuVo);

    /**
     * @description: 删除菜单
     * @author: caiping
     * @date: 2019/6/21 10:06
     * @param: [id]
     * @return: void
     */
    void delMenu(Long id);

    /**
     * @description: 加载资源
     * @author: caiping
     * @date: 2019/6/21 10:30
     * @param: []
     * @return: java.util.List<com.jiadun.dispatch.vo.sys.res.SysResourceTreeVo>
     */
    List<SysResourceTreeVo> loadResoucesTree();


    List<Long> findRoleResource(Long roleId);

    /**
     * @description: 根据角色查询资源
     * @author: caiping
     * @date: 2019/6/27 11:49
     * @param: [roleId]
     * @return: java.util.List<com.jiadun.dispatch.vo.sys.res.SysResourceTreeVo>
     */
    List<SysResourceTreeVo> findRoleResourceByRoleId();
}
