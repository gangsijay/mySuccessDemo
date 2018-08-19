package com.two.secuoauth.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
 
/**
 * 自定义安全配置类
 *
 * @Author: 我爱大金子
 * @Description: 自定义安全配置类
 * @Date: Create in 9:45 2017/7/6
 */
@Configuration
@ConfigurationProperties(prefix = "securityconfig")
public class SecuritySettings {
    /**允许访问的URL，多个用逗号分隔*/
    private String permitall;
 
    public String getPermitall() {
        return permitall;
    }
 
    public void setPermitall(String permitall) {
        this.permitall = permitall;
    }
}