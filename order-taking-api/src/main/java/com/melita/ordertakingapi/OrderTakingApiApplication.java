package com.melita.ordertakingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderTakingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderTakingApiApplication.class, args);
	}

}
