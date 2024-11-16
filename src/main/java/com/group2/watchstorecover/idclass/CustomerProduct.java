package com.group2.watchstorecover.idclass;

import com.group2.watchstorecover.entity.Customer;
import com.group2.watchstorecover.entity.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults  (level = AccessLevel.PRIVATE)
public class CustomerProduct implements Serializable {
    Customer customer;
    Product product;
}
