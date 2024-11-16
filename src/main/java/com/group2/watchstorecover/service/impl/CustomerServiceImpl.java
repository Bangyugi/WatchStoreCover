package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.CustomerRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.CustomerMapper;
import com.group2.watchstorecover.repository.CustomerRepository;
import com.group2.watchstorecover.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    /**
     * Get all customer
     * @param pageable pageable
     * @return PageCustom of Customer
     */
    @Override
    public Response getCustomer(Pageable pageable)
    {
        Page<Customer> page = customerRepository.findAllByCustomerAvailable(true,pageable);
        PageCustom<Customer> pageCustom = PageCustom.<Customer>builder()
                .pageIndex(page.getNumber()+1)
                .pageSize(page.getSize())
                .totalPage(page.getTotalPages())
                .content(page.getContent())
                .sort(page.getSort().toString())
                .build();
        return Response.builder()
                .code(200)
                .data(pageCustom)
                .message("Get all customer successfully")
                .build();

    }

    @Override
    public Response findCustomerById(int customerId){
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(customerId, true).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        return Response.builder()
                .code(200)
                .data(customer)
                .message("find customer by id successfully")
                .build();
    }

    private boolean validate(String regex, String text){
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    @Override
    public Response addCustomer (CustomerRequest customerRequest){
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String regexPhone = "^(84|0[3|5|7|8|9])+([0-9]{8})$";
        if(!validate(regexEmail,customerRequest.getCustomerEmail()) || !validate(regexPhone,customerRequest.getCustomerPhone())){
                    if (!validate(regexEmail,customerRequest.getCustomerEmail())){
                        throw new AppException(ErrorCode.ERR_CUSTOMER_EMAIL_INVALID);
                    }
                    else
                    {
                        if (!validate(regexPhone,customerRequest.getCustomerPhone())){
                            throw new AppException(ErrorCode.ERR_CUSTOMER_PHONE_INVALID);
                        }
                    }
        }
        Customer customer = customerMapper.toCustomer(customerRequest);
        if (!customerRepository.existsByCustomerEmailOrCustomerPhone(customer.getCustomerEmail(),customer.getCustomerPhone())){
            return Response.builder()
                    .code(201)
                    .data(customerRepository.save(customer))
                    .message("Add customer successfully")
                    .build();


        }
        else {
            if (customerRepository.existsByCustomerEmail(customer.getCustomerEmail())){
                throw new AppException(ErrorCode.EMAIL_EXISTS);
            }
            else{
                    throw new AppException(ErrorCode.PHONE_EXISTS);

            }
        }
    }

    @Override
    public Response updateCustomer(int customerId, CustomerRequest customerRequest){
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String regexPhone = "^(84|0[3|5|7|8|9])+([0-9]{8})$";
        if (!validate(regexEmail,customerRequest.getCustomerEmail()) || !validate(regexPhone, customerRequest.getCustomerPhone())){
            if (!validate(regexEmail,customerRequest.getCustomerEmail())){
                throw new AppException(ErrorCode.ERR_CUSTOMER_EMAIL_INVALID);
            }
            else {
                if (!validate(regexPhone,customerRequest.getCustomerPhone())){
                    throw new AppException(ErrorCode.ERR_CUSTOMER_PHONE_INVALID);
                }
            }
        }
        if (customerRequest.getCustomerEmail().equals(customer.getCustomerEmail())){
            if (!customerRequest.getCustomerPhone().equals(customer.getCustomerPhone())){
                    customer.setCustomerPhone(customerRequest.getCustomerPhone());
            }
            if (!customerRequest.getCustomerName().equals(customer.getCustomerName())){
                customer.setCustomerName(customerRequest.getCustomerName());
            }
        }
        else {
            throw new AppException(ErrorCode.EMAIL_NO_CHANGE);
        }

        return Response.builder()
                .code(200)
                .data(customerRepository.save(customer))
                .message("Update customer successfully")
                .build();

    }

    @Override
    public Response temporaryDelCustomer(Integer customerId){

            Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
            customer.setCustomerAvailable(false);
            customerRepository.save(customer);

        return Response.builder()
                .code(200)
                .data(customer)
                .message("Temporary delete customer successfully")
                .build();
    }

    @Override
    public Response permanentDelCustomer(Integer customerId){
            Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
            customerRepository.delete(customer);
        return Response.builder()
                .code(200)
                .data(customer)
                .message("Permanent delete customer successfully")
                .build();
    }

}
