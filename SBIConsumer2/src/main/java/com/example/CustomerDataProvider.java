package com.example;

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

	@HystrixCommand(fallbackMethod = "failoverMessage")
	public String getCustomer(Long id) {

		List<ServiceInstance> instances = discoveryClient.getInstances("SBIProducer");

		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/customer/{id}";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}

		return response.getBody();
	}

	@HystrixCommand(fallbackMethod = "failoverMessage")
	public String getCustomerByFirstName(String name) {

		List<ServiceInstance> instances = discoveryClient.getInstances("SBIProducer");

		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/customerByFirstName/{firstName}";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class, name);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}

		return response.getBody();
	}
	
	
	@HystrixCommand(fallbackMethod = "failoverMessage")
	public String getCustomerByLastName(String name) {

		List<ServiceInstance> instances = discoveryClient.getInstances("SBIProducer");

		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/customerByLastName/{lastName}";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class, name);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}

		return response.getBody();
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	@SuppressWarnings("unused")
	private String failoverMessage(Long id) {
		return "Service is down";
	}
	
	@SuppressWarnings("unused")
	private String failoverMessage(String name) {
		return "Service is down";
	}
}
