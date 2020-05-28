package com.radsyn.jmerchdao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.catalog.CustomerCatalogImpl;
import com.radsyn.jmerchdao.model.Customer;
import com.radsyn.jmerchdao.repo.CustomerRepository;
import com.radsyn.jmerchdao.service.CustomerServiceImpl;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

@RestController
public class CustomerControllerImpl implements CustomerController {
	/***
	@Autowired
    private CustomerRepository xRepository;
	***/
	
	/***	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private String baseSQLstr = "select x.* from Customer x";
	
    @Autowired
    CustomerControllerImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    ***/
	
	@Resource
    private CustomerServiceImpl xService;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<CustomerCatalog> getAll() {
    	CustomerCatalog xcat = xService.findAll();
    	return new ResponseEntity<CustomerCatalog>(xcat, HttpStatus.OK);
    	
    }
    
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Customer> getByID(long id) {
    	/***
    	return this.xRepository
                .findById(id)
                .map(ResponseEntity.accepted()::body)
                .orElse(ResponseEntity.notFound().build());
        ***/
    	
    	Customer xcust = xService.findById(id);    	 			
		if (xcust == null) {
			//log.info(typeName + " with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(xcust, HttpStatus.OK);
		
    }
    
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<Customer>> getByName(String lname, String fname) {
    	List<Customer> searchResults = null;
    	/***
    	String queryStr = "";
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
 		
		***/
    	
    	searchResults = xService.findByName(lname, fname);
    			
    	return new ResponseEntity<List<Customer>>(searchResults, HttpStatus.OK); 
	}
    
    @Override
	public ResponseEntity<Customer> updateEntity(long id, Customer reqobj) {
    	/***
		//System.out.println("update obj reqbody=" + reqobj.getDob());
    	Optional<Customer> domainObj = xRepository.findById(id);
    	
		if (domainObj == null || domainObj.isEmpty()) {
			//log.info(typeName + " with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		Customer currentObj = domainObj.get();

		currentObj.populateWithObjectValues(reqobj);
		
		//System.out.println("update obj dob=" + currentObj.getDob());
		
		try {
			xRepository.saveAndFlush(currentObj);
		} catch (Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Customer>(currentObj, HttpStatus.OK);
		***/
    	Customer domainObj = xService.findById(id);    	 			
		if (domainObj == null) {
			//log.info(typeName + " with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		domainObj.populateWithObjectValues(reqobj);
		try {
			xService.update(domainObj);
		} catch (Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Customer>(domainObj, HttpStatus.OK);
	}
    
    @Override
    public ResponseEntity<Customer> createEntity(@RequestBody Customer reqobj, UriComponentsBuilder ucBuilder) {
    	try {
    		//xRepository.saveAndFlush(reqobj);
    		xService.insert(reqobj);
    	} catch (Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		}
    	HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(reqobj.getCustID()).toUri());
		return new ResponseEntity<Customer>(reqobj, HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<Customer> deleteEntity(long xid) {
    	Customer domainObj = null;
    	/***    	
    	Optional<Customer> xdomainObj = xRepository.findById(xid);
    	
		if (xdomainObj == null || xdomainObj.isEmpty()) {
			//log.info("Unable to delete " + typeName + " with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		domainObj = xdomainObj.get();

		xRepository.delete(domainObj);
		***/
    	domainObj = xService.findById(xid);
    	if (domainObj == null) {
			//log.info("Unable to delete " + typeName + " with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
    	xService.remove(domainObj);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
}


