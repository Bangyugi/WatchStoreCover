package com.group2.watchstorecover.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Brand {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int brandId;
    @Size(min = 4, message = "Brand name must have at least 4 character")
    String brandName;

    @Size(min = 100, message = "Brand details must have at least 100 character")
    String brandDetails;
    @Builder.Default
    boolean brandAvailable = true;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
            @JsonIgnore
    List<Product> products;

}
