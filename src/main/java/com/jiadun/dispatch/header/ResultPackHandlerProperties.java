package com.jiadun.dispatch.header;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @describe: 结果包装器配置文件
 * @author: hcl  
 * @date: 2018/7/1 23:13
 */
@Getter
@Setter
@ConfigurationProperties(prefix="spring.mvc")
@Component
public class ResultPackHandlerProperties {
    /**
     * @describe: 忽略的地址
     */
    private List<String> resultPackIgnores;
}
