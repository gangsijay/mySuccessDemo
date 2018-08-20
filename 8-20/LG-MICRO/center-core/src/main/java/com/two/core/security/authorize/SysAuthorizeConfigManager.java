package com.two.core.security.authorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.two.core.base.common.enums.AuthorizationCode;
import com.two.core.exception.AuthorizeRuntimeException;

/**
 * 功能描述:权限收集器实现
 *
 * @Auther: 田杰
 * @Date: 2018/4/23 23:09
 */
@Component
public class SysAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        boolean existConfig = false;
        String existConfigName = null;
        //加入鉴权配置
        for (AuthorizeConfigProvider configProvider : authorizeConfigProviders) {
            //获取当前的AnyRequestConfig信息是否已经配置，若配置抛出异常
            boolean currentConfig = configProvider.config(config);
            if (existConfig && currentConfig) {
                throw AuthorizeRuntimeException.wrap(AuthorizationCode.AUTH_ANYREQUEST_CONFIG_EXIST.code,
                        existConfigName+AuthorizationCode.AUTH_ANYREQUEST_CONFIG_EXIST.desc);
            }else if(currentConfig){
                existConfig=true;
                existConfigName=configProvider.getClass().getSimpleName();
            }
        }
        //下面所有都要验证权限
        if(!existConfig){
            config.anyRequest().authenticated();
        }

    }
}
