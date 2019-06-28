package com.jiadun.dispatch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.ThreadUtil;
import com.jiadun.dispatch.config.Clients;
import com.jiadun.dispatch.config.OauthClientDetailsProperties;
import com.jiadun.dispatch.entity.SysDept;
import com.jiadun.dispatch.entity.SysRole;
import com.jiadun.dispatch.entity.SysRoleUser;
import com.jiadun.dispatch.entity.SysUser;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.header.BaseContextHandler;
import com.jiadun.dispatch.mapper.SysUserMapper;
import com.jiadun.dispatch.service.*;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import com.jiadun.dispatch.utils.SecurityUtils;
import com.jiadun.dispatch.vo.LoginInfo;
import com.jiadun.dispatch.vo.MenuVO;
import com.jiadun.dispatch.vo.UserInfo;
import com.jiadun.dispatch.vo.WindCloudUser;
import com.jiadun.dispatch.vo.sys.req.AddUserVo;
import com.jiadun.dispatch.vo.sys.req.UpdateUserVo;
import com.jiadun.dispatch.vo.sys.res.SysAreaTreeVo;
import com.jiadun.dispatch.vo.sys.res.UserInfoVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-18
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private OauthClientDetailsProperties oauthClientDetailsProperties;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysAreaService sysAreaService;

