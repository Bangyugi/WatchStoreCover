package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.CartRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Cart;
import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.entity.Product;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.CartMapper;
import com.group2.watchstorecover.repository.CartRepository;
import com.group2.watchstorecover.repository.CustomerRepository;
import com.group2.watchstorecover.repository.ProductRepository;
import com.group2.watchstorecover.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    @Override
    public Response addProduct(CartRequest cartRequest){
        Product product = productRepository.findByProductIdAndProductAvailable(cartRequest.getProductId(),true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(cartRequest.getCustomerId(),true).orElseThrow(()-> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        if (cartRepository.existsByCustomerAndProduct(customer,product)){
            throw new AppException(ErrorCode.CART_EXISTS);
        }
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQuantity(cartRequest.getQuantity());
        return Response.builder()
                .code(200)
                .message("Add product to cart successfully")
                .data(cartRepository.save(cart))
                .build();
    }

    @Override
    public Response updateCart(CartRequest cartRequest){
        Product product = productRepository.findByProductIdAndProductAvailable(cartRequest.getProductId(),true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(cartRequest.getCustomerId(),true).orElseThrow(()-> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        if (cartRepository.existsByCustomerAndProduct(customer,product)){
            Cart cart = cartRepository.findByCustomerAndProduct(customer,product).orElseThrow(()-> new AppException(ErrorCode.CART_NOT_FOUND));
            cart.setQuantity(cartRequest.getQuantity());
            cartRequest.setCartDate(cartRequest.getCartDate());
            return Response.builder()
                    .code(200)
                    .message("Update cart successfully")
                    .data(cartRepository.save(cart))
                    .build();
        }
        else {
            throw new AppException(ErrorCode.CART_NOT_FOUND);
        }
    }

    @Override
    public Response deleteCart(int customerId, int productId){

        Cart cart = cartRepository.findByCustomerIdAndProductId(customerId,productId).orElseThrow(()-> new AppException(ErrorCode.CART_NOT_FOUND));
        cartRepository.delete(cart);
        return Response.builder()
                .code(200)
                .message("Delete cart successfully")
                .build();
    }

    @Override
    public Response getCart(int productId, int customerId){
        Cart cart = cartRepository.findByCustomerIdAndProductId(customerId,productId).orElseThrow(()-> new AppException(ErrorCode.CART_NOT_FOUND));
        return Response.builder()
                .code(200)
                .message("Get cart successfully")
                .data(cart)
                .build();
    }

    @Override
    public Response getCartByCustomer(int customerId){
        List<Cart> carts = cartRepository.findByCustomerId(customerId);
        return Response.builder()
                .code(200)
                .message("Get cart by customer successfully")
                .data(carts)
                .build();
    }

    @Override
    public Response getCartByProduct(int productId){
        List<Cart> carts = cartRepository.findByProductId(productId);
        return Response.builder()
                .code(200)
                .message("Get cart by product successfully")
                .data(carts)
                .build();
    }
}
