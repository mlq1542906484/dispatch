

package com.jiadun.dispatch.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author coders
 * @date 2018/9/27
 * mybatis-plus 配置
 */
@Slf4j
@Configuration
public class MybatisPlusConfigurer {
	/**
	 * 分页插件
	 *
	 * @return PaginationInterceptor
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

//	/**
//	 * 逻辑删除插件
//	 *
//	 * @return LogicSqlInjector
//	 */
//	@Bean
//	public ISqlInjector sqlInjector() {
//		return new LogicSqlInjector();
//	}
}
