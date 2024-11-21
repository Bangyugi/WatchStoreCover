package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.constant.UrlConstant;
import com.group2.watchstorecover.dto.request.AccountRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.AccountUrl.PREFIX)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(UrlConstant.AccountUrl.GET_BY_ID)
    public ResponseEntity<?> getAccountById(@PathVariable("accountId") int accountId) {
        Response response = accountService.getAccountById(accountId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping(UrlConstant.AccountUrl.GET_ALL)
    public ResponseEntity<?> getAllAccounts(
            @RequestParam(value="pageIndex", defaultValue = "1", required = false) int pageIndex,
            @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "username",required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageIndex -1, pageSize, Sort.by(sortBy).descending());
        Response response = accountService.getAllAccount(pageable);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    @PostMapping(UrlConstant.AccountUrl.CREATE)
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        Response response = accountService.addAccount(accountRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping(UrlConstant.AccountUrl.UPDATE)
    public ResponseEntity<?> updateAccount(@PathVariable("accountId") int accountId, @RequestBody @Valid AccountRequest accountRequest) {
        Response response = accountService.updateAccount(accountId, accountRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }


}
