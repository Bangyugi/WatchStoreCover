package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Account;
import com.group2.watchstorecover.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsernameAndAccountAvailable(String username, boolean b);

    boolean existsByUsername(String username);

    boolean existsByCustomer(Customer customer);

    Page<Account> findAllByAccountAvailable(boolean b, Pageable pageable);

    Optional<Account> findByUsername(String username);
}
