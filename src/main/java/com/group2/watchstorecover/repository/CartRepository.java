package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Cart;
import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.entity.Product;
import com.group2.watchstorecover.idclass.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, CustomerProduct> {


    boolean existsByCustomerAndProduct(Customer customer, Product product);

    Optional<Cart> findByCustomerAndProduct(Customer customer, Product product);

    @Query("FROM  Cart c " +
            "WHERE c.product.productId = :productId AND " +
            "c.customer.customerId =:customerId ")
    Optional<Cart> findByCustomerIdAndProductId(int customerId, int productId);

    @Query(value = "FROM Cart c WHERE c.customer.customerId =:customerId")
    List<Cart> findByCustomerId(int customerId);

    @Query(value = "FROM Cart c WHERE c.product.productId =:productId")
    List<Cart> findByProductId(int productId);
}
