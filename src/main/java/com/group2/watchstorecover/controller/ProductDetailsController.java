package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.ProductDetailsRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.ProductDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.ProductDetailsUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "PRODUCT_DETAILS", description = "Product Details API")
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    @Operation(summary = "Get all product details")
    @GetMapping(UrlConstant.ProductDetailsUrl.GET_ALL)
    public ResponseEntity<?> findAllProductDetails(
            @RequestParam(value = "pageIndex",defaultValue = "1",required = false) int pageIndex,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
    ){
        Pageable pageable = PageRequest.of(pageIndex -1, pageSize);
        Response response = productDetailsService.getProductDetails(pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get product details by id")
    @GetMapping(UrlConstant.ProductDetailsUrl.GET_BY_ID)
    public ResponseEntity<?> findProductDetailsById(@PathVariable("productDetailsId") int productId){
        Response response = productDetailsService.getProductDetailsById(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get product details by product id")
    @GetMapping(UrlConstant.ProductDetailsUrl.GET_BY_PRODUCT_ID)
    public ResponseEntity<?> findProductDetailsByProductId(@PathVariable("productId") int productId){
        Response response = productDetailsService.getProductDetailsByProductId(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add new product details")
    @PostMapping(UrlConstant.ProductDetailsUrl.CREATE)
    public ResponseEntity<?> addProductDetails(@Valid @RequestBody ProductDetailsRequest productDetailsRequest){
        Response response = productDetailsService.addProductDetails(productDetailsRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Update product details")
    @PutMapping(UrlConstant.ProductDetailsUrl.UPDATE)
    public ResponseEntity<?> updateProductDetails(@PathVariable("productDetailsId")int productDetailsId, @Valid @RequestBody ProductDetailsRequest productDetailsRequest){
        Response response = productDetailsService.updateProductDetails(productDetailsId,productDetailsRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
