package com.two.secuoauth.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.two.core.base.common.security.SecurityConstants;
import com.two.core.security.authorize.AuthorizeConfigManager;
import com.two.secuoauth.config.oauth2.MyBCryptPasswordEncoder;
 
/**
 * Security安全配置
 *
 * @Author: 我爱大金子
 * @Description: Security安全配置
 * @Date: Create in 15:20 2017/7/5
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthorizeConfigManager authorizeConfigManager;
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserService();
    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new MyBCryptPasswordEncoder();
    }
    
    /***设置不拦截规则*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/druid/**");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//        .anyRequest().authenticated() //任何请求,登录后可以访问
//        // 配置登录URI、登录失败跳转URI与登录成功后默认跳转URI
//        .and().formLogin().loginPage("/login").failureUrl("/login?error").successForwardUrl("/index2").permitAll()
//        // 注销行为任意访问
//        .and().logout().permitAll()
//        // 设置拒绝访问的提示URI
//        .and().exceptionHandling().accessDeniedPage("/login?illegal");
//        authorizeConfigManager.config(http.authorizeRequests());
    	http.requestMatchers()
        .antMatchers("/login", "/oauth/authorize")
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}