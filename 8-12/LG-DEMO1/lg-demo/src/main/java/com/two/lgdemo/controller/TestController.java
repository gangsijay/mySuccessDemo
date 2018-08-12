package com.two.lgdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by SuperS on 2017/9/26.
 *
 * @author SuperS
 */
@RestController
public class TestController {
	@RequestMapping("/test1")
    public String test(){
        return "test1";
    }
    
    @RequestMapping("/test")
    public ModelAndView tes1t(){
        return new ModelAndView("test");
    }
    
    @RequestMapping("/test2")
    public ModelAndView tes11t(){
        return new ModelAndView("login");
    }
}
