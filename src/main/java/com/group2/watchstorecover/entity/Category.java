package com.group2.watchstorecover.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int categoryId;
    @Size(min = 4, message = "Category name must have at least 4 character")
            @Column(columnDefinition = "varchar(150)", unique = true, nullable = false)
    String categoryName;

    @Builder.Default
    boolean categoryAvailable = true;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JsonIgnore
    List<CategoryProduct> products;

}
