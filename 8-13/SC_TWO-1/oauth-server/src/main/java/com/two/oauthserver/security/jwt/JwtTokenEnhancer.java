package com.two.oauthserver.security.jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.two.oauthserver.security.config.ArtAuthUser;

/*** 
* @功能描述: 增强jwt
* @Param:
* @return:  
* @Author: 田杰
* @Date: 2018/4/18
*/ 
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        //往jwt添加的自定义信息
        Map<String , Object> info = new HashMap<>();
        ArtAuthUser user=null;
        if(Objects.nonNull(authentication.getPrincipal())){
            user = ((ArtAuthUser) authentication.getPrincipal());
            info.put("userId", user.getId());
            info.put("userLoginName",  user.getLoginName());
            info.put("userName",  user.getUsername());
            info.put("userRoleList",  user.getRoleList());
        }

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
