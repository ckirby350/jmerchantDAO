package com.radsyn.jmerchdao.dao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.common.CustomerCommon;
import com.radsyn.jmerchdao.model.Customer;
import com.radsyn.jmerchdao.repo.CustomerRepository;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/****** pre-Jupiter methods
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
@RunWith(MockitoJUnitRunner.class)
*****/

@ExtendWith(MockitoExtension.class)
public class CustomerDaoTest {
	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
    CustomerDaoImpl customerDao;
	
	@BeforeEach
	void setUp() {
		NamedParameterJdbcTemplate jdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
		customerDao = new CustomerDaoImpl(jdbcTemplate);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldInjectAllComponents(){
		assertNotNull(customerRepository);
		assertNotNull(customerDao);
	}

	@Test
	public void getAllCustomersTest()
	{
		CustomerCatalog xcat = CustomerCommon.getMultiRowTestCatalog();
		when(customerRepository.findAll()).thenReturn(xcat.getCustomerRecords());
		
		CustomerCatalog returnValueCat = customerDao.findAll();
		assertEquals(3, returnValueCat.getCustomerRecords().size());
        verify(customerRepository, times(1)).findAll();

	 }
	
	@Test
	public void getByIDTest()
	{
		Optional<Customer> testObj = Optional.of(CustomerCommon.get1RowTestCatalog().getCustomerRecords().get(0));
		when(customerRepository.findById((long) 1)).thenReturn(testObj);
		
		Customer returnObj = customerDao.findById((long) 1);
		assertNotNull(returnObj);
		assertEquals("Smith", returnObj.getLastName());
        verify(customerRepository, times(1)).findById((long) 1);

	 }
}
