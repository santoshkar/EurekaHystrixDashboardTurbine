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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sbi.domain.Customer;
import com.sbi.domain.ViewMessage;

/**
 * 
 * @author Santosh Kumar Kar
 *
 */
public class CustomerDataProvider {

	@Autowired
	private DiscoveryClient discoveryClient;

	@HystrixCommand(fallbackMethod = "failoverMessageForId")
	public ViewMessage<Customer[]> getCustomerById(Long id) {

		List<ServiceInstance> instances = discoveryClient.getInstances("SBIProducer");

		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/customer/{id}";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Customer> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), Customer.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}

		ViewMessage<Customer[]> msg = new ViewMessage<>();
		ObjectMapper mapper = new ObjectMapper();
		Customer c = mapper.convertValue(response.getBody(), Customer.class); 
		Customer list[] = new Customer[1];
		list[0] = c;
		msg.setContainsError(false);
		msg.setData(list);
		
		return msg;
	}

	@HystrixCommand(fallbackMethod = "failoverMessageForName")
	public ViewMessage<Customer[]> getCustomerByFirstName(String name) {

		List<ServiceInstance> instances = discoveryClient.getInstances("SBIProducer");

		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/customerByFirstName/{firstName}";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Customer[]> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), Customer[].class, name);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}

		ViewMessage<Customer[]> msg = new ViewMessage<>();
		ObjectMapper mapper = new ObjectMapper();
		Customer[] clist = mapper.convertValue(response.getBody(), Customer[].class); 
		msg.setContainsError(false);
		msg.setData(clist);
		
		return msg;
	}
	
	
	@HystrixCommand(fallbackMethod = "failoverMessageForName")
	public ViewMessage<Customer[]> getCustomerByLastName(String name) {

		List<ServiceInstance> instances = discoveryClient.getInstances("SBIProducer");

		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/customerByLastName/{lastName}";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Customer[]> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), Customer[].class, name);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}

		ViewMessage<Customer[]> msg = new ViewMessage<>();
		ObjectMapper mapper = new ObjectMapper();
		Customer[] clist = mapper.convertValue(response.getBody(), Customer[].class); 
		msg.setContainsError(false);
		msg.setData(clist);
		
		return msg;
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	@SuppressWarnings("unused")
	private ViewMessage<Customer[]> failoverMessageForId(Long id) {
		
		ViewMessage<Customer[]> msg = new ViewMessage<>();
		msg.setContainsError(true);
		msg.setMessage("Producer Server is down");
	
		return msg;
	}
	
	@SuppressWarnings("unused")
	private ViewMessage<Customer[]> failoverMessageForName(String name) {
		
		ViewMessage<Customer[]> msg = new ViewMessage<>();
		msg.setContainsError(true);
		msg.setMessage("Producer Server is down");
	
		return msg;
	}
}
