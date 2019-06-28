package com.jiadun.dispatch.config;


import com.fit.utils.se.EmptyUtils;
import com.fit.utils.se.EncryptUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @describe: oauth客户端信息配置文件
 * @author: hcl  
 * @date: 2018/7/16 23:33
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="oauth.client.details")
public class OauthClientDetailsProperties {


    /**
     * @describe: 客户端id
     */
    private String clientId;


    /**
     * @describe: 客户端密码
     */
    private String clientSecret;


    /**
     * @describe: 授权密钥
     */
    private String authorization;


    /**
     * @describe: 授权方式
     */
    private String grantType;

    /**
     * @describe: 授权服务器获取token地址
     */
    private String oauthTokenServiceUri;

    /**
     * @describe: 获取授权密钥
     * @author: hcl  
     * @date: 2018/7/16 23:36
     * @param: []  
     * @return java.lang.String  
     */
    public String getAuthorization(){
        if(EmptyUtils.isEmpty(authorization)){
            synchronized (OauthClientDetailsProperties.class){
                if(EmptyUtils.isEmpty(authorization)){
                    authorization = "Basic "+ EncryptUtil.base64Encoder(clientId + ":" + clientSecret);
                }
            }
        }
        return authorization;
    }

}
