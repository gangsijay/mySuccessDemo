package com.two.controller;

import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SuperS on 2017/9/26.
 *
 * @author SuperS
 */
@RestController
public class TestController {
    @GetMapping("/")
    public String test(){
        return "succes111s!";
    }
    
    @GetMapping("/index")
    public String index(){
        return "succes11s11!";
    }
    
    @RequestMapping("/user") 
    public String user() { 
        return "user";
    }
}
