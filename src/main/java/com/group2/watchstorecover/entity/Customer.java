package com.group2.watchstorecover.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerId;
    @Column(columnDefinition = "varchar(40)", nullable = false)
    @Size(min = 3, message = "Customer name must have at least 3 character")
    String customerName;
    @Column(columnDefinition = "varchar(50) collate 'utf8_bin'", unique = true, nullable = false)
    String customerEmail;
    @Column(columnDefinition = "varchar(10)", nullable = false, unique = true)
    @Size(min = 10, max = 11, message = "Phone number must have 10 or 11 digits")
    String customerPhone;

    @Builder.Default
    @Column(columnDefinition = "DATE")
    Date accountCreateDate = new Date(System.currentTimeMillis());

    @Builder.Default
    boolean customerAvailable = true;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    Account account;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Cart> carts;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<PurchaseHistory> purchaseHistories;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> comments;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Blog> blogs;
}
