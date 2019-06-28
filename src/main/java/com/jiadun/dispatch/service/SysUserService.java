package com.jiadun.dispatch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiadun.dispatch.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.LoginInfo;
import com.jiadun.dispatch.vo.UserInfo;
import com.jiadun.dispatch.vo.sys.req.AddUserVo;
import com.jiadun.dispatch.vo.sys.req.UpdateUserVo;
import com.jiadun.dispatch.vo.sys.res.UserInfoVo;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
public interface SysUserService extends IService<SysUser> {

    UserInfo findUserInfo(SysUser user);

    /**
     * @description: 登陆
     * @author: caiping
     * @date: 2019/6/18 16:23
     * @param: [info]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Object> login(@RequestBody @Valid LoginInfo info);

    /**
     * @description: 添加用户
     * @author: caiping
     * @date: 2019/6/19 15:06
     * @param: [addUserVo]
     * @return: void
     */
    void add(AddUserVo addUserVo);

    /**
     * @description: 修改用户
     * @author: caiping
     * @date: 2019/6/20 14:22
     * @param: [updateUserVo]
     * @return: void
     */
    void updateUser(UpdateUserVo updateUserVo);


    /**
     * @description: 登出
     * @author: caiping
     * @date: 2019/6/20 14:49
     * @param: [loginName, clientIds]
     * @return: void
     */
    void logout(String loginName,String... clientIds);

    /**
     * @description: 删除用户
     * @author: caiping
     * @date: 2019/6/20 15:00
     * @param: [id]
     * @return: void
     */
    void deleteUser(Long id);

    /**
     * @description: 分页查询用户列表
     * @author: caiping
     * @date: 2019/6/20 15:15
     * @param: [page]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.jiadun.dispatch.vo.sys.res.UserInfoVo>
     */
    IPage<UserInfoVo> pageList(Page page);

    /**
     * 获取当前登陆用户的地区TYPE
     * @return
     */
    Integer getLoginAreaType();

    /**
     * @description: 获取当前用户信息
     * @author: caiping
     * @date: 2019/6/27 17:26
     * @param: []
     * @return: com.jiadun.dispatch.vo.sys.res.UserInfoVo
     */
    UserInfoVo getUserInfo();
}
