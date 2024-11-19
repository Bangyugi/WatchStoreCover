package com.group2.watchstorecover.entity;

import com.group2.watchstorecover.idclass.CustomerProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@IdClass(CustomerProduct.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    Customer customer;
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    Product product;
    @Min(value = 1, message = "Quantity must be at least 1")
    int quantity;
    @Version
    int version;
    @Builder.Default
    @Column(columnDefinition = "DATE")
    LocalDate cartDate = LocalDate.now();
}
