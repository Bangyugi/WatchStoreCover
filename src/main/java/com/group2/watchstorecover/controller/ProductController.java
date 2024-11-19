package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.ProductRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.ProductUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "PRODUCT", description = "Product API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping(UrlConstant.ProductUrl.GET_ALL)
    public ResponseEntity<?> getAllProducts (
            @RequestParam(value = "pageIndex", defaultValue = "1", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = "productName", required = false) String sortBy
    ){
        Pageable pageable = PageRequest.of(pageIndex -1, pageSize, Sort.by(sortBy).descending());
        Response response = productService.findAllProducts(pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get product by id")
    @GetMapping(UrlConstant.ProductUrl.GET_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable("productId") int productId){
        Response response = productService.findProductById(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add new product")
    @PostMapping(UrlConstant.ProductUrl.CREATE)
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductRequest productRequest){
        Response response = productService.addProduct(productRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Update product")
    @PutMapping(UrlConstant.ProductUrl.UPDATE)
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId, @RequestBody ProductRequest productRequest){
        Response response = productService.updateProductById(productId,productRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Delete product")
    @DeleteMapping(UrlConstant.ProductUrl.DELETE)
    public ResponseEntity<?> deleteProduct (@PathVariable("productId") int productId){
        Response response = productService.deleteProductById(productId);
        return ResponseEntity.status(response.getCode()).body(response);
        
    }

    @Operation(summary = "Get product by name")
    @GetMapping(UrlConstant.ProductUrl.GET_BY_NAME)
    public ResponseEntity<?> getProductByProductName(@PathVariable("productName") String productName,
                                                     @RequestParam(value = "pageIndex",defaultValue = "1",required = false) int pageIndex,
                                                     @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                     @RequestParam(value = "sortBy",defaultValue = "productName",required = false) String sortBy
    ){
        Pageable pageable = PageRequest.of(pageIndex -1,pageSize,Sort.by(sortBy.toString()));
        Response response = productService.findProductByProductName(productName,pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
