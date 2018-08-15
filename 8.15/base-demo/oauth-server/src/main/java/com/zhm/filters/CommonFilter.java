package com.zhm.filters;


import com.zhm.service.UserInfoService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by haiming.zhuang on 2017/2/13.
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
        //设置ContextPath
        request.setAttribute("cpath",request.getServletContext().getContextPath());
        String uri = request.getRequestURI();
        //设置当前uri到页面
        request.setAttribute("req_uri",uri);
        if(session.getAttribute(
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)==null){
            //根据HTTP语言头设置语言信息
            LocaleResolver localeResolver =
                    (LocaleResolver) WebApplicationContextUtils.
                            getRequiredWebApplicationContext(request.getServletContext()).
                            getBean("localeResolver");
            AcceptHeaderLocaleResolver ahl = new AcceptHeaderLocaleResolver();
            localeResolver.setLocale(request,response,ahl.resolveLocale(request));
        }
        if(session.getAttribute("currUser")!=null){
            if(request.getParameter("lang")!=null){
                //为jqgrid等前端js设置语言信息
                if(request.getParameter("lang").indexOf("zh")!=-1){
                    request.setAttribute("user_language","zh");
                }else{
                    request.setAttribute("user_language","en");
                }
            }
            else{
                //为jqgrid等前端js设置语言信息
                Locale locale = (Locale) session.getAttribute(
                        SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
                if(locale==null){
                    locale = LocaleContextHolder.getLocale();
                }
                request.setAttribute("user_language",locale.getLanguage());
            }
            //登录状态直接过
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            if (SecurityContextHolder.getContext() != null) {
                if (SecurityContextHolder.getContext().getAuthentication() != null) {
                    //设置用户session
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username = "";
                    if (principal instanceof UserDetails) {
                        username = ((UserDetails) principal).getUsername();
                    } else {
                        username = principal.toString();
                    }
                    if (!StringUtils.isEmpty(username)) {
                        UserInfoService userInfoService =
                                (UserInfoService) WebApplicationContextUtils.
                                        getRequiredWebApplicationContext(request.getServletContext()).
                                        getBean("userInfoService");
                        session.setAttribute("currUser", userInfoService.findByEmail(username));
                    }
                }
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                if(uri.indexOf("login")!=-1||uri.indexOf("favicon.ico")!=-1||uri.indexOf("assets")!=-1) {
                    //直接放行的URI
                    filterChain.doFilter(servletRequest,servletResponse);
                } else{
                    response.sendRedirect("/login");
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
