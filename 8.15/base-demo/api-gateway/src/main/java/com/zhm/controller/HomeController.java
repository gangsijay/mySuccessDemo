package com.zhm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhm on 17-3-18.
 */
@Controller
public class HomeController extends BaseController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String root(){
        return "index";
    }
}
