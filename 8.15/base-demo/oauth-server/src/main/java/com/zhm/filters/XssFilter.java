package com.zhm.filters;

import com.github.rkpunjal.sqlsafe.SqlSafeUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by zhm on 17-2-20.
 */
public class XssFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                if(!SqlSafeUtil.isSqlInjectionSafe(value)){

                    if(isAsyncRequest(request)){
                        response.setStatus(500);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().print("{\"status\":500,\"error\":\"\",\"exception\":\"RuntimeException\",\"message\":\"提交的数据含有sql注入风险\"}");
                        return false;
                    }
                    throw new RuntimeException("提交的数据含有sql注入风险");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public static boolean isAsyncRequest(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }
}
