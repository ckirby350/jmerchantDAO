package com.radsyn.jmerchdao.common;

import java.util.List;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.catalog.CustomerCatalogImpl;
import com.radsyn.jmerchdao.model.Customer;

public class CustomerCommon {
	public static CustomerCatalog get1RowTestCatalog() {
    	CustomerCatalogImpl xCat = new CustomerCatalogImpl();
    	List<Customer> xList = new java.util.ArrayList<Customer>();
    	Customer xRec = new Customer();
    	xRec.setAddr1("100 Oak St.");
    	xRec.setCity("Arlington");
    	xRec.setCountry("US");
    	xRec.setCreatedt(new java.util.Date());
    	xRec.setCustID(1);
    	xRec.setDob(new java.util.Date());
    	xRec.setEmail("test@gmail.com");
    	xRec.setFirstName("Bob");
    	xRec.setLastName("Smith");
    	xRec.setPhone1("9728882222");
    	xRec.setPostcode("75026");
    	xRec.setStateprov("TX");
    	xList.add(xRec);
    	xCat.setCustomerRecords(xList);
    	return xCat;
    }
    
	public static CustomerCatalog getMultiRowTestCatalog() {
    	CustomerCatalogImpl xCat = new CustomerCatalogImpl();
    	List<Customer> xList = new java.util.ArrayList<Customer>();
    	Customer xRec = new Customer();
    	xRec.setAddr1("100 Oak St.");
    	xRec.setCity("Arlington");
    	xRec.setCountry("US");
    	xRec.setCreatedt(new java.util.Date());
    	xRec.setCustID(1);
    	xRec.setDob(new java.util.Date());
    	xRec.setEmail("test@gmail.com");
    	xRec.setFirstName("Bob");
    	xRec.setLastName("Smith");
    	xRec.setPhone1("9728882222");
    	xRec.setPostcode("75026");
    	xRec.setStateprov("TX");
    	xList.add(xRec);
    	xRec = new Customer();
    	xRec.setAddr1("333 Pine Ave.");
    	xRec.setCity("San Diego");
    	xRec.setCountry("US");
    	xRec.setCreatedt(new java.util.Date());
    	xRec.setCustID(2);
    	xRec.setDob(new java.util.Date());
    	xRec.setEmail("jj9983@gmail.com");
    	xRec.setFirstName("Jim");
    	xRec.setLastName("Johnson");
    	xRec.setPhone1("6193828787");
    	xRec.setPostcode("96244");
    	xRec.setStateprov("CA");
    	xList.add(xRec);
    	xRec = new Customer();
    	xRec.setAddr1("1016 Bandera Court");
    	xRec.setCity("Ft. Worth");
    	xRec.setCountry("US");
    	xRec.setCreatedt(new java.util.Date());
    	xRec.setCustID(3);
    	xRec.setDob(new java.util.Date());
    	xRec.setEmail("matty837@gmail.com");
    	xRec.setFirstName("Matt");
    	xRec.setLastName("Meyers");
    	xRec.setPhone1("9728938732");
    	xRec.setPostcode("76244");
    	xRec.setStateprov("TX");
    	xList.add(xRec);
    	xCat.setCustomerRecords(xList);
    	return xCat;
    }
}
