package com.group2.watchstorecover.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {
    String commentContent ;
    int customerId ;
    int productId ;
    int commentParentId;
    @Builder.Default
    boolean commentAvailable = true;
    @Builder.Default
    LocalDateTime commentDate = LocalDateTime.now();

}
