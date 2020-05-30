package com.radsyn.jmerchdao.controller;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.model.Customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.radsyn.jmerchdao.model.Customer;

public interface CustomerController {
    @GetMapping("/customers")
    public ResponseEntity<CustomerCatalog> getAll();
    
    @GetMapping(value = "/customer/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Customer> getByID(@PathVariable("id") long id);
    
    @GetMapping(value = "/customerbyname/{lname}/{fname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getByName(@PathVariable("lname") String lname, @PathVariable("fname") String fname);
    
    @PutMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateEntity(@PathVariable("id") long id, @RequestBody Customer reqobj);
    
    @PostMapping(value = "/customer/")
	public ResponseEntity<Customer> createEntity(@RequestBody Customer reqobj, UriComponentsBuilder ucBuilder);
    
    @DeleteMapping(value = "/customer/{id}")
	public ResponseEntity<Customer> deleteEntity(@PathVariable("id") long id);
    
    @GetMapping(value = "/custkafka/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Customer> consumeByID(@PathVariable("id") long id);
}
