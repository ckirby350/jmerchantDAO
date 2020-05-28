package com.radsyn.jmerchdao.controller;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;

import com.radsyn.jmerchdao.catalog.CustomerCatalog;
import com.radsyn.jmerchdao.catalog.CustomerCatalogImpl;
import com.radsyn.jmerchdao.common.CustomerCommon;
import com.radsyn.jmerchdao.model.Customer;
import com.radsyn.jmerchdao.service.CustomerServiceImpl;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@InjectMocks
    CustomerControllerImpl customerController;

    @Resource
    private org.springframework.test.web.servlet.MockMvc mvc;

    @MockBean
    CustomerServiceImpl customerService;
    
    @Test
    public void shouldInjectAllComponents(){
    	assertNotNull(mvc);
        assertNotNull(customerController);
        assertNotNull(customerService);
    }
    
    private String urlStr = "/customer";
    
        
    @Test
    public void validateCustomerJsonNamesTest() throws Exception
    {
    	CustomerCatalog xcat = CustomerCommon.get1RowTestCatalog();
        when(customerService.findAll()).thenReturn(xcat);
		//.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        mvc.perform(get(urlStr + "s"))
        		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.customers", hasSize(1)))
                .andExpect(jsonPath("$.customers[0].*", hasSize(18)))
                .andExpect(jsonPath("$.customers[0].custID").hasJsonPath())
                .andExpect(jsonPath("$.customers[0].firstName").hasJsonPath());

        verify(customerService, times(1)).findAll();
    }

    @Test
    public void validateCustomerJsonNameValuesTest() throws Exception
    {
    	CustomerCatalog xcat = CustomerCommon.get1RowTestCatalog();
        when(customerService.findAll()).thenReturn(xcat);
        mvc.perform(get(urlStr + "s"))
        		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.customers", hasSize(1)))
                .andExpect(jsonPath("$.customers[0].custID", is(1)))
                .andExpect(jsonPath("$.customers[0].firstName", is("Bob")));

        verify(customerService, times(1)).findAll();
    }
   
}
