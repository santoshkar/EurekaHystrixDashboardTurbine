package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CustomerDataProvider;
import com.sbi.domain.Customer;
import com.sbi.domain.ViewMessage;

@RestController
/**
 * 
 * @author Santosh Kumar Kar
 *
 */
public class SbiConsumerRestController {
	
	@Autowired
	private CustomerDataProvider  consumerClient;
	
	@RequestMapping("/firstname/{name}")
	public ViewMessage<Customer[]> firstName(@PathVariable String name ) {
		return consumerClient.getCustomerByFirstName(name);
	}
	
	
	@RequestMapping("/lastname/{name}")
	public ViewMessage<Customer[]> lastName(@PathVariable String name ) {
		return  consumerClient.getCustomerByLastName(name);
	}

	@RequestMapping("/{id}")
	public ViewMessage<Customer[]> customers(@PathVariable Long id ){
		return consumerClient.getCustomerById(id);
	}
}
