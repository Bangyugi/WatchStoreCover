package com.group2.watchstorecover.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;
    @Column(columnDefinition = "varchar(150)", nullable = false, unique = true)
    String productName;
    @Min(value = 1, message = "Price must be at least 1")
    int productPrice;
    @Builder.Default
    @Column(columnDefinition = "DATE")
    Date productSaleDate = new Date(System.currentTimeMillis());
    @Min(value = 1, message = "Quantity must be at least 1")
    int productQuantity;
    @Min(value = 0, message = "Price reduction must be at least 0")
    @Max(value = 100, message = "Price reduction must be at most 100")
    int productPriceReduction;
    @Version
    long version;
    @Builder.Default
    boolean productAvailable = true;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    Brand brand;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<ProductImage> productImages;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Cart> carts;
    @OneToOne(mappedBy = "product")
    @JsonIgnore
    ProductDetails productDetails;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<PurchaseHistory> purchaseHistories;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> comments;
}
