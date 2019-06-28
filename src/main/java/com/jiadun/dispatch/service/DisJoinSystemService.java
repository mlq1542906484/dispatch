package com.jiadun.dispatch.service;

import com.jiadun.dispatch.entity.DisJoinSystem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiadun.dispatch.vo.dis.req.DisJoinSystemAddReqVo;
import com.jiadun.dispatch.vo.dis.req.DisJoinSystemUpdateReqVo;
import com.jiadun.dispatch.vo.dis.res.DisJoinSystemResVo;

import java.util.List;

/**
 * <p>
 * 接入系统表 服务类
 * </p>
 *
 * @author gen
 * @since 2019-06-21
 */
public interface DisJoinSystemService extends IService<DisJoinSystem> {

    /**
     * @describe: 新增接入系统
     * @author: hcl  
     * @date: 2019/6/21 14:58
     * @param: [disJoinSystemReqVo]  
     * @return void  
     */
    void add(DisJoinSystemAddReqVo disJoinSystemReqVo);

    /**
     * @describe: 修改接入系统
     * @author: hcl  
     * @date: 2019/6/21 15:07
     * @param: [disJoinSystemUpdateReqVo]  
     * @return void  
     */
    void update(DisJoinSystemUpdateReqVo disJoinSystemUpdateReqVo);

    /**
     * @describe: 获取有效的系统
     * @author: hcl
     * @date: 2019/6/21 16:11
     * @param: []
     * @return java.util.List<com.jiadun.dispatch.vo.dis.res.DisJoinSystemResVo>
     */
    List<DisJoinSystemResVo> findAll();

    /**
     * @describe: 删除有效的系统
     * @author: hcl
     * @date: 2019/6/21 17:07
     * @param: [id]
     * @return void
     */
    void deleteById(Long id);
}
