package com.wsw.cloudtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WswCloudStorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WswCloudStorageServiceApplication.class, args);
	}

}
