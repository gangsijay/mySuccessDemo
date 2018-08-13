package com.two.lgums.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import com.two.core.security.authorize.AuthorizeConfigProvider;

/**
 * 功能描述: 项目基础权限控制提供器
 *
 * @Auther: 田杰
 * @Date: 2018/4/24 23:33
 */
public class RdAuthorizeConfigProvider  implements AuthorizeConfigProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
            config.anyRequest().access("@rdPermissionService.hasPermission(request,authentication)");
            return true;
    }
}
