package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.CustomerRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Response getCustomer(Pageable pageable);

    Response findCustomerById(int customerId);

    Response addCustomer (CustomerRequest customerRequest);


    Response updateCustomer(int customerId, CustomerRequest customerRequest);

    Response temporaryDelCustomer(Integer customerIds);

    Response permanentDelCustomer(Integer customerIds);
}
