/**
 * 
 */
package com.two.lgums.security.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.two.core.security.authorize.AuthorizeConfigProvider;


/**
 * 浏览器环境默认的授权配置，对常见的静态资源，如js,css，图片等不验证身份
 * 
 * @author zhailiang
 *
 */
@Component
@Order(Integer.MIN_VALUE)
public class AuthWebAuthorizeConfigProvider implements AuthorizeConfigProvider {


	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(HttpMethod.GET, 
				"/swagger-resources/**",
				"/static/**",
				"/page/**",
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
