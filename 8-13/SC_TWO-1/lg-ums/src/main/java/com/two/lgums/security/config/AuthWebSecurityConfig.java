package com.two.lgums.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.two.core.security.authorize.AuthorizeConfigManager;
import com.two.lgums.security.authorize.ArtAuthenticationSuccessHandler;
import com.two.lgums.security.authorize.LoginSuccessHandler;

/**
 * 功能描述: ${description}
 *
 **/
@Configuration
public class AuthWebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    protected  ArtUserDetailsService myUserDetailsService;

    @Autowired
    AuthorizeConfigManager authorizeConfigManager;
    
    @Autowired
    ArtAuthenticationSuccessHandler artAuthenticationSuccessHandler;
    
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login")
//		.permitAll().defaultSuccessUrl("/", true).successHandler(loginSuccessHandler())
		.loginProcessingUrl("/loginProcess")
                .successForwardUrl("/loginSuccess")
                .failureForwardUrl("/fail")
		.and().authorizeRequests()
//		.anyRequest().authenticated()
		.antMatchers("/login","/loginProcess","/loginSuccess","/fail","/authentication/require","/").permitAll()
		.antMatchers("/me").hasAnyRole("ADMIN")
		.antMatchers("/me").access("hasRole('ADMIN')")
		.and().csrf().disable().headers().frameOptions().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
    
    /**登录成功处理器*/
    private LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
