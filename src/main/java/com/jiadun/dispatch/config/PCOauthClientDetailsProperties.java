package com.jiadun.dispatch.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @describe: pc oauth客户端信息配置文件
 * @author: hcl  
 * @date: 2018/7/16 23:33
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="oauth.client.details.pc")
public class PCOauthClientDetailsProperties extends OauthClientDetailsProperties{

}
