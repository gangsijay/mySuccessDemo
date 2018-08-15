package com.zhm.controller;

import com.zhm.service.AppUserService;
import com.zhm.service.OAuthSessionService;
import com.zhm.service.UserInfoService;
import com.zhm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;


/**
 * Created by zhm on 16-12-7.
 */
@Controller
public class HomeController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private OAuthSessionService oAuthSessionService;
    @RequestMapping("/")
    public String root(ModelMap model){
        return "index";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String error,ModelMap model){
        if(error!=null) {
            model.addAttribute("error",error);
        }
        return "login";
    }
    @RequestMapping(value = "/404",method = RequestMethod.GET)
    public String error404(){
        return "/404";
    }
    @RequestMapping(value = "/500",method = RequestMethod.GET)
    public String error500(){
        return "/500";
    }
    @RequestMapping("/csrf")
    public @ResponseBody
    CsrfToken csrf(CsrfToken token) {
        return token;
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(Principal principal, HttpSession session){
        if("SUCCESS".equals(oAuthSessionService.deleteSession(principal.getName()))){
            session.invalidate();
        }
        return "redirect:/";
    }
}
