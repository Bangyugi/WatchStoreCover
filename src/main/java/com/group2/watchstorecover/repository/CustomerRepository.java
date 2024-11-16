package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Page<Customer> findAllByCustomerAvailable(boolean b, Pageable pageable);

    Optional<Customer> findByCustomerIdAndCustomerAvailable(int customerId, boolean b);

    boolean existsByCustomerEmailOrCustomerPhone(String customerEmail, String customerPhone);

    boolean existsByCustomerEmail(String customerEmail);

    boolean existsByCustomerPhone(String customerPhone);
}
