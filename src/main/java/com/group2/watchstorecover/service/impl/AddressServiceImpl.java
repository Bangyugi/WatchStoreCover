package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.AddressRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Address;
import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.AddressMapper;
import com.group2.watchstorecover.repository.AddressRepository;
import com.group2.watchstorecover.repository.CustomerRepository;
import com.group2.watchstorecover.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    private final CustomerRepository customerRepository;

    @Override
    public Response addAddress(AddressRequest addressRequest){
        Customer customer = customerRepository.findById(addressRequest.getCustomerId()).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        if (addressRepository.existsByCustomer(customer)){
            throw new AppException(ErrorCode.ERR_ADDRESS_EXISTS_FOR_CUSTOMER);
        }
        Address address = addressMapper.toAddress(addressRequest);
        address.setCustomer(customer);
        return Response.builder()
                .code(201)
                .message("Address added successfully")
                .data(addressRepository.save(address))
                .build();
    }

    @Override
    public Response updateAddress(int addressId, AddressRequest addressRequest){
        Address address = addressRepository.findById(addressId).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        if (addressRequest.getCustomerId() != address.getCustomer().getCustomerId()){
            throw new AppException(ErrorCode.ERR_CUSTOMER_NOT_CHANGE);
        }
        addressMapper.updateAddress(address,addressRequest);
        return Response.builder()
                .code(200)
                .message("Address updated successfully")
                .data(addressRepository.save(address))
                .build();
    }

    @Override
    public Response findAddressById(int addressId){
        Address address = addressRepository.findById(addressId).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        return Response.builder()
                .code(200)
                .message("Find address by id successfully")
                .data(address)
                .build();
    }

    @Override
    public Response findAddressByCustomerId(int customerId){
        Address address = addressRepository.findByCustomer(customerId).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        return Response.builder()
                .code(200)
                .message("Find address by customer id successfully")
                .data(address)
                .build();
    }
}
