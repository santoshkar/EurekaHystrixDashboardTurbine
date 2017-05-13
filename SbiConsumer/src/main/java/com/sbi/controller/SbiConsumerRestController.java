package com.sbi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.domain.Customer;
import com.sbi.domain.ViewMessage;
import com.sbi.service.CustomerDataProvider;

@RestController
/**
 * 
 * @author Santosh Kumar Kar
 *
 */
public class SbiConsumerRestController {
	
	@Autowired
	private CustomerDataProvider  consumerClient;

	@RequestMapping("/customers")
	public ViewMessage<Customer[]> firstName() {
		return consumerClient.getAllCustomers();
	}
}
