package com.two.secuoauth.config;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
 
/**
 * 登录成功处理器
 *
 * @Author: 我爱大金子
 * @Description: 登录成功处理器
 * @Date: Create in 11:35 2017/7/6
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User userDetails = (User) authentication.getPrincipal();
        System.out.println("登录用户：username=" + userDetails.getUsername() + ", uri=" + request.getContextPath());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}