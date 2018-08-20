package org.baeldung.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/user")
    public String user() {
        System.out.println("");
        return "user";
    }
}
