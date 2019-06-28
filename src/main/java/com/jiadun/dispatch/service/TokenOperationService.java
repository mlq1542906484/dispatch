package com.jiadun.dispatch.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/20 14:50
 */
public interface TokenOperationService {

    /**
     * @describe: 保存用户token到redis
     * @author: hcl
     * @date: 2018/7/17 12:05
     * @param: [username, token]
     * @return void
     */
    void saveUserToken(String clientId);

    /**
     * @describe: 获取用户token
     * @author: hcl
     * @date: 2018/7/17 12:40
     * @param: [username]
     * @return java.lang.String
     */
    String getUserToken(String clientId,String username);



    /**
     * @describe: 删除用户token+
     * @author: hcl
     * @date: 2018/7/17 12:43
     * @param: [username]
     * @return void
     */
    void removeUserToekn(String clientId,String username);


    /**
     * @describe: 删除和注销用户token
     * @author: hcl
     * @date: 2018/7/17 14:36
     * @param: [clientId, username]
     * @return void
     */
    void removeAndLoginUserToken(String clientId,String... loginNames);

}
