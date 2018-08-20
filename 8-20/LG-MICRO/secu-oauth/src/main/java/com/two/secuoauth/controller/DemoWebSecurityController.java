package com.two.secuoauth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.two.core.base.common.security.SecurityConstants;
import com.two.secuoauth.domain.Msg;

@Controller
public class DemoWebSecurityController {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@RequestMapping(value ="/login")
	public String loginPage(ModelMap map) {
		return "login";
	}
	
	@RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
	
	/**系统首页*/
    @RequestMapping("/")
    public String index(ModelMap model){
        Msg msg =  new Msg("系统主页","主页XXXX"
        		+ "内容","欢迎来到HOME页面,您拥有admin权限");
        model.addAttribute("msg", msg);
        return "home";
    }
    
    /**系统首页*/
    @RequestMapping("/adminUser")
    public String adminUser(ModelMap model){
        Msg msg =  new Msg("管理用户页","管理用户页测试内容","欢迎来到adminUser页面,您拥有adminUser权限");
        model.addAttribute("msg", msg);
        return "admin";
    }
    
    /**系统首页*/
    @RequestMapping("/adminhome")
    public String adminhome(ModelMap model){
        Msg msg =  new Msg("管理员主页","管理员主页测试内容","欢迎来到adminHOME页面,您拥有index权限");
        model.addAttribute("msg", msg);
        return "home";
    }
    
    /**系统首页*/
    @RequestMapping("/userhome")
    public String userhome(ModelMap model){
        Msg msg =  new Msg("普通用户主页","普通用户测试内容","欢迎来到userHOME页面,您拥有index权限");
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
