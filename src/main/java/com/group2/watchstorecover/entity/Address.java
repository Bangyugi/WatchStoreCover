package com.group2.watchstorecover.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int addressId;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    String addressCommune;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    String addressDistrict;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    String addressProvince;
    @Column(columnDefinition = "varchar(70)")
    String addressDetails;
    @OneToOne @JoinColumn(name = "customer_id")
    Customer customer;
}
