package com.radsyn.jmerchdao.dao;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.model.Customer;

public interface CustomerDao {
		Customer findById(long xid);		
		CustomerCatalog findAll();
		java.util.List<Customer> findByName(String lname, String fname);
		void insert(Customer myObjectVO);
		void update(Customer myObjectVO);
		void remove(Customer myObjectVO);
		void evict(Customer myObjectVO);
}
