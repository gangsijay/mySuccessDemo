package com.two.secuoauth.config;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
 
import java.io.IOException;
 
/**
 * 权限管理过滤器
 *
 * @Author: 我爱大金子
 * @Description: 权限管理过滤器
 * @Date: Create in 17:16 2017/7/5
 */
@Service
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    Logger log = LoggerFactory.getLogger(CustomFilterSecurityInterceptor.class);
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;  // 权限配置资源管理器
 
    /**权限管理决断器*/
    @Autowired
    public void setMyAccessDecisionManager(CustomAccessDecisionManager customAccessDecisionManager) {
        super.setAccessDecisionManager(customAccessDecisionManager);
    }
 
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        log.info("【权限管理过滤器】请求URL：" + fi.getRequestUrl());
        invoke(fi);
    }
 
 
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用CustomFilterInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用CustomAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch(Exception e) {
            log.error("【权限管理过滤器】【异常】" + e.getMessage(), e);
        } finally {
            super.afterInvocation(token, null);
        }
    }
 
    @Override
    public void destroy() {
 
    }
 
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }
 
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.customFilterInvocationSecurityMetadataSource;
    }
}