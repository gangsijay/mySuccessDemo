package com.zhm.controller;

import com.zhm.db.AppUser;
import com.zhm.db.Urls;
import com.zhm.db.UserInfo;
import com.zhm.service.AppUserService;
import com.zhm.service.UrlsService;
import com.zhm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.*;

/**
 * Created by zhm on 16-9-13.
 */
@Controller
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UrlsService urlsService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private RedisTemplate<String,Map> redisTemplate;

    /**
     * 该方法会调用的非常频繁，每个Gateway请求都会来请求
     * @param auth
     * @param user
     * @return
     */
    @RequestMapping(value = "/me")
    public @ResponseBody
    Map<String,Object> userInfo(OAuth2Authentication auth, Principal user){
        String client_id = auth.getOAuth2Request().getClientId();
        Map<String,Object> results = redisTemplate.opsForValue().get(user.getName()+"-"+client_id);
        if(results!=null){
            //redis缓存有数据直接返回
            return results;
        }else{
            //从数据库查询数据
            UserInfo userInfo = userInfoService.findByMobileOrEmail(user.getName());
            List<AppUser> appUser = appUserService.findListByUserid(userInfo.getUserid());
            results = new HashMap<String, Object>();
            results.put("authorities",getAuthorities(userInfo,appUser,client_id));
            results.put("username",userInfo.getUsername());
            results.put("mobile",userInfo.getMobile());
            results.put("email",userInfo.getEmail());
            results.put("realname",userInfo.getUsername());
            results.put("userid",userInfo.getUserid());
            redisTemplate.opsForValue().set(user.getName()+"-"+client_id,results);
            return results;
        }
    }
    private Set<GrantedAuthority> getAuthorities(UserInfo user, List<AppUser> appUsers, String client_id){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(AppUser appUser:appUsers){
            if(appUser.getClient_id().equals(client_id)){
                if(appUser.getIsadmin()==1){
                    List<Urls> allUrls = urlsService.findAllByClientid(client_id);
                    Set<String> limits = generateAllLimits(allUrls);
                    for(String limit : limits) {
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(limit);
                        authorities.add(grantedAuthority);
                    }
                }else{
                    //查询用户的权限
                    //获取用户权限
                    List<Urls> userUrls = getUrlsFromUserAndClientid(user.getUserid(),client_id);
                    //获取角色权限
                    List<Urls> roleUrls = getUrlsFromRoleAndClientid(user.getUserid(),client_id);
                    Set<String> limits = generateLimits(userUrls,roleUrls);
                    for(String limit : limits) {
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(limit);
                        authorities.add(grantedAuthority);
                    }
                }
            }
            if(appUser.getIsadmin()==1){
                authorities.add(new SimpleGrantedAuthority("ADMIN-"+appUser.getClient_id()));
            }
        }

        return authorities;
    }

    private Set<String> generateAllLimits(List<Urls> allUrls) {
        Set<String> results = new HashSet<String>();
        for(Urls tmp:allUrls){
            results.add(tmp.getNeed_permission());
        }
        return results;
    }

    private Set<String> generateLimits(List<Urls> userUrls, List<Urls> roleUrls) {
        Set<String> results = new HashSet<String>();
        for(Urls tmp:userUrls){
            results.add(tmp.getNeed_permission());
        }
        for(Urls tmp:roleUrls){
            results.add(tmp.getNeed_permission());
        }
        return results;
    }

    private List<Urls> getUrlsFromRoleAndClientid(String userid, String clientId) {
        return urlsService.findUrlByUserRoleAndClientid(userid,clientId);
    }

    private List<Urls> getUrlsFromUserAndClientid(String userid, String clientId) {
        return urlsService.findUrlsByUserAndClientid(userid,clientId);
    }
}
