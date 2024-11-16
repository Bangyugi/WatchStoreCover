package com.group2.watchstorecover.idclass;

import com.group2.watchstorecover.entity.Category;
import com.group2.watchstorecover.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryProduct implements Serializable {
    Category category;
    Product product;
}
