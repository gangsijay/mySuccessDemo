package com.two.core.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 功能描述:鉴权配置提供器接口,所有需要鉴权的业务模块实现此接口.
 *
 * @Auther: 田杰
 * @Date: 2018/4/23 22:42
 */
public interface AuthorizeConfigProvider {

    /**
     * 功能描述:anyRequest返回对象,用于基础通用配置.
     * @param:
     * @auther: 田杰
     * @date: 2018/4/23  
     */
    boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}

