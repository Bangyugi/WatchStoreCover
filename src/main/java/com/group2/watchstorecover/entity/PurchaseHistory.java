package com.group2.watchstorecover.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int purchaseHistoryId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    int quantity;
    @Builder.Default
    LocalDate purchaseHistoryDay = LocalDate.now();
    @Column(columnDefinition = "varchar(50)")
    String paymentMethod;
    int priceSold;

}
