package com.radsyn.jmerchdao.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.catalog.CustomerCatalogImpl;
import com.radsyn.jmerchdao.dao.CustomerDao;
import com.radsyn.jmerchdao.model.Customer;
import com.radsyn.jmerchdao.repo.CustomerRepository;

@Component
public class CustomerDaoImpl implements CustomerDao {
	@Resource
    private CustomerRepository customerRepository;
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);
	private String baseSQLstr = "select x.* from Customer x";
	
	private LocalSessionFactoryBean entityManagerFactory;

	@Autowired
	CustomerDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@SuppressWarnings("unchecked")
	public Customer findById(long xid) {
		Optional<Customer> xobj = customerRepository.findById(xid);
		if (xobj == null || xobj.isEmpty()) {
			return null;
		}
		return xobj.get();
	}
	
	@SuppressWarnings("unchecked")
	public CustomerCatalog findAll() {
		List<Customer> list = null;
		list = customerRepository.findAll();
		CustomerCatalogImpl xcat = new CustomerCatalogImpl();
		xcat.setCustomerRecords(list);
		return xcat;
	}
	
	@SuppressWarnings("unchecked")
	public java.util.List<Customer> findByName(String lname, String fname) {
		String queryStr = "";
		List<Customer> searchResults = null;
    	Map<String, String> queryParams = new HashMap<>();
        //queryParams.put("searchTerm", searchTerm);
 
        if (fname == null || fname.trim().length() < 1 || fname.toUpperCase().trim().equals("NULL")) {
			queryStr = baseSQLstr + " where x.lastName like '" 
					+ lname.toUpperCase().trim() + "%' order by lastName, firstName";
		} else if (lname == null || lname.trim().length() < 1 || lname.toUpperCase().trim().equals("NULL")) {
			queryStr = baseSQLstr + " where x.firstName like '" 
					+ fname.toUpperCase().trim() + "%' order by firstName, lastName";
		} else {
			queryStr = baseSQLstr + " where x.lastName like '" 
					+ lname.toUpperCase().trim() + "%' and x.firstName like '" + fname.toUpperCase().trim() + "%' order by lastName, firstName";
		}
        
        searchResults = jdbcTemplate.query(queryStr, queryParams, new BeanPropertyRowMapper<>(Customer.class));
 
		return searchResults;
	}
	
	@SuppressWarnings("unchecked")
	public java.util.List<Customer> findByCustomerAddr1(String xaddr1){
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public java.util.List<Customer> findByPhone1(String xphone1) {
		return null;
	}
	
	public void insert(Customer myObjectVO) {
		customerRepository.saveAndFlush(myObjectVO);
	}
	
	public void update(Customer myObjectVO) {
		//System.out.println("update obj reqbody=" + reqobj.getDob());
		/***
    	Optional<Customer> domainObj = customerRepository.findById(myObjectVO.getCustID());
    	
		if (domainObj == null || domainObj.isEmpty()) {
			//log.info(typeName + " with id " + id + " not found");
			return;
		}
		Customer currentObj = domainObj.get();

		currentObj.populateWithObjectValues(myObjectVO);
		
		//System.out.println("update obj dob=" + currentObj.getDob());
		***/
		customerRepository.saveAndFlush(myObjectVO);
		
	}
	
	public void remove(Customer myObjectVO) {
		customerRepository.delete(myObjectVO);
	}
	
	public void evict(Customer myObjectVO) {
		
	}

}
