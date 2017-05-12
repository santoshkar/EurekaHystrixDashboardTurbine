package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
@EnableHystrix
public class SbiConsumer2Application {

	@Autowired
	private CustomerDataProvider consumerClient;

	@Bean
	public CustomerDataProvider consumerControllerClient() {
		return new CustomerDataProvider();
	}

	@RequestMapping("/{id}")
	public String home(@PathVariable Long id) {
		String customer =  consumerClient.getCustomer(id);
		return customer;
	}
	
	@RequestMapping("/firstname/{name}")
	public String firstName(@PathVariable String name) {
		String customer =  consumerClient.getCustomerByFirstName(name);
		return customer;
	}
	
	
	@RequestMapping("/lastname/{name}")
	public String lastName(@PathVariable String name) {
		String customer =  consumerClient.getCustomerByLastName(name);
		return customer;
	}

	@RequestMapping("/")
	public String home() {
		return "welcome";
	}

	public static void main(String[] args) {
		SpringApplication.run(SbiConsumer2Application.class, args);
	}
}
