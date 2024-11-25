package com.group2.watchstorecover.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int commentId;
    @Column(columnDefinition = "text")
    String commentContent;
    @Builder.Default
            @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime commentDate = LocalDateTime.now();

    @ManyToOne @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToOne @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "commentParent_id")
    Comment commentParent;

    @Builder.Default
    boolean commentAvailable = true;

    @OneToMany(mappedBy = "commentParent")
            @JsonIgnore
    List<Comment> comments = new ArrayList<>();
}
