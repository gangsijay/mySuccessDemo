package com.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableTransactionManagement
public class LgUmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgUmsApplication.class, args);
	}
}
