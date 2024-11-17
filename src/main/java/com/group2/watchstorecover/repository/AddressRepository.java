package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Address;
import com.group2.watchstorecover.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByCustomer(Customer customer);

    @Query(value = "from Address a where a.customer.customerId = :customerId")
    Optional<Address> findByCustomer(int customerId);
}
