package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.CartRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.CartUrl.PREFIX)
@RequiredArgsConstructor
@Tag(name = "CART", description = "Cart API")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Get all carts")
    @GetMapping(UrlConstant.CartUrl.GET_BY_ID)
    public ResponseEntity<?> getCart(@PathVariable("customerId")int customerId, @PathVariable("productId") int productId) {
        Response response = cartService.getCart(customerId, productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get cart by customer id")
    @GetMapping(UrlConstant.CartUrl.GET_BY_CUSTOMER_ID)
    public ResponseEntity<?> getCartByCustomerId(@PathVariable("customerId")int customerId) {
        Response response = cartService.getCartByCustomer(customerId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Get cart by product id")
    @GetMapping(UrlConstant.CartUrl.GET_BY_PRODUCT_ID)
    public ResponseEntity<?> getCartByProductId(@PathVariable("productId")int productId) {
        Response response = cartService.getCartByProduct(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Add new cart")
    @PostMapping(UrlConstant.CartUrl.CREATE)
    public ResponseEntity<?> createCart(@Valid @RequestBody CartRequest cartRequest) {
        Response response = cartService.addProduct(cartRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Update cart")
    @PutMapping(UrlConstant.CartUrl.UPDATE)
    public ResponseEntity<?> updateCart(@Valid @RequestBody CartRequest cartRequest) {
        Response response = cartService.updateCart(cartRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @Operation(summary = "Delete cart")
    @DeleteMapping(UrlConstant.CartUrl.DELETE)
    public ResponseEntity<?> deleteCart(@PathVariable("customerId")int customerId, @PathVariable("productId") int productId) {
        Response response = cartService.deleteCart(customerId, productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
