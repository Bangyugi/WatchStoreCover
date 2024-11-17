package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.AddressRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.AddressUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "ADDRESS", description = "API for address")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Get address by id")
    @GetMapping(UrlConstant.AddressUrl.FIND_BY_ADDRESS_ID)
    public ResponseEntity<?> getAddressById(@PathVariable("addressId") int addressId){
        Response response = addressService.findAddressById(addressId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get address by customer id")
    @GetMapping(UrlConstant.AddressUrl.FIND_BY_CUSTOMER_ID)
    public ResponseEntity<?> getAddressByCustomerId(@PathVariable("customerId") int customerId){
        Response response = addressService.findAddressByCustomerId(customerId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add address")
    @PostMapping(UrlConstant.AddressUrl.CREATE)
    public ResponseEntity<?> addAddress (@Valid @RequestBody AddressRequest addressRequest){
        Response response = addressService.addAddress(addressRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Update address")
    @PutMapping(UrlConstant.AddressUrl.UPDATE)
    public ResponseEntity<?> updateAddress(@PathVariable("addressId") int addressId, @Valid @RequestBody AddressRequest addressRequest){
        Response response = addressService.updateAddress(addressId, addressRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }



}
