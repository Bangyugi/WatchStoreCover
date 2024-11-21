package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.AccountRequest;
import com.group2.watchstorecover.dto.response.PageCustom;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Account;
import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.entity.Role;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.mapper.AccountMapper;
import com.group2.watchstorecover.repository.AccountRepository;
import com.group2.watchstorecover.repository.CustomerRepository;
import com.group2.watchstorecover.repository.RoleRepository;
import com.group2.watchstorecover.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response addAccount(AccountRequest accountRequest){
        if (accountRepository.existsByUsername(accountRequest.getUsername())){
            throw new AppException(ErrorCode.ACCOUNT_ALREADY_EXISTS);
        }
        Role role = roleRepository.findById(3).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(accountRequest.getCustomerId(),true).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));

        if (accountRepository.existsByCustomer(customer)){
            throw new RuntimeException("Account already exists for this customer");
        }

        Account account = accountMapper.toAccount(accountRequest);
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setRole(role);
        account.setCustomer(customer);

        accountRepository.save(account);
        return Response.builder()
                .code(201)
                .data(account)
                .message("Account created successfully")
                .build();

    }

    @Override
    public Response updateAccount(int accountId, AccountRequest accountRequest){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        accountRepository.save(account);
        return Response.builder()
                .code(200)
                .data(account)
                .message("Account updated successfully")
                .build();
    }

    @Override
    public Response getAllAccount(Pageable pageable){
        Page<Account> page = accountRepository.findAllByAccountAvailable(true,pageable);
        PageCustom<Account> pageCustom = PageCustom.<Account>builder()
                .pageSize(page.getSize() + 1)
                .pageIndex(page.getNumber())
                .totalPage(page.getTotalPages())
                .content(page.getContent())
                .build();
        return Response.builder()
                .code(200)
                .data(pageCustom)
                .message("Get all account successfully")
                .build();
    }

    @Override
    public Response getAccountById(int accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        return Response.builder()
                .code(200)
                .data(account)
                .message("Get account by id successfully")
                .build();
    }




}
