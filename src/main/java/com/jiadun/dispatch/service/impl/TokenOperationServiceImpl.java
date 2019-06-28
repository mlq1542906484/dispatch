package com.jiadun.dispatch.service.impl;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.header.BaseContextHandler;
import com.jiadun.dispatch.service.TokenOperationService;
import com.jiadun.dispatch.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/20 14:51
 */
@Service
public class TokenOperationServiceImpl implements TokenOperationService {

    @Value("${token.live-time}")
    private Integer liveTime;
    /**
     * @describe: token命名空间
     */
    private String namespace = "custom_token_store";

    /**
     * @describe: redisTokenStore
     * @author: hcl
     * @date: 2018/7/17 14:38
     */
    @Autowired
    private RedisTokenStore redisTokenStore;

    /**
     * @describe: redis操作对象
     * @author: hcl
     * @date: 2018/7/17 12:47
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveUserToken(String clientId) {
        String key = this.namespace + ":" + clientId + ":" + SecurityUtils.getUsername();
        if(liveTime <= 0){
            stringRedisTemplate.opsForValue().set(key,BaseContextHandler.getToken());
        }else{
            stringRedisTemplate.opsForValue().set(key,BaseContextHandler.getToken(),liveTime, TimeUnit.SECONDS);
        }
    }

    @Override
    public String getUserToken(String clientId, String loginName) {
        String key = this.namespace + ":" + clientId + ":" + loginName;
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void removeUserToekn(String clientId,String loginName) {
        String key = this.namespace + ":" + clientId + ":" + loginName;
        stringRedisTemplate.delete(key);
    }

    @Override
    public void removeAndLoginUserToken(String clientId, String... loginNames) {
        if(EmptyUtils.isNotEmptys(clientId,loginNames)){
            for(String loginName : loginNames){
                String key = this.namespace + ":" + clientId + ":" + loginName;
                String token = stringRedisTemplate.opsForValue().get(key);
                if(EmptyUtils.isEmpty(token)){
                    continue;
//                    throw new RestBusinessException(RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR,"token");
                }
                stringRedisTemplate.delete(key);
                redisTokenStore.removeAccessToken(token);
            }
        }
    }

}
