package com.wsw.cloudtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WswCloudAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WswCloudAccountServiceApplication.class, args);
	}

}
