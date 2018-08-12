package com.two.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.two.oauthserver.service.security.JPAUserDetailsService;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JPAUserDetailsService jpaUserDetailsService;

    //密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyBCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .anyRequest().permitAll() //任何请求,登录后可以访问
        // 配置登录URI、登录失败跳转URI与登录成功后默认跳转URI
        .and().formLogin().loginPage("/login").defaultSuccessUrl("/").successForwardUrl("/index2").permitAll()
        // 注销行为任意访问
        .and().logout().permitAll()
        // 设置拒绝访问的提示URI
        .and().exceptionHandling().accessDeniedPage("/login?illegal")
        .and().csrf().disable().anonymous().disable();
    }
    
    /***设置不拦截规则*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/druid/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	LoginAuthenticationProvider authenticationProvider = new LoginAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(jpaUserDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(jpaUserDetailsService).passwordEncoder(passwordEncoder());
    }

    //不定义没有password grant_type
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    /**登录成功处理器*/
    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
