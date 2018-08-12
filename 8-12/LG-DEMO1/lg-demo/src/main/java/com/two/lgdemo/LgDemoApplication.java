package com.two.lgdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LgDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgDemoApplication.class, args);
	}
}
