package com.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

import com.two.filter.AccessUserNameFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulTestApplication {
	
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
	
	@Bean
	public AccessUserNameFilter accessUserNameFilter() {
		return new AccessUserNameFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulTestApplication.class, args);
	}
	
}
