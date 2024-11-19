package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.ProductRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.ProductService;
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
public class ProductController {

    private final ProductService productService;

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

    @GetMapping(UrlConstant.ProductUrl.GET_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable("productId") int productId){
        Response response = productService.findProductById(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping(UrlConstant.ProductUrl.CREATE)
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductRequest productRequest){
        Response response = productService.addProduct(productRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping(UrlConstant.ProductUrl.UPDATE)
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId, @RequestBody ProductRequest productRequest){
        Response response = productService.updateProductById(productId,productRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping(UrlConstant.ProductUrl.DELETE)
    public ResponseEntity<?> deleteProduct (@PathVariable("productId") int productId){
        Response response = productService.deleteProductById(productId);
        return ResponseEntity.status(response.getCode()).body(response);
        
    }
}
