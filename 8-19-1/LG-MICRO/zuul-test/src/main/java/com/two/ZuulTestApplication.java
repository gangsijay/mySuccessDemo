package com.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ZuulTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulTestApplication.class, args);
	}
	
	@GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @Component
    @EnableOAuth2Sso // 实现基于OAuth2的单点登录，建议跟踪进代码阅读以下该注解的注释，很有用
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
        	http
        	.authorizeRequests().anyRequest().authenticated()
        	.and()
        	.csrf().disable();
//            http.
//                    antMatcher("/**")
//                    // 所有请求都得经过认证和授权
//                    .authorizeRequests().anyRequest().authenticated()
//                    .and().authorizeRequests().antMatchers("/","/anon").permitAll()
//                    .and()
//                    // 这里之所以要禁用csrf，是为了方便。
//                    // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
//                    // 那样我还得写一个界面，发送post请求
//                    .csrf().disable()
//                    .formLogin().loginPage("/login").failureUrl("/login?error").successForwardUrl("/index1").permitAll()
//                    .and()
//                    // 退出的URL是/logout
//                    .logout().logoutUrl("/logout").permitAll()
//                    // 退出成功后，跳转到/路径。
//                    .logoutSuccessUrl("/index");
        }
    }
}
