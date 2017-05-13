package com.sbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.service.CustomerDataProvider;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
@EnableHystrix
/**
 * 
 * @author Sanosh Kumar Kar
 *
 */
public class SbiConsumerApplication {
	
	@Bean
	public  CustomerDataProvider  consumerControllerClient()
	{
		return  new CustomerDataProvider();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SbiConsumerApplication.class, args);
	}
}
