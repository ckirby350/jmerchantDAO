package com.radsyn.jmerchdao.catalog;

import com.radsyn.jmerchdao.model.Customer;
import java.io.Serializable; 
import java.util.List;
import java.util.Map;

public class CustomerCatalogImpl implements CustomerCatalog, Serializable {
 	private static final long serialVersionUID = 1L;
	private List<Customer> customerRecords;
    
	public List<Customer> getCustomerRecords() {
		return customerRecords;
	}
	public void setCustomerRecords(List<Customer> customerRecords) {
		this.customerRecords = customerRecords;
	}
	
}
