package com.group2.watchstorecover.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int blogId;
    @Column(columnDefinition = "varchar(200)",nullable = false)
    String blogName;
    @Column(columnDefinition = "text",nullable = false)
    String blogContent;

    @Builder.Default
    int view =  0;
    @Builder.Default
    boolean available = true;
    @Builder.Default
            @Column(columnDefinition = "DATE")
    Date blogCreateDate = new Date(System.currentTimeMillis());
    @ManyToOne @JoinColumn(name = "customer_id")
    Customer customer;
}
