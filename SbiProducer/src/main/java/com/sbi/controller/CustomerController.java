package com.sbi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.domain.Customer;
import com.sbi.domain.CustomerRepository;

/**
 * 
 * @author Santosh Kumar Kar
 *
 */
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerDao;
		
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public List<Customer> getCustomers(){
		return (List<Customer>) customerDao.findAll();
	}
	
	@RequestMapping(value="/customer/{userId}", method=RequestMethod.GET)
	public Customer getCustomerById(@PathVariable Long userId){
		return customerDao.findById(userId);
	}
	
	@RequestMapping(value="/customerByFirstName/{firstName}", method=RequestMethod.GET)
	public List<Customer> getCustomerByFirstName(@PathVariable String firstName){
		return customerDao.findByFirstNameContainingIgnoreCase(firstName);
	}
	
	@RequestMapping(value="/customerByLastName/{lastName}", method=RequestMethod.GET)
	public List<Customer> getCustomerByLastName(@PathVariable String lastName){
		return customerDao.findByLastNameContainingIgnoreCase(lastName);
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public Customer postCustomers(@RequestBody Customer customer){
		return customerDao.save(customer);
	}
	
}
