package com.two.core.security.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 功能描述:鉴权配置提供器实现
 *
 * @Auther: 田杰
 * @Date: 2018/4/23 22:54
 */
@Component
@Order(Integer.MIN_VALUE)
public class SysAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/*.html",
                "/swagger-resources/**",
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.otf",
                "/**/*.eot",
                "/**/*.svg",
                "/**/*.ttf",
                "/**/*.woff",
                "/**/*.woff2"
        ).permitAll();

        return false;
    }
}
