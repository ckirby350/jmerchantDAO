package com.radsyn.jmerchdao.repo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.radsyn.jmerchdao.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;


@DataJpaTest
@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value="classpath:data/Customer.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class CustomerRepositoryTest {
	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired
    private TestEntityManager entityManager;
    
	@Test
    public void shouldInjectAllComponents() {
        assertNotNull(customerRepository);
    }
	
	
	@Test	
    public void shouldFindAll() {
        List<Customer> objList = customerRepository.findAll();

        //Total active and inactive records in xml, 4 in this case
        assertEquals(4, objList.size());
     }
	
}
