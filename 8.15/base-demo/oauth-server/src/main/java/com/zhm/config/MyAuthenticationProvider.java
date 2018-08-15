package com.zhm.config;

import com.zhm.db.UserInfo;
import com.zhm.service.AppUserService;
import com.zhm.service.UserInfoService;
import com.zhm.utils.EncryptUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

/**
 * Created by zhm on 16-3-13.
 */
public class MyAuthenticationProvider implements AuthenticationProvider {
    private UserInfoService userInfoService;
    private AppUserService appUserService;
    public MyAuthenticationProvider(AppUserService appUserService,UserInfoService userInfoService){
        //注入用户服务
        this.appUserService = appUserService;
        this.userInfoService = userInfoService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        //获取输入用户信息
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());
        //查询本地数据库用户数据
        UserInfo userInfo = userInfoService.findByMobileOrEmail(username);
        if(userInfo==null){
            throw new BadCredentialsException("用户不存在");
        }
        if(auth.getDetails()!=null&&auth.getDetails() instanceof Map){
            Map clientDetails = (Map)auth.getDetails();
            String cid = clientDetails.get("client_id").toString();
            if(appUserService.checkUserAllowVisitApp(cid,userInfo.getUserid())==0){
                throw new BadCredentialsException("用户无权登录该应用");
            }
        }
        if(EncryptUtils.encodeMD5String(password).equalsIgnoreCase(userInfo.getPassword())) {
            //密码匹配返回成功信息
            return new UsernamePasswordAuthenticationToken(userInfo.getMobile(), null, null);
        }
        //返回不匹配信息
        throw new BadCredentialsException("Bad Credentials");
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
