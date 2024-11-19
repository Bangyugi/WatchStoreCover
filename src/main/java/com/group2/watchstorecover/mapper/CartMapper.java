package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.CartRequest;
import com.group2.watchstorecover.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CartRequest cartRequest);
}