//    @Autowired
//    private TokenOperationService tokenOperationService;

    /**
     * @describe: 客户端集合
     */
    @Autowired
    private Clients clients;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @describe: 加密器
     * @author: hcl
     * @date: 2018/6/29 13:43
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @describe: add 方法加锁对象
     */
    private static Object addLock = new Object();

    @Override
    public UserInfo findUserInfo(SysUser user) {

        UserInfo userInfo = new UserInfo();
//        sysUser.setPassword(null);
        userInfo.setSysUser(user);
        //设置角色列表  （ID）
        List<Long> roleIds = sysRoleService.findRolesByUserId(user.getId())
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));

        //设置权限列表（menu.permission）
        List<String> permissions = new ArrayList<>();
        roleIds.forEach(roleId -> {
            List<MenuVO> menuVOS = sysResourceService.findMenuByRoleId(roleId);
            List<String> permissionList = new ArrayList<>();
            for (MenuVO menuVo: menuVOS) {
                if(StringUtils.isNotEmpty(menuVo.getPermission())){
                    permissionList.add(menuVo.getParentId()+menuVo.getPermission());
                }
            }
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(permissions);
        return userInfo;
    }

    @Override
    public Map<String, Object> login(@RequestBody @Valid LoginInfo info) {
        //设置请求体
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username",info.getUsername());
        params.add("password",info.getPassword());
        params.add("grant_type",oauthClientDetailsProperties.getGrantType());
        // 设置请求的header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ArrayList<MediaType> accepts = new ArrayList<>();
        accepts.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(accepts);
        headers.set("Authorization", oauthClientDetailsProperties.getAuthorization());
        HttpEntity<MultiValueMap<String, String>> multiValueMapHttpEntity = new HttpEntity<>(params, headers);
        return restTemplate.postForObject(oauthClientDetailsProperties.getOauthTokenServiceUri(), multiValueMapHttpEntity, Map.class);
    }

    @Override
    @Transactional
    public void add(AddUserVo addUserVo) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(addUserVo,sysUser);
        sysUser.setPwd(passwordEncoder.encode(sysUser.getPwd()));
        String loginName = SecurityUtils.getUsername();
        LocalDateTime now = LocalDateTime.now();

        sysUser.setState(1);
        synchronized (addLock) {
            SysUser selectUser = super.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getName,addUserVo.getName()));
            if(EmptyUtils.isNotEmpty(selectUser)){
                throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_ALREADY_ERROR,"用户");
            }
            sysUser.setIUser(loginName);
            sysUser.setUUser(loginName);
            sysUser.setITime(now);
            sysUser.setUTime(now);
            super.save(sysUser);
        }
        if(EmptyUtils.isNotEmpty(addUserVo.getRoleId())) {
            SysRole sysRole = sysRoleService.getById(addUserVo.getRoleId());
            if(EmptyUtils.isEmpty(sysRole)){
                throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"角色");
            }
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(sysRole.getId());
            sysRoleUser.setUserId(sysUser.getId());
            sysRoleUser.setIUser(SecurityUtils.getUsername());
            sysRoleUser.setUUser(SecurityUtils.getUsername());
            sysRoleUser.setITime(now);
            sysRoleUser.setUTime(now);
            sysRoleUserService.save(sysRoleUser);
        }
    }

    @Override
    public void updateUser(UpdateUserVo updateUserVo) {
        SysUser oldSysUser = null;
        Integer oldState = null;
        if(EmptyUtils.isNotEmpty(updateUserVo.getId())){
            oldSysUser = super.getById(updateUserVo.getId());
            oldState = oldSysUser.getState();
        }
        if(EmptyUtils.isEmpty(oldSysUser)){
            throw new RestBusinessException("没有查询到对应的用户！", RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR);
        }

        SysDept sysDeptCount = new SysDept();
        sysDeptCount.setId(updateUserVo.getDeptId());
        if(sysDeptService.count(Wrappers.emptyWrapper()) < 1){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"部门");
        }

        if(EmptyUtils.isNotEmpty(updateUserVo.getRoleId())){
            if(sysRoleService.count(Wrappers.emptyWrapper()) < 1){
                throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"角色");
            }
        }

        boolean updateRole = true;
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(updateUserVo,sysUser);
        synchronized (addLock) {
            SysUser selectUser = super.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getName,updateUserVo.getName()));
            super.updateById(sysUser);

            List<SysRoleUser> list = sysRoleUserService.list(Wrappers.<SysRoleUser>query().lambda().eq(SysRoleUser::getUserId,oldSysUser.getId()));

            if((EmptyUtils.isEmpty(list) && EmptyUtils.isEmpty(updateUserVo.getRoleId())) || (list.size() == 1 && EmptyUtils.isNotEmpty(updateUserVo.getRoleId()) && list.get(0).getRoleId().equals(updateUserVo.getRoleId()))){
                updateRole = false;
            }else{
                //删除用户角色
                sysRoleUserService.deleteByUserId(oldSysUser.getId());
                if(EmptyUtils.isNotEmpty(updateUserVo.getRoleId())){
                    //添加用户角色
                    SysRoleUser sysRoleUser = new SysRoleUser();
                    sysRoleUser.setUserId(oldSysUser.getId());
                    sysRoleUser.setRoleId(updateUserVo.getRoleId());
                    sysRoleUser.setIUser(SecurityUtils.getUsername());
                    sysRoleUser.setUUser(SecurityUtils.getUsername());
                    sysRoleUser.setITime(LocalDateTime.now());
                    sysRoleUser.setUTime(LocalDateTime.now());
                    sysRoleUserService.save(sysRoleUser);
                }

            }

        }

        //判断如果是停用用户或者修改了用户角色就让你用户的token失效
        if(updateRole || (oldState == (byte)1)){
            //异步注销此角色的所有用户
            String token = BaseContextHandler.getToken();
            SysUser sysUserTemp = oldSysUser;
            ThreadUtil.asynTaskExecutor(new Runnable() {
                @Override
                public void run() {
                    BaseContextHandler.setToken(token);
                    //注销此角色的所有用户的toekn
                    logout(sysUserTemp.getName(),clients.getClientIds());
                    BaseContextHandler.remove();
                }
            });

        }



    }

    @Override
    public void logout(String loginName, String... clientIds) {
        if(EmptyUtils.isNotEmpty(clientIds)){
            for (String clientId : clientIds) {
                //将此用户的token作废
//                tokenOperationService.removeAndLoginUserToken(clientId,loginName);
            }
        }

    }

    @Override
    public void deleteUser(Long id) {
        SysUser sysUser = super.getById(id);
        if(EmptyUtils.isEmpty(sysUser)){
            throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"用户");
        }
        sysUser.setState(3);
        super.updateById(sysUser);
    }

    @Override
    public IPage<UserInfoVo> pageList(Page page) {
        IPage<UserInfoVo> data = super.baseMapper.pageList(page);
        List<UserInfoVo> userInfoVos = data.getRecords();
        for(UserInfoVo userInfoVo : userInfoVos){
            Long deptId = userInfoVo.getDeptId();
            if(EmptyUtils.isNotEmpty(deptId)){
                SysDept sysDept = sysDeptService.getById(deptId);
                String code= sysDept.getAreaCode();
                List<String> codes = new ArrayList<>();
                codes = sysAreaService.getAreaTree(codes,code);
                codes.sort(String::compareTo);
                userInfoVo.setCodes(codes);
                List<SysRoleUser> list = sysRoleUserService.list(Wrappers.<SysRoleUser>query().lambda().eq(SysRoleUser :: getUserId , userInfoVo.getId()));
                if(CollectionUtils.isNotEmpty(list)){
                    userInfoVo.setRoleId(list.get(0).getRoleId());
                    String roleName = sysRoleService.getById(list.get(0).getRoleId()).getName();
                    userInfoVo.setRoleName(roleName);
                }
            }
        }
        return data;
    }

    @Override
    public Integer getLoginAreaType() {
        String loginName = SecurityUtils.getUsername();
        return super.baseMapper.getLoginAreaType(loginName);
    }

    @Override
    public UserInfoVo getUserInfo() {

        WindCloudUser windCloudUser = SecurityUtils.getUser();


        return null;
    }

}
