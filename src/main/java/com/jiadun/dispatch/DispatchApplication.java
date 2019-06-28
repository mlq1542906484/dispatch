package com.jiadun.dispatch;

import com.jiadun.dispatch.annotation.EnableAnningSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/18 10:35
 */
@SpringBootApplication
@EnableResourceServer
@EnableWebSecurity
@EnableAnningSwagger2
@EnableScheduling
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class DispatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DispatchApplication.class, args);
    }


}
