package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.AddressRequest;
import com.group2.watchstorecover.dto.response.Response;

public interface AddressService {
    Response addAddress(AddressRequest addressRequest);

    Response updateAddress(int addressId, AddressRequest addressRequest);

    Response findAddressById(int addressId);

    Response findAddressByCustomerId(int customerId);
}
