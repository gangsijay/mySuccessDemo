package com.two.lgums.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.two.core.base.common.security.SecurityConstants;

@Controller
public class UMSWebSecurityController {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@RequestMapping(value ="/login")
	public String loginPage(ModelMap map) {
		return "login1";
	}
	
	@RequestMapping(value ="/me")
	public String me(ModelMap map) {
		return "me";
	}
	
	@RequestMapping(value="/loginProcess")
    public String loginPage11(ModelMap map){
        return "loginProcess"; //当浏览器输入/index时，会返回 /static/home.html的页面
    }

	@RequestMapping(value ="/loginSuccess")
	public String login(ModelMap map) {

		return "success";
	}
	
	@GetMapping(value ="/loginSuccess")
	public String login1111(ModelMap map) {

		return "success";
	}
	
//	@RequestMapping(value ="/")
//	public String loginHome(ModelMap map) {
//		return "home";
//	}
	
	@RequestMapping("/")
    public String index(Model model){
//        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有index权限");
//        model.addAttribute("msg", msg);
        return "home";
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
