package com.sbi.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Santosh Kumar Kar
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	public Customer findById(Long id);
	public List<Customer> findByFirstNameContainingIgnoreCase(String name);
	public List<Customer> findByLastNameContainingIgnoreCase(String name);
}
