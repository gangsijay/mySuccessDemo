//package com.two.secuoauth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
//
//import com.two.core.base.common.security.SecurityConstants;
//import com.two.core.security.authorize.AuthorizeConfigManager;
//
///**
// * Created by SuperS on 2017/9/25.
// *
// * @author SuperS
// */
//@Configuration
//@EnableResourceServer
//@Order(6)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
////    @Autowired
////    SysProperties sysProperties;
//    @Autowired
//    AuthorizeConfigManager authorizeConfigManager;
//    @Autowired
//    private OAuth2WebSecurityExpressionHandler expressionHandler;
//
//    /***
//     * @功能描述: 配置jwt
//     * @Param: []
//     * @return: org.springframework.security.oauth2.provider.token.TokenStore
//     * @Author: 田杰
//     * @Date: 2018/4/18
//     */
////    @Bean
////    public TokenStore jwtTokenStore() {
////        return new JwtTokenStore(jwtAccessTokenConverter());
////    }
//
//    /***
//     * @功能描述: 给JWT加签名
//     * @Param: []
//     * @return: org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
//     * @Author: 田杰
//     * @Date: 2018/4/18
//     */
////    @Bean
////    public JwtAccessTokenConverter jwtAccessTokenConverter() {
////        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
////        //jwt签名
////        jwtAccessTokenConverter.setSigningKey("");
////        return jwtAccessTokenConverter;
////    }
//    
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
//                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL).successForwardUrl("/index2")
//                .and().csrf().disable().anonymous().disable();
//        authorizeConfigManager.config(http.authorizeRequests());
//    }
//
////    @Override
////    public void configure(ResourceServerSecurityConfigurer resources) {
////        resources.resourceId(sysProperties.getOauth2()
////                .getResourceid()).tokenStore(jwtTokenStore()).expressionHandler(expressionHandler);
////    }
//
//    @Bean
//    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
//        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(applicationContext);
//        return expressionHandler;
//    }
//}
