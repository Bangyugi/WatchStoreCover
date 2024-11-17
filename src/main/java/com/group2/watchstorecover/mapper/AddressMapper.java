package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.AddressRequest;
import com.group2.watchstorecover.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressRequest addressRequest);
    void updateAddress(@MappingTarget Address address,AddressRequest addressRequest);
}
