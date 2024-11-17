package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.AccountRequest;
import com.group2.watchstorecover.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountRequest accountRequest);

}
