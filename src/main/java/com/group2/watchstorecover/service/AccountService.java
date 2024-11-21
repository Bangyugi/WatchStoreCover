package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.AccountRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface AccountService {
    Response addAccount (AccountRequest accountRequest);

    Response updateAccount(int accountId, AccountRequest accountRequest);

    Response getAllAccount(Pageable pageable);

    Response getAccountById(int accountId);


}
