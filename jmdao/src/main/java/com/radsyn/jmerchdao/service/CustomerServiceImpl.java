package com.radsyn.jmerchdao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.dao.CustomerDao;
import com.radsyn.jmerchdao.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	public Customer findById(long xid) {
		return customerDao.findById(xid);
	}
	
	public CustomerCatalog findAll() {
		return customerDao.findAll();
	}
	
	public java.util.List<Customer> findByName(String lname, String fname) {
		return customerDao.findByName(lname, fname);
	}
	
	public void insert(Customer myObjectVO) {
		customerDao.insert(myObjectVO);
	}
	
	public void update(Customer myObjectVO) {
		customerDao.update(myObjectVO);
	}
	
	public void remove(Customer myObjectVO) {
		customerDao.remove(myObjectVO);
	}
	
	public void evict(Customer myObjectVO) {
		customerDao.evict(myObjectVO);
	}
	
}
