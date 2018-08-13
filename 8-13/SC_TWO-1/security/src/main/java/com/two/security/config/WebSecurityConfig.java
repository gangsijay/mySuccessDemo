package com.two.security.config;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 
/**
 * Security安全配置
 *
 * @Author: 我爱大金子
 * @Description: Security安全配置
 * @Date: Create in 15:20 2017/7/5
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;    // 权限管理过滤器
    @Autowired
    private SecuritySettings securitySettings;     // 自定义安全配置类
    
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    /**注册UserDetailsService的bean*/
    @Bean
    public UserDetailsService customUserService(){
        return new CustomUserService();
    }
 
    /**登录认证*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());; //userDetailsService验证
 
    }
 
    /***设置不拦截规则*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/druid/**");
    }
 
    /**安全策略配置*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置游客可以访问的URI
        if (StringUtils.isNotBlank(securitySettings.getPermitall())) {
            http.authorizeRequests().antMatchers(securitySettings.getPermitall().split(",")).permitAll();
        }
 
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                // 配置登录URI、登录失败跳转URI与登录成功后默认跳转URI
                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll().defaultSuccessUrl("/", true).successHandler(loginSuccessHandler())
                // 注销行为任意访问
                .and().logout().permitAll()
                // 设置拒绝访问的提示URI
                .and().exceptionHandling().accessDeniedPage("/login?illegal")
        ;
 
        http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
 
    /**登录成功处理器*/
    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}