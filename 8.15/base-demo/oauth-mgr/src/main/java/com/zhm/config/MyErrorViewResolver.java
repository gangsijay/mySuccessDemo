package com.zhm.config;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhm on 17-2-19.
 */
public class MyErrorViewResolver implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus httpStatus, Map<String, Object> map) {
        ModelAndView result = new ModelAndView();
        result.setStatus(httpStatus);
        if(httpStatus== HttpStatus.NOT_FOUND){
            if(isAsyncRequest(request)){
                result.setView(new MappingJackson2JsonView());
            }else{
                result.setViewName("/errors/404");
            }
        }
        else if(httpStatus== HttpStatus.FORBIDDEN){
            if(isAsyncRequest(request)){
                result.setView(new MappingJackson2JsonView());
            }else{
                result.setViewName("/errors/403");
            }
        }
        else {
            result.addAllObjects(map);
            if(isAsyncRequest(request)){
                result.setView(new MappingJackson2JsonView());
            }else{
                result.setViewName("/errors/500");
            }
        }
        return result;
    }
    public static boolean isAsyncRequest(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }
}
