package com.two.lgums.security.authorize;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * 功能描述: 权限匹配,有返回true
 *
 * @Auther: 田杰
 * @Date: 2018/4/24 22:47
 */
@Component("rdPermissionService")
public class RdPermissionServiceImpl implements RdPermissionService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            //查询用户所有权限urls
            Set<String> permissions = null;// userService.findAllPermissionsUrls(username);
//			//读取用户所有权限的url，需要查询数据库
//			Set<String> urls = new HashSet<>();
//			urls.add("/user/*");
            if(permissions == null || permissions.isEmpty()){
                return true;
            }
            for (String url : permissions) {
                boolean hasUrl = antPathMatcher.match(url == null ? "" : url, request.getRequestURI());
                if(hasUrl){
                    hasPermission=true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
