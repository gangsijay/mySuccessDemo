package com.two.lgums.security.authorize;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/**
 * 功能描述: 权限匹配,有返回true
 *
 * @Auther: 田杰
 * @Date: 2018/4/24 22:39
 */
public interface RdPermissionService {
   boolean  hasPermission(HttpServletRequest request, Authentication authentication);
}
