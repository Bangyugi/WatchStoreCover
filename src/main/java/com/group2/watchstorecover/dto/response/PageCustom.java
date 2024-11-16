package com.group2.watchstorecover.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageCustom<T> {
    int pageIndex;
    int pageSize;
    int totalPage;
    List<T>content;
    String sort;
}
