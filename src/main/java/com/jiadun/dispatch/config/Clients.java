package com.jiadun.dispatch.config;

import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.exception.RestBusinessException;
import com.jiadun.dispatch.state.RestCommBusinessStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @describe: 客户端集合
 * @author: hcl  
 * @date: 2019/1/14 15:30
 */
@Getter
@Setter
@Component
public class Clients {

    private Object lock1 = new Object();

    /**
     * @describe: 客户端信息配置文件
     */
    @Autowired
    private PCOauthClientDetailsProperties pcoauthClientDetailsProperties;




    /**
     * @describe: 客户端类型集合
     */
    private Map<Byte,OauthClientDetailsProperties> oauthClientDetailsPropertiesMap;


    /**
     * @describe: 客户端id集合
     */
    private String[] clientIds;


    /**
     * @describe: 根据客户端类型,获取客户端数据,客户端类型,1:pc,2:手机端
     * @author: hcl  
     * @date: 2019/1/14 15:33
     * @param: [clientType]  
     * @return com.jiadun.eagle.upms.sys.rest.config.OauthClientDetailsProperties  
     */
    public OauthClientDetailsProperties findByClientType(Byte clientType){
        Map<Byte,OauthClientDetailsProperties> oauthClientDetailsPropertiesMap = this.getOauthClientDetailsPropertiesMap();
        OauthClientDetailsProperties oauthClientDetailsProperties = oauthClientDetailsPropertiesMap.get(clientType);
        if(EmptyUtils.isEmpty(oauthClientDetailsProperties)){
            throw new RestBusinessException("客户端类型错误！",RestCommBusinessStatus.BUSINESS_OBJECT_NOT_FOUND_ERROR);
        }
        return oauthClientDetailsProperties;
    }


    /**
     * @describe: 获取客户端id集合
     * @author: hcl
     * @date: 2019/1/14 15:48
     * @param: []
     * @return java.util.List<java.lang.String>
     */
    public String[] getClientIds(){
        if(EmptyUtils.isEmpty(clientIds)){
            synchronized (lock1){
                if(EmptyUtils.isEmpty(clientIds)){
                    Map<Byte,OauthClientDetailsProperties> oauthClientDetailsPropertiesMap = this.getOauthClientDetailsPropertiesMap();
                    Set<String> clientSet = new HashSet<>();
                    for (Map.Entry<Byte, OauthClientDetailsProperties> byteOauthClientDetailsPropertiesEntry : oauthClientDetailsPropertiesMap.entrySet()) {
                        clientSet.add(byteOauthClientDetailsPropertiesEntry.getValue().getClientId());
                    }
                    clientIds = clientSet.toArray(new String[clientSet.size()]);
                }
            }
        }
        return clientIds;
    }


    /**
     * @describe: 获取OauthClientDetailsPropertiesMap
     * @author: hcl  
     * @date: 2019/1/14 15:53
     * @param: []  
     * @return com.jiadun.eagle.upms.sys.rest.config.OauthClientDetailsProperties  
     */
    private Map<Byte,OauthClientDetailsProperties> getOauthClientDetailsPropertiesMap(){
        if(EmptyUtils.isEmpty(oauthClientDetailsPropertiesMap)){
            synchronized (this){
                if(EmptyUtils.isEmpty(oauthClientDetailsPropertiesMap)){
                    oauthClientDetailsPropertiesMap = new HashMap<>();
                    oauthClientDetailsPropertiesMap.put((byte)1,pcoauthClientDetailsProperties);
                }
            }
        }
        return oauthClientDetailsPropertiesMap;
    }




}
