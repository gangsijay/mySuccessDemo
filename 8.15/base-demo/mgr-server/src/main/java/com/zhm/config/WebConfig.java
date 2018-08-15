package com.zhm.config;

import com.zhm.filters.CommonFilter;
import com.zhm.filters.XssFilter;
import freemarker.cache.ClassTemplateLoader;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by zhm on 16-7-19.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new XssFilter());
    }

    /**
     * 加载自定义过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(someFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("commonFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "commonFilter")
    public Filter someFilter() {
        return new CommonFilter();
    }

    /**
     * 以下为springboot中freemarker无法识别jsp标签所加的配置
     *
     */

    @Bean
    @Autowired
    public freemarker.template.Configuration freeMarkerConfig(ServletContext servletContext) throws IOException,
            TemplateException {
        FreeMarkerConfigurer freemarkerConfig = configFreeMarkerConfigurer(servletContext);
        return freemarkerConfig.getConfiguration();
    }

    @Bean
    @Autowired
    public TaglibFactory taglibFactory(ServletContext servletContext) throws IOException, TemplateException {
        FreeMarkerConfigurer freemarkerConfig = configFreeMarkerConfigurer(servletContext);
        TaglibFactory taglibFactory = freemarkerConfig.getTaglibFactory();
        taglibFactory.setObjectWrapper(freemarker.template.Configuration.getDefaultObjectWrapper(freemarker.template.Configuration.getVersion()));
        return taglibFactory;
    }

    @Autowired
    @Bean
    public FreeMarkerConfig springFreeMarkerConfig(ServletContext servletContext) throws IOException, TemplateException {
        return new MyFreeMarkerConfig(freeMarkerConfig(servletContext), taglibFactory(servletContext));
    }

    private static FreeMarkerConfigurer configFreeMarkerConfigurer(ServletContext servletContext) throws IOException,
            TemplateException {
        FreeMarkerConfigurer freemarkerConfig = new FreeMarkerConfigurer();
        freemarkerConfig
                .setPreTemplateLoaders(new ClassTemplateLoader(WebConfig.class, "/templates/"));
        ServletContext servletContextProxy = (ServletContext) Proxy.newProxyInstance(
                ServletContextResourceHandler.class.getClassLoader(),
                new Class<?>[] { ServletContext.class },
                new ServletContextResourceHandler(servletContext));
        freemarkerConfig.setServletContext(servletContextProxy);
        Properties settings = new Properties();
        settings.put("default_encoding", "UTF-8");
        freemarkerConfig.setFreemarkerSettings(settings);
        freemarkerConfig.afterPropertiesSet();
        return freemarkerConfig;
    }

    @Bean
    public FreeMarkerViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(false);
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }


    private static class ServletContextResourceHandler implements InvocationHandler
    {

        private final ServletContext target;

        private ServletContextResourceHandler(ServletContext target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("getResourceAsStream".equals(method.getName())) {
                Object result = method.invoke(target, args);
                if (result == null) {
                    result = WebConfig.class.getResourceAsStream((String) args[0]);
                }
                return result;
            } else if ("getResource".equals(method.getName())) {
                Object result = method.invoke(target, args);
                if (result == null) {
                    result = WebConfig.class.getResource((String) args[0]);
                }
                return result;
            }

            return method.invoke(target, args);
        }
    }

    private static class MyFreeMarkerConfig implements FreeMarkerConfig {

        private final freemarker.template.Configuration configuration;
        private final TaglibFactory taglibFactory;

        private MyFreeMarkerConfig(freemarker.template.Configuration configuration, TaglibFactory taglibFactory) {
            this.configuration = configuration;
            this.taglibFactory = taglibFactory;
        }

        @Override
        public freemarker.template.Configuration getConfiguration() {
            return configuration;
        }

        @Override
        public TaglibFactory getTaglibFactory() {
            return taglibFactory;
        }
    }
}