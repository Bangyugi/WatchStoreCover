package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.CustomerRequest;
import com.group2.watchstorecover.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest customerRequest);
}
