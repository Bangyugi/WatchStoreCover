package com.group2.watchstorecover.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryProduct {

    @Id
            @ManyToOne @JoinColumn(name = "category_id")
    Category category;
    @Id
            @ManyToOne @JoinColumn(name = "product_id")

    Product product;
}
