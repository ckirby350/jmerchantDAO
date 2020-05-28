package com.radsyn.jmerchdao.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radsyn.jmerchdao.model.Customer;
import java.util.List;
import java.util.Map;

public interface CustomerCatalog {
    @JsonProperty("customers")
    List<Customer> getCustomerRecords();
}



