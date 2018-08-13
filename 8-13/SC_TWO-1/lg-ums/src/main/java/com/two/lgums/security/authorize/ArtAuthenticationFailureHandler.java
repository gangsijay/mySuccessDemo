package com.two.lgums.security.authorize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.two.core.exception.BizException;

/*** 
* @功能描述: 登录失败后的处理
* @Param:
* @return:  
* @Author: 田杰
* @Date: 2018/4/18
*/ 
@Component
public class ArtAuthenticationFailureHandler
		extends SimpleUrlAuthenticationFailureHandler{


	@Autowired
	private ObjectMapper objectMapper;

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		
		//log.info("登录失败");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().write(objectMapper.writeValueAsString(new BizException("SYS-ERRO-OOOO2","认证失败")));

	}
}
