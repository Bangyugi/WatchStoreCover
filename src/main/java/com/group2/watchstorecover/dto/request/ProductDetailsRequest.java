package com.group2.watchstorecover.dto.request;

import com.group2.watchstorecover.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailsRequest {
    String shellMaterial;
    String glassMaterial;
    String wireMaterial;
    String waterResistance;
    String shape;
    String faceSize;
    String shellColor;
    String faceColor;
    String origin;
    String style;
    int productId;
}
