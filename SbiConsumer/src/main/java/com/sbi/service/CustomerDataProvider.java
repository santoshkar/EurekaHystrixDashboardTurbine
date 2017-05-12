package com.sbi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * @author Santosh Kumar Kar
 *
 */
public class CustomerDataProvider {

	@Autowired
	private DiscoveryClient discoveryClient;

	@HystrixCommand(fallbackMethod = "defaultGreeting")
	public String getAllCustomers() {

		List<ServiceInstance> instances=discoveryClient.getInstances("SBIProducer");
				
		ServiceInstance serviceInstance=instances.get(0);

		String baseUrl=serviceInstance.getUri().toString();

		baseUrl=baseUrl+"/customers";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
			response=restTemplate.exchange(baseUrl,
					HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}

		return response.getBody();
	}

	/*
	 * Without Eureka
	 */
	public String getAllCustomers_withoutEureka() {

		String baseUrl = "http://localhost:8081/customers";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
			response=restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
		return response.getBody();
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	

    private String defaultGreeting() {
        return "Service is down";
    }
}
