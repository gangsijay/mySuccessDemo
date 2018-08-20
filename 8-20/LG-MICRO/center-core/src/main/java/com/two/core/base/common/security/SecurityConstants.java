package com.two.core.base.common.security;

/**
 * 功能描述:权限控制相关的常量
 *
 * @auther: Tjee
 * @date: 2018/4/11 21:22
 */
public class SecurityConstants {

    /**
     * 后台默认登录页面
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/art/loginPage";

    /**
     *
     *后台用户名密码登录请求处理的url
     * @see
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL = "/art/login";

    public static final String DEFAULT_LOGIN_SUCCESS_URL = "/art/index";
    /**
     * 需要身份认证时，默认跳转的url
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    /**
     * 基础角色
     */
    public static String BASE_ROLE = "ROLE_USER";
    /**
     * 授权码模式
     */
    public static  String AUTHORIZATION_CODE = "authorization_code";
    /**
     * 密码模式
     */
    public static String PASSWORD = "password";
    /**
     * 刷新token
     */
    public static  String REFRESH_TOKEN = "refresh_token";

    /**
     * oauth token
     */
    public static String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * 手机登录URL
     */
    public static  String MOBILE_TOKEN_URL = "/mobile/token";

}
