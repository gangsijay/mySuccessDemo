package com.zhm.filters;


import com.zhm.db.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by haiming.zhuang on 2016/7/13.
 */
public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        request.setAttribute("cpath",request.getServletContext().getContextPath());
        Locale locale = (Locale) session.getAttribute(
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(locale==null){
            LocaleResolver localeResolver =
                    (LocaleResolver) WebApplicationContextUtils.
                            getRequiredWebApplicationContext(request.getServletContext()).
                            getBean("localeResolver");
            AcceptHeaderLocaleResolver ahl = new AcceptHeaderLocaleResolver();
            localeResolver.setLocale(request,response,ahl.resolveLocale(request));
            request.setAttribute("user_language",localeResolver.resolveLocale(request).getLanguage());

        }else{
            request.setAttribute("user_language",locale.getLanguage());
        }
        String uri = request.getRequestURI();
        request.setAttribute("req_uri",uri);
        if(session.getAttribute("currUser")!=null){
            //已经登录
            if(request.getParameter("lang")!=null){
                //链接有语言
                if(request.getParameter("lang").indexOf("zh")!=-1){
                    request.setAttribute("user_language","zh");
                }else{
                    request.setAttribute("user_language","en");
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            if (SecurityContextHolder.getContext() != null) {
                if (SecurityContextHolder.getContext().getAuthentication() != null) {
                    OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
                    UserInfo userInfo = new UserInfo();
                    generateUserInfo(auth,userInfo);
                    session.setAttribute("currUser",userInfo);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }else{
                if(uri.indexOf("login")!=-1||uri.indexOf("favicon.ico")!=-1||uri.indexOf("assets")!=-1) {
                    filterChain.doFilter(servletRequest,servletResponse);
                } else{
                    response.sendRedirect("/login");
                }
            }
        }
    }

    private void generateUserInfo(OAuth2Authentication auth,UserInfo userInfo) {
        Map map = (LinkedHashMap)auth.getUserAuthentication().getDetails();
        userInfo.setUserid(map.get("userid").toString());
        userInfo.setUsername(map.get("realname").toString());
        userInfo.setEmail(map.get("email").toString());
    }

    @Override
    public void destroy() {

    }
}
