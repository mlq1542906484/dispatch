package com.jiadun.dispatch.annotation;

import com.jiadun.dispatch.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author coders
 * @date 2018/7/21
 * 开启 windcloud swagger
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableAnningSwagger2 {
}
