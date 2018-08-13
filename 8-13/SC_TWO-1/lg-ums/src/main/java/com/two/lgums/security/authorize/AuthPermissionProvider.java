package com.two.lgums.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.two.core.security.authorize.AuthorizeConfigProvider;

/**
 * 功能描述:鉴权配置提供器接口,所有需要鉴权的业务模块实现此接口.
 *
 * @Auther: 田杰
 * @Date: 2018/4/24 00:17
 */
@Component
public class AuthPermissionProvider implements AuthorizeConfigProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/**").hasRole("ADMIN");
        return false;
    }
}
