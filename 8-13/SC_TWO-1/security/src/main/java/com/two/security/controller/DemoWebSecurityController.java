package com.two.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.two.core.base.common.security.SecurityConstants;
import com.two.security.domain.Msg;

@Controller
public class DemoWebSecurityController {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@RequestMapping(value ="/login")
	public String loginPage(ModelMap map) {
		return "login";
	}
	
	/**系统首页*/
    @RequestMapping("/")
    public String index(ModelMap model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有index权限");
        model.addAttribute("msg", msg);
        return "home";
    }
 
    /**系统首页2*/
    @RequestMapping("/index2")
    public String index2(ModelMap model){
        Msg msg =  new Msg("测试标题2","测试内容2","欢迎来到HOME页面,您拥有home权限");
        model.addAttribute("msg", msg);
        return "home";
    }
    
    /**home*/
    @RequestMapping("/home")
    public String home(ModelMap model){
        Msg msg =  new Msg("测试标题2","测试内容2","欢迎来到HOME页面,您拥有home权限");
        model.addAttribute("msg", msg);
        return "admin";
    }
	
	/**
	 * 当需要身份认证的时候，跳转到这里
	 */
	@RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
	public String requireAuthentication(HttpServletRequest request, HttpServletResponse response)  {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			if(StringUtils.endsWith(targetUrl, ".html")) {
				return "index";
			}
		}
		return "login";
	}
}
