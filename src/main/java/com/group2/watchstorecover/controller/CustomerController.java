package com.group2.watchstorecover.controller;


import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.CustomerRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.CustomerUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "CUSTOMER API", description = "API for customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Get all customer")
    @GetMapping(UrlConstant.CustomerUrl.GET_ALL)
    public ResponseEntity<?> getAllCustomer(
            @RequestParam(value = "pageIndex", defaultValue = "1", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "accountCreateDate", required = false) String sortBy) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.by(sortBy).descending());
        Response response = customerService.getCustomer(pageable);
        return ResponseEntity.status(response.getCode()).body(response);

    }


    @Operation(summary = "Get customer by id")
    @GetMapping(UrlConstant.CustomerUrl.GET_BY_ID)
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") int customerId){
        Response response = customerService.findCustomerById(customerId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add customer")
    @PostMapping(UrlConstant.CustomerUrl.CREATE)
    public ResponseEntity<?> addCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        Response response = customerService.addCustomer(customerRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Update customer")
    @PutMapping(UrlConstant.CustomerUrl.UPDATE)
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") int customerId, @RequestBody @Valid CustomerRequest customerRequest){
        Response response = customerService.updateCustomer(customerId, customerRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Temporary delete customer")
    @DeleteMapping( UrlConstant.CustomerUrl.TEMPORARY_DELETE)
    public ResponseEntity<?> temporaryDelCustomer(@PathVariable("customerId") int customerId){
        Response response = customerService.temporaryDelCustomer(customerId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Permanent delete customer")
    @DeleteMapping(UrlConstant.CustomerUrl.PERMANENT_DELETE)
    public ResponseEntity<?> permanentDelete (@PathVariable("customerId") Integer customerId){
        Response response = customerService.permanentDelCustomer(customerId);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
