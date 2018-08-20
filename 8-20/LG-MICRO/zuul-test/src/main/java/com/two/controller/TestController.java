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
    @GetMapping("/user")
    public String test(){
        return "succes111s!";
    }
    
    @GetMapping("/indexsss")
    public String index(){
        return "succes11s11!";
    }
    
    @RequestMapping("/usersss") 
    public String user() { 
        return "user";
    }
}
