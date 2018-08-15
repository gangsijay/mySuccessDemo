package com.zhm.config;

import com.zhm.service.AppUserService;
import com.zhm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


/**
 * Created by zhm on 17-2-13.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AppUserService appUserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .permitAll().and()
                .authorizeRequests()
                .antMatchers("/assets/**","/login/**","/logout**","/favicon**").permitAll()
                .anyRequest().authenticated()
//                .and().headers().frameOptions().sameOrigin()
                .and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public MyAuthenticationProvider authenticationProvider()  throws Exception {
        MyAuthenticationProvider authenticationProvider = new MyAuthenticationProvider(appUserService,userInfoService);
        return authenticationProvider;
    }
}

