package com.two.oauth2.controller;

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

@Controller
public class UMSWebSecurityController {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@RequestMapping(value ="/login")
	public String loginPage(ModelMap map) {
		return "login";
	}
	
	@RequestMapping(value ="/oauth/confirm_access")
	public String confirmAccess(ModelMap map) {
		return "authorize";
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
