package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.CartRequest;
import com.group2.watchstorecover.dto.response.Response;

public interface CartService {
    Response addProduct (CartRequest cartRequest);

    Response updateCart(CartRequest cartRequest);


    Response deleteCart(int productId, int customerId);

    Response getCart(int productId, int customerId);

    Response getCartByCustomer(int customerId);

    Response getCartByProduct(int productId);
}
